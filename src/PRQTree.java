import java.util.List;

/**
 * This class represents the complete functionality for the PRQuad Tree
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class PRQTree {

    private TreeNode root;

    private final int regionLength;


    /**
     * The constructor for this tree, initializes root and width and height
     *
     * @param width the width of the region encapsulated in this tree
     */
    public PRQTree(int width) {

        if (width < 1) {
            throw new IllegalArgumentException();
        }

        root = EmptyTreeNode.getInstance();
        regionLength = width;
    }


    /**
     * Inserts the point into this tree.
     *
     * @param pair the point that is to be inserted into this tree
     */
    public void insert(Point pair) {
        root = root.insert(pair,
                new Point(0, 0), regionLength);
    }


    /**
     * Removes the point from this tree.
     *
     * @param point the point to remove from this tree
     *              was not found
     */
    public void removeByValue(Point point) {
        root = root.removeByValue(point,
                new Point(0, 0), regionLength);
    }


    /**
     * Returns all points that have duplicates in this tree.
     *
     * @return The list of points that are considered duplicates
     */
    public List<Point> duplicates() {
        return root.duplicates();
    }


    /**
     * Searches points within rectangle region specified in function
     * parameters
     *
     * @param start the top left point of the rectangle
     * @param width the width of the rectangle
     */
    List<Point> regionSearch(Point start, int width) {
        return root.regionSearch(start, width,
                new Point(0, 0), regionLength);
    }


    /**
     * Returns the tree representation in the form of a string
     *
     * @return string representing the internal structure of this tree
     */
    public String dump() {
        return root.dump();
    }

}
