import java.util.Iterator;

/**
 * This abstract class holds the functionality that is central
 * to all the concrete implementations of the Container class
 *
 * @param <K> Key
 * @param <V> Value
 * @author Muhammad Ali Qadri
 */
public abstract class AbstractContainer<K extends Comparable<? super K>, V>
        implements Container<K, V> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        Iterator<KVPair<K, V>> it = iterator();

        while (it.hasNext()) {
            sb.append("(").append(it.next().toString()).append(")");

            if (it.hasNext())
                sb.append(", ");
        }

        sb.append("]:");
        sb.append(size());

        return sb.toString();
    }
}
