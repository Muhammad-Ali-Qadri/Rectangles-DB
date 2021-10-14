import java.util.ArrayList;
import java.util.List;


/**
 * This implementation of a tree node represents a leaf node in the
 * PRQuadTree implementation.
 *
 * @param <K> the type of key stored in node
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class LeafTreeNode<K extends Comparable<? super K>>
        implements TreeNode<K> {

    //Represents the list of key-point pairs stored in this leaf node
    private final List<KVPair<K, Point>> pairs;

    public LeafTreeNode() {
        pairs = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<K> insert(KVPair<K, Point> pair, Point start,
                              int width) {
        //TODO: add find all method to the skiplist class

        boolean foundPoint = false;

        for (KVPair<K, Point> listPoint : pairs) {
            if (pair.getValue().equals(listPoint.getValue())) {
                foundPoint = true;
                break;
            }
        }

        pairs.add(pair);

        if (foundPoint || pairs.size() <= 3) {
            return this;
        }
        else {
            TreeNode<K> internal = new IntTreeNode<>();

            for (KVPair<K, Point> listPoint : pairs) {
                internal = internal.insert(listPoint, start, width);
            }

            return internal;
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<K> removeByValue(Point point, Point start, int width) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> duplicates() {
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> regionSearch(Rectangle searchRect,
                                    Point CurrentRegionStart,
                                    int currentRegionWidth) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int dump(int level, Point start, int width,
                    StringBuilder treeStringBuilder) {
        treeStringBuilder.append("  ".repeat(level)).append("Node at ")
                .append(start).append(", ").append(width).append(":\n");

        for (KVPair<K, Point> pair : pairs) {
            treeStringBuilder.append("  ".repeat(level)).
                    append("(").append(pair).append(")\n");
        }

        return 1;
    }
}
