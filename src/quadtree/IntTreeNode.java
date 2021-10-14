package quadtree;

import processor.Rectangle;
import skiplist.KVPair;

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
     */
    @Override
    public TreeNode<K> insert(KVPair<K, Point> pair, Point start, int width) {
        Point point = pair.getValue();
        int regionWidth = (width / 2);

        //Add to north region
        if (point.getY() >= start.getY()
            && point.getY() < start.getY() + (width / 2)) {
            //add to north-west region
            if (point.getX() >= start.getX()
                && point.getX() < start.getX() + (width / 2)) {
                nwChildRegion = nwChildRegion.insert(pair, start, regionWidth);
            }
            else { //add to north-east region
                neChildRegion = neChildRegion.insert(pair,
                        new Point(width / 2, start.getY()), regionWidth);
            }
        }//Add to south region
        else {
            //add to south-west region
            if (point.getX() >= start.getX()
                && point.getX() < start.getX() + (width / 2)) {
                swChildRegion = swChildRegion.insert(pair,
                        new Point(start.getX(), width / 2), regionWidth);
            }
            else { //add to south-east region
                seChildRegion = seChildRegion.insert(pair,
                        new Point(width / 2, width / 2), regionWidth);
            }
        }

        return this;
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

        treeStringBuilder.append("  ".repeat(level)).append("Node at ").
                append(start).append(", ").append(width).append(": Internal\n");

        return 1 + nwChildRegion.dump(level + 1, start, width / 2,
                treeStringBuilder)
               + neChildRegion.dump(level + 1, new Point((width / 2),
                        start.getY()), width / 2, treeStringBuilder)
               + swChildRegion.dump(level + 1, new Point(start.getX(),
                        width / 2), width / 2, treeStringBuilder)
               + seChildRegion.dump(level + 1,
                new Point(width / 2, width / 2),
                width / 2, treeStringBuilder);
    }
}
