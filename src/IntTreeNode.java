import java.util.List;

/**
 * This implementation of a tree node represents an internal node in the
 * PRQuadTree implementation.
 *
 * @param <K> the type of key stored in node
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class IntTreeNode<K extends Comparable<? super K>>
        implements TreeNode<K> {

    private TreeNode<K> nwChildRegion; //Represents the north-west leaf node
    private TreeNode<K> neChildRegion; //Represents the north-east leaf node
    private TreeNode<K> swChildRegion; //Represents the south-west leaf node
    private TreeNode<K> seChildRegion; //Represents the south-east leaf node

    public IntTreeNode() {
        nwChildRegion = EmptyTreeNode.getInstance();
        neChildRegion = EmptyTreeNode.getInstance();
        swChildRegion = EmptyTreeNode.getInstance();
        seChildRegion = EmptyTreeNode.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public TreeNode<K> insert(KVPair<K, Point> pair, Point start, int width) {

        Point point = pair.getValue();

        //Add to north region
        if (point.getY() >= start.getY()
            && point.getY() <= start.getY() + width / 2) {
            //add to north-west region
            if (point.getX() >= start.getX()
                && point.getX() <= start.getX() + width / 2) {
                nwChildRegion = nwChildRegion.insert(pair, start, width / 2);
            }
            else { //add to north-east region
                neChildRegion = neChildRegion.insert(pair,
                        new Point(((width / 2) + 1), start.getY()),
                        (width / 2) - 1);
            }
        }//Add to south region
        else {
            //add to south-west region
            if (point.getX() >= start.getX()
                && point.getX() <= start.getX() + width / 2) {
                swChildRegion = swChildRegion.insert(pair,
                        new Point(start.getX(), (width / 2) + 1),
                        (width / 2) - 1);
            }
            else { //add to south-east region
                seChildRegion = seChildRegion.insert(pair,
                        new Point((width / 2) + 1, (width / 2) + 1),
                        (width / 2) - 1);
            }
        }

        return this;
    }


    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public TreeNode<K> removeByValue(Point point, Point start, int width) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<Point> duplicates() {
        return null;
    }


    /**
     * {@inheritDoc}
     *
     * @return
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
    public String dump() {
        return null;
    }
}
