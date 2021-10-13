import java.util.List;


/**
 * This implementation of a tree node represents a leaf node in the
 * PRQuadTree implementation.
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class LeafTreeNode implements TreeNode {

    //Represents the list of key-point pairs stored in this leaf node, with
    // their individual count
    private List<Pair<Integer, Point>> points;

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode insert(Point pair, Point start, int width) {
        return null;
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
