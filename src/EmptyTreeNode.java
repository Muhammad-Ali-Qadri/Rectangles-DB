import java.util.List;


/**
 * This is a singleton implementation of a tree node represents an empty node
 * that is
 * used as a flyweight object in the PRQuadTree implementation.
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class EmptyTreeNode implements TreeNode {

    private static EmptyTreeNode flyWeight;

    private EmptyTreeNode() {

    }

    /**
     * Returns the only Empty Node object in the entire application. It is
     * created whenever this method is first time called.
     *
     * @return The empty node singleton object.
     */
    public static EmptyTreeNode getInstance() {
        if (flyWeight == null) {
            flyWeight = new EmptyTreeNode();
        }

        return flyWeight;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode insert(Point pair, Point start, int width) {
        TreeNode leaf = new LeafTreeNode();

        return leaf.insert(pair, start, width);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode removeByValue(Point point, Point start, int width) {
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
    public List<Point> regionSearch(Point SearchRegionStart,
                                    int SearchRegionWidth,
                                    Point CurrentRegionStart,
                                    int currentRegionWidth) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dump() {
        return null;
    }
}
