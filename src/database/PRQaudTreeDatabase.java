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
     */
    @Override
    public Boolean insert(KVPair<String, Point> pair) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<String, Point> remove(String key) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<String, Point> removeByValue(Point value) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KVPair<String, Point>> regionSearch(int x, int y, int w, int h) {
        return null;
    }

    /**
     * {@inheritDoc}
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
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dump() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateV(Point value) {
        return null;
    }
}
