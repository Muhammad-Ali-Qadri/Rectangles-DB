package database;

import processor.Rectangle;
import quadtree.PRQTree;
import quadtree.Point;
import skiplist.KVPair;
import skiplist.Pair;
import skiplist.SkipList;

import java.util.List;

public class PRQaudTreeDatabase implements Database<String, Point>{

    private static final int WORLD_WIDTH = 1024;

    private final PRQTree<String> prqTree;
    private final SkipList<String, Point> skipList;

    public PRQaudTreeDatabase(){
        prqTree = new PRQTree<>(WORLD_WIDTH);
        skipList = new SkipList<>();
    }

    /**
     * {@inheritDoc}
     * Point Inserted: ({name}, {x}, {y})
     * Point rejected: ({name}, {x}, {y})
     */
    @Override
    public Boolean insert(KVPair<String, Point> pair) {
        List<KVPair<String, Point>> result = skipList.search(pair.getKey() );
        if (result != null) {
            for(KVPair<String, Point> item : result) {
                if (item.getValue() == pair.getValue() )
                    return false;
            }
        }
        insertionHelper(pair);
        return true;
    }

    /**
     * {@inheritDoc}
     * Point removed: ({name}, {x}, {y})
     * Point not removed: {name}
     */
    @Override
    public KVPair<String, Point> remove(String key) {
        KVPair<String, Point> item = skipList.remove(key);
        if (item != null) {
            prqTree.removeByValue(item.getValue());
            String printMe = "Point removed: (" + key + ", " + item + ") Removed";
            System.out.println(printMe);
        }
        else {
            System.out.println("Point not removed: " + key);
        }
        return item;
    }

    /**
     * {@inheritDoc}
     * Point rejected: ({x}, {y})
     * Point not found: ({x}, {y})
     */
    @Override
    public KVPair<String, Point> removeByValue(Point value) {
        KVPair<String, Point> item = prqTree.removeByValue(value);
        if (!validateV(value)) {
            String rejected = "Point rejected: (" + value + ")";
            System.out.println(rejected);
            return null;
        }

        if (item != null)
        {
            skipList.remove(item.getKey() );
            String found = "Point removed: (" + item + ")";
            System.out.println(found);
        }
        else{
            String notFound = "Point not found: (" + value + ")";
            System.out.println(notFound);
        }
        return item;
    }

    /**
     * {@inheritDoc}
     * Point found: ({name1}, {x1}, {y1})
     * Rectangle rejected: ({x}, {y}, {w}, {h})
     */
    @Override
    public List<KVPair<String, Point>> regionSearch(int x, int y, int w, int h) {
        Rectangle printMe = new Rectangle(x, y, w, h);
        if ( w < 1 || h < 1)
        {
            StringBuilder item = new StringBuilder("Rectangle rejected: (");
            item.append(printMe);
            item.append(")");
            System.out.println(item );
            return null;
        }
        List<KVPair<String, Point>> points = prqTree.regionSearch(printMe);
        StringBuilder regionHeader = new StringBuilder("Rectangles");
        regionHeader.append(" intersecting region (" );
        regionHeader.append(printMe );
        regionHeader.append( ")");
        System.out.println(regionHeader);

        for (KVPair<String, Point> point: points){
            regionHeader.setLength(0); // clear string builder
            String str = "Point found: "+ point.toString() + ")";
            regionHeader.append(str);
            System.out.println(regionHeader );
        }
        String nodeString = points.size()  + "quadtree nodes visited";
        System.out.println(nodeString);
        return points;
    }

    /**
     * {@inheritDoc}
     * This doesn't get used at all. I don't think.
     */
    @Override
    public List<Pair<KVPair<String, Point>, KVPair<String, Point>>>
    intersections() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KVPair<String, Point>> search(String key) {
        List<KVPair<String, Point>> items = skipList.search(key);
        if (items == null) {
            System.out.println("Point not found: " + key);
            return null;
        }

        for (KVPair<String, Point> item : items) {
            String printThis = "Found (" + item.toString() + ")";
            System.out.println(printThis);
        }

        return items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dump() {
        System.out.println("SkipList dump:");
        skipList.dump();
        System.out.println("QuadTree dump:");
        prqTree.dump();
        return null;
    }

    /**
     * {@inheritDoc}
     * returns true if valid, false if not a valid point
     */
    @Override
    public Boolean validateV(Point value) {
        return value.getX() < 1025 && value.getY() < 1025;
    }

    /**
     *
     * @param pair that's being validated for insertion.
     *             Might rename to validationHelper
     */
    public void insertionHelper(KVPair<String, Point> pair) {
        if (validateV(pair.getValue() ) ) {
            prqTree.insert(pair);
            skipList.insert(pair);
            String approved = "Point inserted: (" + pair + ")";
            System.out.println(approved);
        }
        else {
            String rejection = "Point rejected: (" + pair+ ")";
            System.out.println(rejection);
        }
    }

    /**
     * Will print the duplicates
     * @return A list containing duplicates
     */
    public List<Point> duplicates() {
        List<Point> dupList = prqTree.duplicates();
        System.out.println("Duplicate points:");
        for (Point point : dupList) {
            System.out.println("(" + point.toString() + ")" );
        }
        return dupList;
    }
}
