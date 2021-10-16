package quadtree;

import processor.Rectangle;
import skiplist.KVPair;

import java.util.*;
import java.util.stream.Collectors;

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
        if (pointInNorthRegion(start, width, point)) {
            //add to north-west region
            if (pointInWestRegion(start, width, point)) {
                nwChildRegion = nwChildRegion.insert(pair, start, regionWidth);
            }
            else { //add to north-east region
                neChildRegion = neChildRegion.insert(pair,
                        new Point(width / 2, start.getY()), regionWidth);
            }
        }//Add to south region
        else {
            //add to south-west region
            if (pointInWestRegion(start, width, point)) {
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
    public TreeNode<K> removeByValue(Point point, Point start, int width, StringBuilder key) {
        int regionWidth = (width / 2);

        //remove from north region
        if (pointInNorthRegion(start, width, point)) {
            //remove from north-west region
            if (pointInWestRegion(start, width, point)) {
                nwChildRegion = nwChildRegion.removeByValue(point, start,
                        regionWidth, key);
            }
            else { //remove from north-east region
                neChildRegion = neChildRegion.removeByValue(point,
                        new Point(width / 2, start.getY()), regionWidth, key);
            }
        }//remove from south region
        else {
            //remove from south-west region
            if (pointInWestRegion(start, width, point)) {
                swChildRegion = swChildRegion.removeByValue(point,
                        new Point(start.getX(), width / 2), regionWidth, key);
            }
            else { //add to south-east region
                seChildRegion = seChildRegion.removeByValue(point,
                        new Point(width / 2, width / 2), regionWidth, key);
            }
        }

        return reduceNode(start, width);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> duplicates() {
        List<Point> duplicates = new ArrayList<>();
        duplicates.addAll(nwChildRegion.duplicates());
        duplicates.addAll(neChildRegion.duplicates());
        duplicates.addAll(swChildRegion.duplicates());
        duplicates.addAll(seChildRegion.duplicates());

        return duplicates;
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
    public List<KVPair<K, Point>> getKeyValuePairs(){
        return new ArrayList<>();
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


    private boolean pointInWestRegion(Point start, int width, Point point) {
        return point.getX() >= start.getX()
               && point.getX() < start.getX() + (width / 2);
    }

    private boolean pointInNorthRegion(Point start, int width, Point point) {
        return point.getY() >= start.getY()
               && point.getY() < start.getY() + (width / 2);
    }

    private TreeNode<K> reduceNode(Point start, int width) {
        //Get all points in direct children
        List<KVPair<K, Point>> internalPoints = new ArrayList<>();
        internalPoints.addAll(nwChildRegion.getKeyValuePairs());
        internalPoints.addAll(neChildRegion.getKeyValuePairs());
        internalPoints.addAll(swChildRegion.getKeyValuePairs());
        internalPoints.addAll(seChildRegion.getKeyValuePairs());

        //If children are empty, then reduce to an empty node
        if(internalPoints.size() == 0){
            return EmptyTreeNode.getInstance();
        }

        List<Point> distinct =
                internalPoints.stream().map(KVPair::getValue).distinct().
                        collect(Collectors.toList());

        //if unique points count <= 3, reduce this node to a leaf
        if(distinct.size() <= 3){
            TreeNode<K> leaf = new LeafTreeNode<>();
            for (KVPair<K, Point> listPoint : internalPoints) {
                leaf = leaf.insert(listPoint, start, width);
            }

            return leaf;
        }

        return this;
    }
}
