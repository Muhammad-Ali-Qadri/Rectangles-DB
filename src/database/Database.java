package database;

import skiplist.KVPair;
import skiplist.Pair;

import java.util.List;

/**
 * This class is responsible for interfacing between the command processor and
 * the internal data-structure.
 * The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * internal data-structure method after some preparation.
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public interface Database<K extends Comparable<? super K>, V> {

    /**
     * Inserts the KVPair in the internal data-structure if
     * the value is valid. This insert will insert the KVPair specified into
     * the sorted internal data-structure appropriately
     *
     * @param pair the KVPair to be inserted
     * @return True if successful insert
     */
    Boolean insert(KVPair<K, V> pair);


    /**
     * Removes a value with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param key the name of the value to be removed
     * @return pair that is removed.
     */
    KVPair<K, V> remove(K key);


    /**
     * Removes a value with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param value the value to remove from this object
     * @return pair that is removed.
     */
    KVPair<K, V> removeByValue(V value);


    /**
     * Displays all the values inside the specified region. The value
     * must have some area inside the area that is created by the region,
     * meaning, Vs that only touch a side or corner of the region
     * specified will not be said to be in the region.
     *
     * @param x x-Coordinate of the region
     * @param y y-Coordinate of the region
     * @param w width of the region
     * @param h height of the region
     * @return the list of values that are within the region
     */
    List<KVPair<K, V>> regionSearch(int x, int y, int w, int h);


    /**
     * Prints out all the values that Intersect each other by calling the
     * internal data-structure method for intersections.
     *
     * @return The rectangle pairs that intersect
     */
    List<Pair<KVPair<K, V>,
                KVPair<K, V>>> intersections();


    /**
     * Prints out all the values with the specified
     * name in the internal data-structure.
     * This method will delegate the searching to the internal data-structure
     * class completely.
     *
     * @param key name of the V to be searched for
     * @return list of items that have the name as keu
     */
    List<KVPair<K, V>> search(K key);

    /**
     * Returns dump of the internal data-structure which includes information
     * about the size of the internal data-structure and shows all contents
     * of the internal data-structure. This
     * will all be delegated to the internal data-structure.
     *
     * @return K representation of this databases current state
     */
    String dump();


    /**
     * Checks if the input is a valid value for internal data-structures
     * Database.
     *
     * @param value the possible value to check
     * @return boolean value of validation result
     */
    Boolean validateV(V value);
}
