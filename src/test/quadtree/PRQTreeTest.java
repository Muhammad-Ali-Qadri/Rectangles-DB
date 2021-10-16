package test.quadtree;

import quadtree.PRQTree;
import quadtree.Point;
import org.junit.Before;
import org.junit.Test;
import skiplist.KVPair;
import skiplist.Pair;

import static org.junit.Assert.*;

/**
 * This is a test class responsible for testing the operations of an
 * leaf tree node
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class PRQTreeTest {

    private static final int WORLD_WIDTH = 1024;
    private static final Point ROOT_START = new Point(0, 0);

    //The empty node to test on
    private PRQTree prqTree;


    /**
     * Setup test
     */
    @Before
    public void setUp() {
        prqTree = new PRQTree(WORLD_WIDTH);
    }


    /**
     * Test the Empty Dump
     */
    @Test
    public void testEmptyDump() {

        String dumpString = "Node at 0, 0, 1024: Empty\n" +
                            "QuadTree Size: 1 QuadTree Nodes Printed.\n";

        assertEquals(dumpString, prqTree.dump());
    }

    /**
     * Test the dump and insert on node with points (P1, 0, 0), (P1, 0, 1)
     */
    @Test
    public void testMultiLeafInsertDump() {

        prqTree.insert(new KVPair<>("P1", new Point(0, 0)));
        prqTree.insert(new KVPair<>("P1", new Point(0, 1)));

        String dumpString = "Node at 0, 0, 1024:\n" +
                            "(P1, 0, 0)\n" +
                            "(P1, 0, 1)\n" +
                            "QuadTree Size: 1 QuadTree Nodes Printed.\n";

        assertEquals(dumpString, prqTree.dump());
    }


    /**
     * Test the dump and insert on node with points (P1, 0, 0), (P1, 512, 0),
     * (P1, 0, 512), (P1, 512, 512)
     */
    @Test
    public void testMultiInsertDump() {

        prqTree.insert(new KVPair<>("P1", new Point(0, 0)));
        prqTree.insert(new KVPair<>("P1", new Point(512, 0)));
        prqTree.insert(new KVPair<>("P1", new Point(0, 512)));
        prqTree.insert(new KVPair<>("P1", new Point(512, 512)));

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P1, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P1, 0, 512)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P1, 512, 512)\n" +
                            "QuadTree Size: 5 QuadTree Nodes Printed.\n";

        assertEquals(dumpString, prqTree.dump());
    }

    /**
     * Test the remove and dump on point (P1, 0, 0)
     */
    @Test
    public void testLeafRemoveByValueDump() {

        prqTree.insert(new KVPair<>("P1", new Point(0, 0)));

        KVPair<String, Point> pair = prqTree.removeByValue(new Point(0, 0));

        assertEquals(new KVPair<>("P1", new Point(0, 0)), pair);

        testEmptyDump();
    }

    /**
     * Test the remove and dump on point (P1, 0, 0), (P1, 0, 1), (P1, 512, 0),
     * (P1, 0, 512), (P1, 512, 512). Remove (P1, 512, 512), (P1, 0, 1)
     */
    @Test
    public void testMultiRemoveByValueDump() {

        testMultiInsertDump();

        prqTree.insert(new KVPair<>("P1", new Point(0, 1)));

        KVPair<String, Point> pair = prqTree.removeByValue(new Point(0, 1));
        assertEquals(new KVPair<>("P1", new Point(0, 1)), pair);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P1, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P1, 0, 512)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P1, 512, 512)\n" +
                            "QuadTree Size: 5 QuadTree Nodes Printed.\n";

        assertEquals(dumpString, prqTree.dump());

        pair = prqTree.removeByValue(new Point(0, 0));
        assertEquals(new KVPair<>("P1", new Point(0, 0)), pair);

        dumpString = "Node at 0, 0, 1024:\n" +
                            "(P1, 512, 0)\n" +
                            "(P1, 0, 512)\n" +
                            "(P1, 512, 512)\n" +
                            "QuadTree Size: 1 QuadTree Nodes Printed.\n";

        assertEquals(dumpString, prqTree.dump());
    }
}