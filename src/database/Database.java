package database;

import processor.Rectangle;
import skiplist.KVPair;
import skiplist.Pair;

import java.util.List;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList.
 * The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public interface Database {

    /**
     * Inserts the KVPair in the SkipList if
     * the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList
     * appropriately
     *
     * @param pair the KVPair to be inserted
     * @return True if successful insert
     */
    Boolean insert(KVPair<String, Rectangle> pair);


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param name the name of the rectangle to be removed
     * @return pair that is removed.
     */
    KVPair<String, Rectangle> remove(String name);


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param rectangle the rectangle to remove from this object
     * @return pair that is removed.
     */
    KVPair<String, Rectangle> removeByValue(Rectangle rectangle);


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
     *
     * @param x x-Coordinate of the region
     * @param y y-Coordinate of the region
     * @param w width of the region
     * @param h height of the region
     * @return the list of rectangles that are within the region
     */
    List<KVPair<String, Rectangle>> regionSearch(int x, int y, int w, int h);


    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections.
     *
     * @return The rectangle pairs that intersect
     */
    List<Pair<KVPair<String, Rectangle>,
                KVPair<String, Rectangle>>> intersections();


    /**
     * Prints out all the rectangles with the specified
     * name in the SkipList.
     * This method will delegate the searching to the SkipList
     * class completely.
     *
     * @param name name of the Rectangle to be searched for
     * @return list of items that have the name as keu
     */
    List<KVPair<String, Rectangle>> search(String name);

    /**
     * Returns dump of the SkipList which includes information
     * about the size of the SkipList and shows all of the contents
     * of the SkipList. This
     * will all be delegated to the SkipList.
     *
     * @return string representation of this databases current state
     */
    String dump();


    /**
     * Checks if the input is a valid rectangle for SkipLists Database.
     *
     * @param rectangle the possible rectangle to check
     * @return boolean value of validation result
     */
    Boolean validateRectangle(Rectangle rectangle);
}
