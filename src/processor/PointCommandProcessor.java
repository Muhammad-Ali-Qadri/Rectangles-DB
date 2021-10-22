package processor;

import database.Database;
import database.PRQuadTreeDatabase;
import quadtree.Point;
import skiplist.KVPair;

import java.util.List;


/**
 * Processor for parsing commands and performing different operations based
 * on the commands and values passed onto the PRQuadTree database
 *
 * @author Cristian Diaz-Claure
 * @version 1
 */
public class PointCommandProcessor implements Processor {

    private final Database<String, Point> data;

    /**
     * Construct this object, initialize quad tree.
     */
    public PointCommandProcessor() {
        data = new PRQuadTreeDatabase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String process(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String output;
        if (input.equals("dump")) {
            return data.dump();
        }
        else if (input.equals("duplicates")) {
            output = duplicateHandler();
        }
        else {
            String command = input.substring(0, input.indexOf(' '));
            String rectObj = everythingAfter1stSpace(input);
            String[] container = rectObj.split("\\s+");

            switch (command) {
                case "insert": {
                    output = insertHandler(container);
                    break;
                }
                case "remove": {
                    output = removeHandler(container);
                    break;
                }
                case "regionsearch": {
                    output = regionHandler(container);
                    break;
                }
                case "search": {
                    output = searchHandler(container);
                    break;
                }
                default:
                    output = "";
            }
        }
        return output;
    }

    /**
     * @param container the list of inputs for insert command
     * @return string representation for system out
     */
    private String insertHandler(String[] container) {
        String name = container[0];
        int x = Integer.parseInt(container[1]);
        int y = Integer.parseInt(container[2]);
        Point sendMe = new Point(x, y);
        KVPair<String, Point> kvObj = new KVPair<>(name, sendMe);

        return (data.insert(kvObj)) ? "Point inserted: (" + kvObj + ")" :
                "Point rejected: (" + kvObj + ")";
    }


    /**
     * @param container the list of inputs for insert command
     * @return string representation for system out
     */
    private String removeHandler(String[] container) {

        KVPair<String, Point> removedElement;
        Point point;

        if (container.length > 1) { //we were given coordinates
            int x = Integer.parseInt(container[0]);
            int y = Integer.parseInt(container[1]);
            point = new Point(x, y);

            if (!data.validateV(point)) {
                return "Point rejected: (" + point + ")";
            }

            removedElement = data.removeByValue(point);

            return (removedElement != null) ?
                    "Point removed: (" + removedElement + ")" :
                    "Point not found: (" + point + ")";
        }
        else {
            removedElement = data.remove(container[0]);

            return (removedElement != null) ?
                    "Point removed: (" + removedElement + ")" :
                    "Point not removed: " + container[0];
        }
    }


    /**
     * @param line Parsed Line from top level point2 class
     * @return Just everything after initial space
     */
    private String everythingAfter1stSpace(String line) {
        int findSpace = line.indexOf(' ') + 1;
        int lineLength = line.length();
        return line.substring(findSpace, lineLength).trim();
    }

    /**
     * @param container the list of inputs for insert command
     * @return String representation for console, for regionSearch
     */
    private String regionHandler(String[] container) {

        int x = Integer.parseInt(container[0]);
        int y = Integer.parseInt(container[1]);
        int w = Integer.parseInt(container[2]);
        int h = Integer.parseInt(container[3]);
        Rectangle region = new Rectangle(x, y, w, h);

        if (w < 1 || h < 1) {
            return "Rectangle " + "rejected: (" + region + ")";
        }

        StringBuilder nodesVisited = new StringBuilder();
        List<KVPair<String, Point>> pairs = data.regionSearch(x, y, w, h,
                nodesVisited);

        if (pairs == null) {
            return "Rectangle rejected: (" + region + ")";
        }

        StringBuilder sb =
                new StringBuilder("Points intersecting region (");
        sb.append(region).append(")").append("\n");

        if (pairs.size() > 0) {
            String prefix = "";
            for (Object point : pairs) {
                sb.append(prefix);
                prefix = "\n";
                sb.append("Point found: (").append(point).append(")");
            }
        }
        sb.append("\n").append(nodesVisited).append(" quadtree nodes visited");
        return sb.toString();
    }

    /**
     * @param container the list of inputs for insert command
     * @return returns string that needs to console logged.
     */
    private String searchHandler(String[] container) {
        List<KVPair<String, Point>> pairs = data.search(container[0]);
        if (pairs == null) {
            return "Point not found: " + container[0];
        }

        StringBuilder sb = new StringBuilder();
        for (KVPair<String, Point> point : pairs) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("Found (").append(point).append(")");
        }
        return sb.toString();
    }


    /**
     * @return string to be printed to console
     */
    private String duplicateHandler() {
        List<Point> points = data.duplicates();

        StringBuilder start = new StringBuilder("Duplicate points:");
        for (Object point : points) {
            start.append("\n(").append(point.toString()).append(")");
        }
        return start.toString();
    }
}
