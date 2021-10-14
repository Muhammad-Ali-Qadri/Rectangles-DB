import java.util.List;


/**
 * This is a singleton implementation of a tree node represents an empty node
 * that is
 * used as a flyweight object in the PRQuadTree implementation.
 *
 * @param <K> the type of key stored in node
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class EmptyTreeNode<K extends Comparable<? super K>>
        implements TreeNode<K> {

    private static EmptyTreeNode<?> flyWeight;

    private EmptyTreeNode() {

    }

    /**
     * Returns the only Empty Node object in the entire application. It is
     * created whenever this method is first time called.
     *
     * @param <K1> the type of key in the empty node
     * @return The empty node singleton object.
     */
    @SuppressWarnings("unchecked")
    public static <K1 extends Comparable<? super K1>>
    EmptyTreeNode<K1> getInstance() {
        if (flyWeight == null) {
            flyWeight = new EmptyTreeNode<K1>();
        }

        return (EmptyTreeNode<K1>) flyWeight;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<K> insert(KVPair<K, Point> pair, Point start,
                              int width) {
        TreeNode<K> leaf = new LeafTreeNode<>();

        return leaf.insert(pair, start, width);
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
        treeStringBuilder.append("  ".repeat(level)).append("Node at ").append(start).append(", ")
                .append(width).append(": Empty").append("\n");
        return 1;
    }
}
