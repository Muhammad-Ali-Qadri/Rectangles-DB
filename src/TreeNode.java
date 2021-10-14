import java.util.List;

/**
 * This interface represents the behavior of a tree node in a PRQuad Tree
 *
 * @param <K> the key for each point stored in this tree node
 * @author Muhammad Ali Qadri
 * @version 1
 */
public interface TreeNode<K extends Comparable<? super K>> {
    /**
     * Inserts the point into this node recursively. Following are the rules
     * associated with the insertion into a node:
     *
     * <p>1- If this node is an empty leaf, the point is inserted into
     * it.</p>
     * <p>2- If this node is a leaf with 3 other points, then this is
     * converted to an internal tree node, and all 4 points are now
     * inserted recursively into its subtree.</p>
     * <p>3- If this is an internal node, the point is inserted
     * recursively into its subtree.</p>
     *
     * @param pair  the pair that is to be inserted into this node
     * @param start the starting top left point of this nodes region
     * @param width the width of this region
     * @return The node in which insertion has been made. Can be either a new
     * internal or leaf node, or this.
     */
    TreeNode<K> insert(KVPair<K, Point> pair, Point start, int width);


    /**
     * Removes the point from this node. First the point is found and its key
     * and node is then removed recursively.
     *
     * @param point the point to remove from this node
     * @param start the starting top left point of this nodes region
     * @param width the width of this region
     * @return The node from which the value has been deleted. Null if value
     * was not found
     */
    TreeNode<K> removeByValue(Point point, Point start, int width);


    /**
     * Returns all key-value pairs that have duplicate points in this node.
     *
     * @return The list of pairs that are considered duplicates
     */
    List<Point> duplicates();


    /** Searches points within rectangle region specified in function
     * parameters
     * @param searchRect the rectangle within which points are to be searched
     * @param CurrentRegionStart the top left point of the current region
     * @param currentRegionWidth the width of the current region
     * @return  */
    List<Point> regionSearch(Rectangle searchRect, Point CurrentRegionStart,
                         int currentRegionWidth);


    /**
     * Returns the nodes tree representation in the form of a string
     *
     * @return string representing the internal structure of this node
     */
    String dump();
}
