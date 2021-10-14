package database;

import processor.Rectangle;
import skiplist.KVPair;
import skiplist.Pair;
import skiplist.SkipList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
 * @version 1
 */
public class SkipListDatabase implements Database {

    //Size of the world box
    private static final int WORLD_BOX_WIDTH = 1024;
    private static final int WORLD_BOX_HEIGHT = 1024;

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
    public Boolean insert(KVPair<String, Rectangle> pair) {
        if (pair == null) {
            throw new IllegalArgumentException();
        }

        //Reject if invalid key
        //Reject if coordinates or height or width < 0
        if ((!validateKey(pair.getKey()))
            || !validateRectangle(pair.getValue())) {
            return false;
        }

        list.insert(pair);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<String, Rectangle> remove(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }

        if (!validateKey(name)) {
            return null;
        }

        return list.remove(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<String, Rectangle> removeByValue(Rectangle rectangle) {
        if (rectangle == null) {
            throw new IllegalArgumentException();
        }

        if (list.isEmpty() || !validateRectangle(rectangle)) {
            return null;
        }

        return list.removeByValue(rectangle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KVPair<String, Rectangle>> regionSearch(int x, int y
            , int w, int h) {

        //Reject if height or width < 0
        if (w <= 0 || h <= 0) {
            return null;
        }

        //No need to perform actual search if region outside world box
        if (x + w < 0 || y + h < 0) {
            return new ArrayList<>();
        }

        //Adjust x, y, width and height of region
        if (x < 0) {
            w = w + x;
            x = 0;
        }

        if (y < 0) {
            h = h + y;
            y = 0;
        }

        w = Math.min(w, WORLD_BOX_WIDTH);
        h = Math.min(h, WORLD_BOX_HEIGHT);

        Rectangle region = new Rectangle(x, y, w, h);
        List<KVPair<String, Rectangle>> rectangles = new ArrayList<>();

        //Find the rectangles that intersect or are contained within the region
        for (KVPair<String, Rectangle> pair : list) {
            if (region.intersects(pair.getValue())
                || pair.getValue().intersects(region)
                || region.contains(pair.getValue())) {
                rectangles.add(pair);
            }
        }

        return rectangles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<KVPair<String, Rectangle>,
            KVPair<String, Rectangle>>> intersections() {

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>>
                intersectingPairs = new ArrayList<>();

        //Loop through each rectangle and compare with each other
        for (KVPair<String, Rectangle> outer : list) {
            for (KVPair<String, Rectangle> inner : list) {
                //If rectangle not compared with itself, and rectangles
                // intersect each-other, and rectangles are not nested within
                // each-other then true, otherwise false.
                if (outer.getValue() != inner.getValue()
                    && ((outer.getValue().intersects(inner.getValue())
                         || inner.getValue().intersects(outer.getValue())))) {
                    intersectingPairs.add(new Pair<>(outer, inner));
                }
            }
        }

        return intersectingPairs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KVPair<String, Rectangle>> search(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }

        if (!validateKey(name)) {
            return null;
        }

        return list.search(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dump() {
        return list.dump();
    }

    /**
     * Checks if the input is a valid key for SkipLists Database.
     *
     * @param key the possible key to check
     * @return boolean value of validation result
     */
    private Boolean validateKey(String key) {
        //Regex match starting with letter,
        // then any character or digit or underscore
        return Pattern.compile("^[a-zA-Z][\\w_]*$").matcher(key).find();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateRectangle(Rectangle rectangle) {
        return (rectangle != null)
               && !(rectangle.x < 0 || rectangle.y < 0
                    || rectangle.width <= 0 || rectangle.height <= 0
                    || rectangle.x + rectangle.width > WORLD_BOX_WIDTH
                    || rectangle.y + rectangle.height > WORLD_BOX_HEIGHT);
    }
}
