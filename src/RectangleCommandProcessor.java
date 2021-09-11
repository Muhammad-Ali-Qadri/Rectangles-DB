import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 *
 * @author Muhammad Ali Qadri
 */
public class RectangleCommandProcessor implements Processor {

    //Size of the world box
    private static final int WorldBoxWidth = 1024;
    private static final int worldBoxHeight = 1024;

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private final Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * <p>
     * the database object to manipulate
     */
    public RectangleCommandProcessor() {
        data = new SkipListDatabase();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String process(String input) {
        //stores all the words form input
        List<String> allInputs = new ArrayList<>();
        //finds all the words in the input statement
        Matcher matcher = Pattern.compile("[A-Za-z0-9_-]+").matcher(input);

        while (matcher.find())
            allInputs.add(matcher.group());

        StringBuilder result = new StringBuilder();

        for (String in : allInputs) result.append(in).append(" ");
        result.append("\n\n");

        //allInputs.get(0) is the command to operate
        if (allInputs.get(0).equalsIgnoreCase("insert")) {
            result.append(processInsertion(allInputs));
        } else if (allInputs.get(0).equalsIgnoreCase("remove")) {
            result.append(processRemove(allInputs));
        } else if (allInputs.get(0).equalsIgnoreCase("regionSearch")) {
            result.append(processRegionSearch(allInputs));
        } else if (allInputs.get(0).equalsIgnoreCase("intersection")) {
            result.append(processIntersections());
        } else if (allInputs.get(0).equalsIgnoreCase("search")) {
            result.append(processSearch(allInputs.get(1)));
        } else if (allInputs.get(0).equalsIgnoreCase("dump")) {
            result.append(processDump());
        }

        return result.toString();
    }

    /**
     * Validates inputs to the insert operation and performs insert if valid.
     *
     * @param operationInput The input values of the operation to be performed
     * @return result of the operation
     */
    private String processInsertion(List<String> operationInput) {
        //Regex match starting with letter,
        // then any character or digit or underscore
        if (!validateKey(operationInput.get(1)))
            return "Rectangle rejected: " +
                    getRectangleRepresentation(operationInput);

        int x = Integer.parseInt(operationInput.get(2)),
                y = Integer.parseInt(operationInput.get(3)),
                w = Integer.parseInt(operationInput.get(4)),
                h = Integer.parseInt(operationInput.get(5));

        //Reject if coordinates or height or width < 0
        if (x < 0 || y < 0 || w <= 0 || h <= 0
                || x + w > WorldBoxWidth || y + h > worldBoxHeight)
            return "Rectangle rejected: " +
                    getRectangleRepresentation(operationInput);

        data.insert(new KVPair<>(operationInput.get(1),
                new Rectangle(x, y, w, h)));

        return "Rectangle inserted: " +
                getRectangleRepresentation(operationInput);
    }


    /**
     * Validates inputs to the remove operation and performs remove if valid.
     *
     * @param operationInput The input values of the operation to be performed
     * @return result of the operation
     */
    private String processRemove(List<String> operationInput) {
        KVPair<String, Rectangle> pair; //Removed value

        if (operationInput.size() == 2) { //remove by key only
            //Regex match starting with letter,
            // then any character or digit or underscore
            if (!validateKey(operationInput.get(1)))
                return "Rectangle not found: " +
                        getRectangleRepresentation(operationInput);

            pair = data.remove(operationInput.get(1));
        } else { //remove by value
            int x = Integer.parseInt(operationInput.get(1)),
                    y = Integer.parseInt(operationInput.get(2)),
                    w = Integer.parseInt(operationInput.get(3)),
                    h = Integer.parseInt(operationInput.get(4));

            //Reject if coordinates or height or width < 0
            if (x < 0 || y < 0 || w <= 0 || h <= 0
                    || x + w > WorldBoxWidth || y + h > worldBoxHeight)
                return "Rectangle not found: " +
                        getRectangleRepresentation(operationInput);

            pair = data.remove(x, y, w, h);
        }

        return ((pair != null) ?
                "Rectangle removed: " : "Rectangle not found: ") +
                getRectangleRepresentation(operationInput);
    }


    /**
     * Validates inputs to the remove operation and performs remove if valid.
     *
     * @param operationInput The input values of the operation to be performed
     * @return result of the operation
     */
    private String processRegionSearch(List<String> operationInput) {
        StringBuilder sb = new StringBuilder("Rectangles intersecting region "
                + getRectangleRepresentation(operationInput) + ":\n");

        int x = Integer.parseInt(operationInput.get(1)),
                y = Integer.parseInt(operationInput.get(2)),
                w = Integer.parseInt(operationInput.get(3)),
                h = Integer.parseInt(operationInput.get(4));

        //Reject if height or width < 0
        if (w <= 0 || h <= 0)
            return "Rectangle rejected: " +
                    getRectangleRepresentation(operationInput);

        //No need to perform actual search if region outside world box
        if (x + w < 0 || y + h < 0)
            return sb.toString();

        //Adjust x, y to start from (0, 0)
        if (x < 0) {
            w = w + x;
            x = 0;
        }

        if (y < 0) {
            h = h + y;
            y = 0;
        }

        //Adjust width and height of region
        w = Math.min(w, WorldBoxWidth);
        h = Math.min(h, worldBoxHeight);

        List<KVPair<String, Rectangle>> rectangles =
                data.regionSearch(x, y, w, h);

        if (rectangles != null && rectangles.size() > 0) {
            for (KVPair<String, Rectangle> pair : rectangles)
                sb.append("(").append(pair).append(")\n");
        }

        return sb.toString();
    }


    /**
     * Validates inputs to the intersection operation and find
     * all pairs of rectangles that intersect.
     *
     * @return result of the operation
     */
    private String processIntersections() {
        StringBuilder output = new StringBuilder("Intersecting pairs:");

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>>
                rectangles = data.intersections();

        //print out intersections
        if (rectangles != null && rectangles.size() > 0) {
            for (Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>
                    pair : rectangles) {
                output.append("(").append(pair).append(")\n");
            }
        }

        return output.toString();
    }

    /**
     * Searches the key in the database and returns the list
     * of rectangles that match this key.
     *
     * @return result of the operation
     */
    private String processSearch(String key) {
        if (!validateKey(key))
            return "Rectangle not found: " + key;

        StringBuilder output = new StringBuilder();

        List<KVPair<String, Rectangle>> rectangles = data.search(key);

        //Print out rectangles found from search
        if (rectangles != null && rectangles.size() > 0) {
            for (KVPair<String, Rectangle> pair : rectangles)
                output.append("(").append(pair).append(")\n");

            return output.toString();
        } else
            return "Rectangle not found: " + key;
    }

    /**
     * Gives detail dump of the database
     *
     * @return result of the operation
     */
    private String processDump() {
        return data.dump();
    }


    /**
     * Returns the dump string for the input rectangle
     *
     * @param stringInputs The input values of the operation to be performed
     * @return string representing the input values in readable form
     */
    private String getRectangleRepresentation(List<String> stringInputs) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 1; i < stringInputs.size(); i++) {
            sb.append(stringInputs.get(i));
            if (i != stringInputs.size() - 1)
                sb.append(", ");
        }

        sb.append(")");

        return sb.toString();
    }

    private Boolean validateKey(String key) {
        return Pattern.compile("[a-zA-Z][\\w_]?").matcher(key).find();
    }

}
