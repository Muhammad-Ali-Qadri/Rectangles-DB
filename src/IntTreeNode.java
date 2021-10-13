import java.util.List;


/**
 * This implementation of a tree node represents an internal node in the
 * PRQuadTree implementation.
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class IntTreeNode implements TreeNode {

    //Represents the flyweight static node for empty nodes
    private static final TreeNode EMPTY_NODE = EmptyTreeNode.getInstance();

    private TreeNode nwChildRegion; //Represents the north-west leaf node
    private TreeNode neChildRegion; //Represents the north-east leaf node
    private TreeNode swChildRegion; //Represents the south-west leaf node
    private TreeNode seChildRegion; //Represents the south-east leaf node

    public IntTreeNode(){
        nwChildRegion = EmptyTreeNode.getInstance();
        neChildRegion = EmptyTreeNode.getInstance();
        swChildRegion = EmptyTreeNode.getInstance();
        seChildRegion = EmptyTreeNode.getInstance();
    }

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
