package quadtree;

import processor.Rectangle;
import skiplist.KVPair;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the complete functionality for the PRQuad Tree
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class PRQTree {

    private TreeNode<String> root;
    private final Point rootStart;
    private final int rootLength;


    /**
     * The constructor for this tree, initializes root and width and height
     *
     * @param width the width of the region encapsulated in this tree
     */
    public PRQTree(int width) {

        if (width < 1) {
            throw new IllegalArgumentException();
        }

        rootStart = new Point(0, 0);
        root = EmptyTreeNode.getInstance();
        rootLength = width;
    }


    /**
     * Inserts the point into this tree.
     *
     * @param pair the point that is to be inserted into this tree
     */
    public void insert(KVPair<String, Point> pair) {
        root = root.insert(pair, rootStart, rootLength);
    }


    /**
     * Removes the point from this tree.
     *
     * @param point the point to remove from this tree
     *              was not found
     */
    public KVPair<String, Point> removeByValue(Point point) {
        StringBuilder sb = new StringBuilder();
        root = root.removeByValue(point, rootStart, rootLength, sb);

        return new KVPair<>(sb.toString(), point);
    }


    /**
     * Returns all points that have duplicates in this tree.
     *
     * @return The list of points that are considered duplicates
     */
    public List<Point> duplicates() {
        List<Point> duplicates = new ArrayList<>();
        root.duplicates(duplicates);

        return duplicates;
    }


    /**
     * Searches points within rectangle region specified in function
     * parameters
     *
     * @param rect rectangle to search
     */
    public List<KVPair<String, Point>> regionSearch(Rectangle rect) {
        List<KVPair<String, Point>> search = new ArrayList<>();
        root.regionSearch(rect, rootStart, rootLength, search);

        return search;
    }


    /**
     * Returns the tree representation in the form of a string
     *
     * @return string representing the internal structure of this tree
     */
    public String dump() {
        StringBuilder sb = new StringBuilder();
        int nodes = root.dump(0, rootStart, rootLength, sb);

        sb.append("QuadTree Size: ").append(nodes)
                .append(" QuadTree Nodes Printed.\n");

        return sb.toString();
    }

}