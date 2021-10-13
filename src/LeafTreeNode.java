import java.util.ArrayList;
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
    private final List<Point> points;

    public LeafTreeNode(){
        points = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode insert(Point pair, Point start, int width) {
        //TODO: add find all method to the skiplist class

        boolean foundPoint = false;

        for(Point listPoint: points){
            if(pair.equals(listPoint)){
                foundPoint = true;
                break;
            }
        }

        points.add(pair);

        if(foundPoint || points.size() < 3){
            return this;
        }
        else{
            TreeNode internal = new IntTreeNode();

            for(Point listPoint: points){
                internal = internal.insert(listPoint, start, width);
            }

            return internal;
        }
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
