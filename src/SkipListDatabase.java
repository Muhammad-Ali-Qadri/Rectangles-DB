import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 *
 * @author Muhammad Ali Qadri
 */
public class SkipListDatabase implements Database {
    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private final SkipList<String, Rectangle> list;

    /**
     * Constructor for Database class, will be responsible
     * for initializing the SkipList.
     */
    public SkipListDatabase() {
        list = new SkipList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(KVPair<String, Rectangle> pair) {
        list.insert(pair);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<String, Rectangle> remove(String name) {
        return list.remove(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<String, Rectangle> remove(int x, int y, int w, int h) {
        return list.removeByValue(new Rectangle(x, y, w, h));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KVPair<String, Rectangle>> regionSearch(int x, int y
            , int w, int h) {
        Rectangle region = new Rectangle(x, y, w, h);
        List<KVPair<String, Rectangle>> rectangles = new ArrayList<>();

        for (KVPair<String, Rectangle> pair : list) {
            if (region.intersects(pair.getValue())
                    || region.contains(pair.getValue()))
                rectangles.add(pair);
        }

        return rectangles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>>
    intersections() {

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>>
                intersectingPairs = new ArrayList<>();

        for (KVPair<String, Rectangle> outer : list) {
            for (KVPair<String, Rectangle> inner : list) {
                if (!outer.equals(inner) &&
                        outer.getValue().intersects(inner.getValue()))
                    intersectingPairs.add(new Pair<>(outer, inner));
            }
        }

        return intersectingPairs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KVPair<String, Rectangle>> search(String name) {
        return list.search(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dump() {
        return list.dump();
    }
}
