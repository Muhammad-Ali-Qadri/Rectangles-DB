package SkipLists;

/** This abstract class holds the functionality that is central
 * to all the concrete implementations of the Container class
 *
 * @param <K> Key
 * @param <V> Value
 * @author Muhammad Ali Qadri
 */
public abstract class AbstractContainer<K extends Comparable<? super K>, V> implements Container<K, V> {

    /**
    * {@inheritDoc}
    * */
    @Override
    public String toString() {
        return "AbstractContainer{" +
                "size=" + size() +
                '}';
    }
}
