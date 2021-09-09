package SkipLists;

import java.awt.Rectangle;

public class SkipListDatabase implements Database{
    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private final SkipList<String, Rectangle> list;

    /**
     * {@inheritDoc}
     */
    public SkipListDatabase() {
        list = new SkipList<>();
    }

    /**
     * {@inheritDoc}
     */
    public void insert(KVPair<String, Rectangle> pair) {

    }

    /**
     * {@inheritDoc}
     */
    public void remove(String name) {

    }

    /**
     * {@inheritDoc}
     */
    public void remove(int x, int y, int w, int h) {

    }

    /**
     * {@inheritDoc}
     */
    public void regionSearch(int x, int y, int w, int h) {

    }

    /**
     * {@inheritDoc}
     */
    public void intersections() {

    }

    /**
     * {@inheritDoc}
     */
    public void search(String name) {

    }

    /**
     * {@inheritDoc}
     */
    public void dump() {
        list.toString();
    }
}
