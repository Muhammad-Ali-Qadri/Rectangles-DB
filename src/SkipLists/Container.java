package SkipLists;

import java.util.ArrayList;

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
     */
    V search(K key);


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicographical order.
     *
     * @param it the KVPair to be inserted
     */
    void insert(KVPair<K, V> it);


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     *
     * @param key the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
    KVPair<K, V> remove(K key);


    /**
     * Removes a KVPair with the specified value.
     *
     * @param val the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    KVPair<K, V> removeByValue(V val);

    /**
     * Returns the current number of elements in the list
     *
     * @return returns a positive integer indicating the size of the list
     */
    int size();

    /**
     * Returns the current number of elements in the list
     * <p>Example:</p><code>[(K, V), (K, V).....]</code>
     *
     * @return returns a positive integer indicating the size of the list
     */
    String toString();
}
