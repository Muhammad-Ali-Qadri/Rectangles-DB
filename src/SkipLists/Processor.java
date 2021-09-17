package SkipLists;

/**
 * This is the interface for the processor that will be used
 * to process any input line
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */

public interface Processor {
    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionSearch, search, intersections, and dump. If the command in the
     * file line is not one of these, an appropriate message will be written
     * in the console. This processor method is called for each line in the
     * file. Note that the methods called will themselves write to the
     * console, this method does not, only calling methods that do.
     *
     * @param input a single line from the text file
     * @return The string output of the processing done
     * @throws IllegalArgumentException if input parameter is null
     */
    String process(String input);
}
