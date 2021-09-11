package SkipLists;

import java.util.ArrayList;
import java.util.List;

/** This interface holds the generic functionality a container should have
 * for a key-value based container. The basic functionality includes
 * the functions present in this class. The container also should be iterable.
 *
 * @param <K> Key
 * @param <V> Value
 * @author Muhammad Ali Qadri
 */
public interface Container<K extends Comparable<? super K>, V> extends Iterable<KVPair<K, V>> {

    /**
     * Searches for the value using the key which is a Comparable object.
     *
     * @param key key to be searched for
     * @return the list of items that have the key
     * @throws IllegalArgumentException @param cannot be null
     */
    List<KVPair<K, V>> search(K key);

    /**
     * Gets the first element in the list.
     *
     * @return The first element in the list
     */
    KVPair<K, V> first();


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicographical order.
     *
     * @param it the KVPair to be inserted
     * @throws IllegalArgumentException @param cannot be null
     */
    void insert(KVPair<K, V> it);


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     *
     * @param key the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     * @throws IllegalArgumentException @param cannot be null
     */
    KVPair<K, V> remove(K key);


    /**
     * Removes a KVPair with the specified value.
     *
     * @param val the value of the KVPair to be removed
     * @return returns true if the removal was successful
     * @throws IllegalArgumentException @param cannot be null
     */
    KVPair<K, V> removeByValue(V val);

    /**
     * Returns the current number of elements in the list
     *
     * @return returns a positive integer indicating the size of the list
     */
    int size();

    /**Checks if the current container is empty
     *
     * */
    boolean isEmpty();

    /**
     * Returns the current number of elements in the list
     * <p>Example:</p><code>[(K, V), (K, V).....]</code>
     *
     * @return returns a positive integer indicating the size of the list
     */
    String toString();

    /** Provides the detailed internal element details of this object
     *
     * @return The string containing human-readable details of this
     * container.
     * */
    String Dump();
}
