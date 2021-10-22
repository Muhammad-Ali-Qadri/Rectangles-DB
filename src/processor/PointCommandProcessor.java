package processor;

import database.Database;
import database.PRQuadTreeDatabase;
import quadtree.*;
import skiplist.*;

import java.util.List;

public class PointCommandProcessor implements Processor{
    /**
     * {@inheritDoc}
     */

    private final Database<String, Point> data;

    public PointCommandProcessor() {
        data = new PRQuadTreeDatabase();
    }

    @Override
    public String process(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String output;
        if (input.equals("dump") ) {
            return data.dump();
        }
        else if (input.equals("duplicates") ) {
            List<Point> item = data.duplicates();
            output= duplicateHandler(item);
        }
        else {
            String command = input.substring(0, input.indexOf(' '));
            String rectObj = everythingAfter1stSpace(input);
            String[] container = rectObj.split("\\s+");

            switch (command) {
                case "insert": {
                    String name = container[0];
                    int x = Integer.parseInt(container[1]);
                    int y = Integer.parseInt(container[2]);
                    Point sendMe = new Point(x, y);
                    KVPair<String, Point> kvObj = new KVPair<>(name, sendMe);
                    boolean inserted = data.insert(kvObj);
                    output = insertHandler(kvObj, inserted);
                    break;
                }
                case "remove": {
                    KVPair<String, Point> result;
                    Point value = null;
                    boolean normalRemove = true;
                    if (container.length > 1) { //we were given coordinates
                        int x = Integer.parseInt(container[0]);
                        int y = Integer.parseInt(container[1]);
                        value = new Point(x, y);
                        if (!data.validateV(value)) {
                            return "Point rejected: (" + value + ")";
                        }
                        result = data.removeByValue(value);
                        normalRemove = false;
                    } else {
                        result = data.remove(container[0]);
                    }
                    String sendMe = normalRemove ? container[0]
                            : value.toString();
                    output = removeHandler(normalRemove, sendMe, result);
                    break;
                    }
                case "regionsearch": {
                    int x = Integer.parseInt(container[0]);
                    int y = Integer.parseInt(container[1]);
                    int w = Integer.parseInt(container[2]);
                    int h = Integer.parseInt(container[3]);
                    Rectangle printMe = new Rectangle(x, y, w, h);
                    if (w < 1 || h < 1) {
                        StringBuilder item = new StringBuilder("Rectangle ");
                        item.append("rejected: (").append(printMe).append(")");
                        return item.toString();
                    }

                    StringBuilder nodes = new StringBuilder();
                    List<KVPair<String, Point>> ans;
                    ans = data.regionSearch(x, y, w, h, nodes);
                    output = regionHandler(ans, printMe, nodes.toString());
                    break;
                }
                case "search": {
                    //This is search
                    List<KVPair<String, Point>> resp = data.search(container[0]);
                    output = searchHandler(resp, container[0]);
                    break;
                }
                default: output = "";
            }
        }
        return output;
    }

    /**
     *
     * @param line Parsed Line from top level point2 class
     * @return Just everything after initial space
     */
    private String everythingAfter1stSpace(String line) {
        int findSpace = line.indexOf(' ') + 1;
        int lineLength = line.length();
        return line.substring(findSpace, lineLength ).trim();
    }

    /**
     *
     * @param pair list of points that will be processed into a string
     * @param me the rectangular region in question
     * @return String representation for console, for regionSearch
     */
    private String regionHandler(List<KVPair<String, Point>> pair, Rectangle me,
                                 String nodes) {

        if (pair == null) {
            return "Rectangle rejected: (" + me.toString() + ")";
        }
        StringBuilder sb =
                new StringBuilder("Points intersecting region (");
        sb.append(me).append(")").append("\n");
        if (pair.size() > 0) {
            String prefix = "";
            for (Object point : pair) {
                sb.append(prefix);
                prefix = "\n";
                sb.append("Point found: (").append(point).append(")");
            }
        }
        sb.append("\n").append(nodes).append(" quadtree nodes visited");
        return sb.toString();
    }

    /**
     *
     * @param pair Contains list search found
     * @param key Contains name of Point to be found
     * @return returns string that needs to console logged.
     */
    private String searchHandler(List<KVPair<String, Point>> pair, String key) {
        if (pair == null) {
            return "Point not found: " + key;
        }

        StringBuilder sb = new StringBuilder();
        for (KVPair<String, Point> point : pair) {
            if (sb.length() > 0){
                sb.append("\n");
            }
            sb.append("Found (").append(point).append(")");
        }
        return sb.toString();
    }

    /**
     *
     * @param point which was intended on being added.
     * @param inserted to skipList and quadtree or not
     * @return string representation for system out
     */
    private String insertHandler(KVPair<String, Point> point
            , boolean inserted) {
        return  (inserted) ? "Point inserted: (" + point + ")":
                "Point rejected: (" + point+ ")";
    }

    /**
     *
     * @param removalType when true denotes a remove with key, false with point
     * @param key for when remove with key was called, or the point as a string
     * @param pair the point which was removed
     * @return string representation for system out
     */
    private String removeHandler(boolean removalType, String key
            , KVPair<String, Point> pair)  {
        if(removalType) {
            return (pair != null) ? "Point removed: (" + pair +
                    ")" : "Point not removed: " + key;
        }
        else {
            return (pair != null) ? "Point removed: (" + pair + ")" :
                    "Point not found: (" + key + ")";
        }
    }

    /**
     *
     * @param points returned from db
     * @return string to be printed to console
     */
    private String duplicateHandler(List<Point> points) {
        StringBuilder start = new StringBuilder("Duplicate points:");
        for (Object point : points) {
            start.append("\n(").append(point.toString()).append(")");
        }
        return start.toString();
    }
}
