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
        return insertionHelper(pair);
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
        if (item != null) {
            skipList.remove(item.getKey() );
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
        List<KVPair<String, Point>> points = prqTree.regionSearch(printMe);
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
        return items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dump() {
        StringBuilder concatMe = new StringBuilder();
        concatMe.append(skipList.dump());
        concatMe.append("\nQuadTree dump:\n");
        concatMe.append(prqTree.dump());
        return concatMe.toString();
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
    public boolean insertionHelper(KVPair<String, Point> pair) {
        if (validateV(pair.getValue() ) ) {
            prqTree.insert(pair);
            skipList.insert(pair);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> duplicates() {
        List<Point> dupList = prqTree.duplicates();
        return dupList;
    }
}
