package processor;

import database.Database;
import database.SkipListDatabase;
import skiplist.KVPair;
import skiplist.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class RectangleCommandProcessor implements Processor {

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
        if (input == null) {
            throw new IllegalArgumentException();
        }
        //stores all the words form input
        List<String> allInputs = new ArrayList<>();
        //finds all the words in the input statement
        Matcher matcher = Pattern.compile("[\\S]+").matcher(input);

        while (matcher.find()) {
            allInputs.add(matcher.group());
        }

        StringBuilder result = new StringBuilder();

        //allInputs.get(0) is the command to operate
        if (allInputs.get(0).equalsIgnoreCase("insert")) {
            result.append(processInsertion(allInputs));
        }
        else if (allInputs.get(0).equalsIgnoreCase("remove")) {
            result.append(processRemove(allInputs));
        }
        else if (allInputs.get(0).equalsIgnoreCase("regionSearch")) {
            result.append(processRegionSearch(allInputs));
        }
        else if (allInputs.get(0).equalsIgnoreCase("intersections")) {
            result.append(processIntersections());
        }
        else if (allInputs.get(0).equalsIgnoreCase("search")) {
            result.append(processSearch(allInputs.get(1)));
        }
        else if (allInputs.get(0).equalsIgnoreCase("dump")) {
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
        //Parse all input values
        int x = Integer.parseInt(operationInput.get(2));
        int y = Integer.parseInt(operationInput.get(3));
        int w = Integer.parseInt(operationInput.get(4));
        int h = Integer.parseInt(operationInput.get(5));

        KVPair<String, Rectangle> pair = new KVPair<>(operationInput.get(1),
                new Rectangle(x, y, w, h));

        //return message in either case of failure or success
        return ((data.insert(pair)) ? "Rectangle inserted: (" :
                "Rectangle rejected: (") + pair + ")";
    }


    /**
     * Validates inputs to the remove operation and performs remove if valid.
     *
     * @param operationInput The input values of the operation to be performed
     * @return result of the operation
     */
    private String processRemove(List<String> operationInput) {
        KVPair<String, Rectangle> pair; //Removed value
        Boolean result = false;

        if (operationInput.size() == 2) { //remove by key only
            pair = data.remove(operationInput.get(1));
        }
        else { //remove by value
            int x = Integer.parseInt(operationInput.get(1));
            int y = Integer.parseInt(operationInput.get(2));
            int w = Integer.parseInt(operationInput.get(3));
            int h = Integer.parseInt(operationInput.get(4));

            Rectangle rect = new Rectangle(x, y, w, h);
            if (!data.validateRectangle(rect)) {
                return "Rectangle rejected: " +
                       getRectangleRepresentation(operationInput);
            }

            pair = data.removeByValue(rect);
        }

        return ((pair != null) ?
                "Rectangle removed: (" + pair + ")" :
                "Rectangle not removed: " +
                getRectangleRepresentation(operationInput));
    }


    /**
     * Validates inputs to the remove operation and performs remove if valid.
     *
     * @param operationInput The input values of the operation to be performed
     * @return result of the operation
     */
    private String processRegionSearch(List<String> operationInput) {
        int x = Integer.parseInt(operationInput.get(1));
        int y = Integer.parseInt(operationInput.get(2));
        int w = Integer.parseInt(operationInput.get(3));
        int h = Integer.parseInt(operationInput.get(4));

        List<KVPair<String, Rectangle>> rectangles =
                data.regionSearch(x, y, w, h);

        if (rectangles == null) {
            return "Rectangle rejected: " +
                   getRectangleRepresentation(operationInput);
        }
        else {
            StringBuilder sb =
                    new StringBuilder("Rectangles intersecting region ");
            sb.append(getRectangleRepresentation(operationInput)).append(":");

            if (rectangles.size() > 0) {
                sb.append("\n");

                String prefix = "";
                for (KVPair<String, Rectangle> pair : rectangles) {
                    sb.append(prefix);
                    prefix = "\n";
                    sb.append("(").append(pair).append(")");
                }
            }

            return sb.toString();
        }
    }


    /**
     * Validates inputs to the intersection operation and find
     * all pairs of rectangles that intersect.
     *
     * @return result of the operation
     */
    private String processIntersections() {
        StringBuilder output = new StringBuilder("Intersections pairs:");

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>>
                rectangles = data.intersections();

        //print out intersections
        if (rectangles != null) {
            String prefix = "\n";
            for (Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>
                    pair : rectangles) {
                output.append(prefix);
                prefix = "\n";
                output.append("(").append(pair).append(")");
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
        StringBuilder output = new StringBuilder();

        List<KVPair<String, Rectangle>> rectangles = data.search(key);

        //Print out rectangles found from search
        if (rectangles != null) {
            output.append("Rectangles found:\n");
            String prefix = "";
            for (KVPair<String, Rectangle> pair : rectangles) {
                output.append(prefix);
                prefix = "\n";
                output.append("(").append(pair).append(")");
            }

            return output.toString();
        }
        else {
            return "Rectangle not found: " + key;
        }
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
            if (i != stringInputs.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append(")");

        return sb.toString();
    }
}
