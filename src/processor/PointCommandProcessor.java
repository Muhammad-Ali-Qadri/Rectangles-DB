package processor;

import database.Database;
import database.PRQaudTreeDatabase;
import quadtree.*;
import skiplist.*;

import java.util.ArrayList;
import java.util.List;

public class PointCommandProcessor implements Processor{
    /**
     * {@inheritDoc}
     */

    private final Database<String, Point> data;

    public PointCommandProcessor() {
        data = new PRQaudTreeDatabase();
    }

    @Override
    public String process(String input) {
        String output;
        if (input.equals("dump") ) {
            return data.dump();
        }
        else if (input.equals("duplicates") ) {
            List<Point> item = data.duplicates(); //nothing to do... just call method
            output= commandFormatter(false, item, "duplicates", null, null, null);
        }
        else {
            String command = input.substring(0, input.indexOf(' '));
            if (command.equals("insert") ) {
                //I want what's left of the string, after the space
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s+");
                String name = container[0];
                int x = Integer.parseInt(container[1]);
                int y = Integer.parseInt(container[2]);

                Point sendMe = new Point(x, y);
                KVPair<String, Point> kvobj = new KVPair<>(name, sendMe);
                boolean inserted = data.insert(kvobj);
                output= commandFormatter(inserted, listifier(kvobj), "insert", null, null, null);
            }
            else if (command.equals("remove" ) ) {
                //I want what's beyond the first space, in Rect_obj
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s+");

                KVPair<String, Point> result;
                Point value = null;
                boolean normalRemove = true;
                if (container.length > 1) { //we were given coordinates
                    int x = Integer.parseInt(container[0]);
                    int y = Integer.parseInt(container[1]);
                    value = new Point (x, y);
                    if (!data.validateV(value)) {
                        return "Point rejected: (" + value + ")";
                    }
                    result = data.removeByValue(value);
                    normalRemove = false;
                }
                else {
                    result = data.remove(container[0]);
                }
                String sendMe = normalRemove ? container[0] : value.toString();
                output = commandFormatter(normalRemove, listifier(result),
                        "remove", sendMe, null, null);
            }
            else if (command.equals("regionsearch") ) {
                //I want what's left of the string... after the space
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s+");
                int x = Integer.parseInt(container[0]);
                int y = Integer.parseInt(container[1]);
                int w = Integer.parseInt(container[2]);
                int h = Integer.parseInt(container[3]);
                Rectangle printMe = new Rectangle(x, y, w, h);
                if ( w < 1 || h < 1)
                {
                    StringBuilder item = new StringBuilder("Rectangle rejected: (");
                    item.append(printMe);
                    item.append(")");
                    return item.toString();
                }

                StringBuilder nodes = new StringBuilder();
                List<KVPair<String, Point>> ans = data.regionSearch( x, y, w, h, nodes);
                output = commandFormatter(false, ans, "regionsearch", null,
                        printMe, nodes.toString());
            }
            else {
                //I want what's left of the string, this is search
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s"); //just the name
                List<KVPair<String, Point>>  resp = data.search(container[0] );
                output = commandFormatter(false, resp, "search", container[0], null, null);
            }
        }
        return output;
    }

    /**
     *
     * @param line Parsed Line from top level point2 class
     * @return Just everything after initial space
     */
    public String everythingAfter1stSpace(String line) {
        int findSpace = line.indexOf(' ') + 1;
        int lineLength = line.length();
        return line.substring(findSpace, lineLength ).trim();
    }

    /**
     *
     * @param successful insertion operation returns this, is passed to this method
     * @param pair list of data for any operation, for individual commands its a list with one item
     * @param command command ran
     * @param key the key if passed
     * @param region Rectangle if needed
     * @param nodes
     * @return String representation of all commands
     */

    public String commandFormatter(boolean successful, List<? extends Object>
            pair, String command, String key, Rectangle region, String nodes) {
        String sendMe;
        switch(command) {
            case "insert":
                sendMe = (successful) ? "Point inserted: (" + pair.get(0) + ")":
                            "Point rejected: (" + pair.get(0)+ ")";
                break;
            case "remove":
                if(successful) {
                    sendMe = (pair.get(0) != null) ? "Point removed: (" + key + ", " + pair.get(0) + ") Removed" :
                            "Point not removed: " + key;
                }
                else {
                    sendMe = (pair.get(0) != null) ? "Point removed: (" + pair.get(0) + ")" : "Point not found: (" + key + ")";
                }
                break;
            case "regionsearch":
                sendMe = regionHandler(pair, region, nodes);
                break;
            case "duplicates":
                StringBuilder start = new StringBuilder("Duplicate points:");
                for (Object point : pair) {
                    start.append("\n(").append(point.toString()).append(")");
                }
                sendMe = start.toString();
                break;
            default:
                sendMe = listHandler(pair, key);
                break;
        }
        return sendMe;
    }

    /**
     *
     * @param pair list of points that will be processed into a string
     * @param me the rectangular region in question
     * @return String representation for console, for regionSearch
     */
    private String regionHandler(List<? extends Object> pair, Rectangle me,
                                 String nodes) {

        if (pair == null) {
            return "Rectangle rejected: (" + me.toString() + ")";
        }
        StringBuilder sb =
                new StringBuilder("Points intersecting region (");
        sb.append(me);
        sb.append(")");
        sb.append("\n");
        if (pair.size() > 0) {
            String prefix = "";
            for (Object point : pair) {
                sb.append(prefix);
                prefix = "\n";
                sb.append("Point found: (").append(point).append(")");
            }
        }

        sb.append("\n").append(nodes);
        sb.append(" quadtree nodes visited");
        return sb.toString();
    }

    /**
     *
     * @param pair Contains list search found
     * @param key Contains name of Point to be found
     * @return returns string that needs to console logged.
     */
    public String listHandler(List<? extends Object> pair, String key) {
        if (pair == null) {
            return "Point not found: " + key;
        }

        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (Object point : pair) {
            sb.append(prefix);
            prefix = "\n";
            sb.append("Found (").append(point).append(")");
        }
        return sb.toString();
    }

    /**
     *
     * @param obj A kvpair, that we want as a list
     * @return List of that object
     */
    public List<KVPair<String, Point>> listifier(KVPair<String, Point> obj) {
        List<KVPair<String, Point>> item = new ArrayList<>();
        item.add(obj);
        return item;
    }
}
