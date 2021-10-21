package test.quadtree;

import processor.Rectangle;
import quadtree.PRQTree;
import quadtree.Point;
import org.junit.Before;
import org.junit.Test;
import quadtree.TreeNode;
import skiplist.KVPair;
import skiplist.Pair;

import java.util.ArrayList;
import java.util.List;

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
    private PRQTree<String> prqTree;


    /**
     * Setup test
     */
    @Before
    public void setUp() {
        prqTree = new PRQTree<>(WORLD_WIDTH);
    }


    /**
     * Test the Empty Dump
     */
    @Test
    public void testEmptyDump() {

        String dumpString = "Node at 0, 0, 1024: Empty\n" +
                            "QuadTree Size: 1 QuadTree Nodes Printed.";

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
                            "QuadTree Size: 1 QuadTree Nodes Printed.";

        assertEquals(dumpString, prqTree.dump());
    }


    /**
     * Test the dump and insert on node with points (P1, 0, 0), (P1, 512, 0),
     * (P1, 0, 512), (P1, 512, 512)
     */
    @Test
    public void testMultiInsertDump() {

        prqTree.insert(new KVPair<>("P1", new Point(0, 0)));
        prqTree.insert(new KVPair<>("P2", new Point(512, 0)));
        prqTree.insert(new KVPair<>("P3", new Point(0, 512)));
        prqTree.insert(new KVPair<>("P4", new Point(512, 512)));

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P4, 512, 512)\n" +
                            "QuadTree Size: 5 QuadTree Nodes Printed.";

        assertEquals(dumpString, prqTree.dump());
    }


    /**
     * Test the dump and insert on node with points reaching 2 levels
     */
    @Test
    public void testMulti2LevelInsertDump() {

        testMultiInsertDump();

        prqTree.insert(new KVPair<>("P1", new Point(256, 0)));
        prqTree.insert(new KVPair<>("P1", new Point(0, 256)));
        prqTree.insert(new KVPair<>("P1", new Point(256, 256)));

        prqTree.insert(new KVPair<>("P4", new Point(768, 512)));
        prqTree.insert(new KVPair<>("P4", new Point(512, 768)));
        prqTree.insert(new KVPair<>("P4", new Point(768, 768)));

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512: Internal\n" +
                            "    Node at 0, 0, 256:\n" +
                            "    (P1, 0, 0)\n" +
                            "    Node at 256, 0, 256:\n" +
                            "    (P1, 256, 0)\n" +
                            "    Node at 0, 256, 256:\n" +
                            "    (P1, 0, 256)\n" +
                            "    Node at 256, 256, 256:\n" +
                            "    (P1, 256, 256)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  Node at 512, 512, 512: Internal\n" +
                            "    Node at 512, 512, 256:\n" +
                            "    (P4, 512, 512)\n" +
                            "    Node at 768, 512, 256:\n" +
                            "    (P4, 768, 512)\n" +
                            "    Node at 512, 768, 256:\n" +
                            "    (P4, 512, 768)\n" +
                            "    Node at 768, 768, 256:\n" +
                            "    (P4, 768, 768)\n" +
                            "QuadTree Size: 13 QuadTree Nodes Printed.";

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

        testMulti2LevelInsertDump();

        KVPair<String, Point> pair = prqTree.removeByValue(new Point(0, 0));
        assertEquals(new KVPair<>("P1", new Point(0, 0)), pair);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 256, 0)\n" +
                            "  (P1, 0, 256)\n" +
                            "  (P1, 256, 256)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  Node at 512, 512, 512: Internal\n" +
                            "    Node at 512, 512, 256:\n" +
                            "    (P4, 512, 512)\n" +
                            "    Node at 768, 512, 256:\n" +
                            "    (P4, 768, 512)\n" +
                            "    Node at 512, 768, 256:\n" +
                            "    (P4, 512, 768)\n" +
                            "    Node at 768, 768, 256:\n" +
                            "    (P4, 768, 768)\n" +
                            "QuadTree Size: 9 QuadTree Nodes Printed.";

        assertEquals(dumpString, prqTree.dump());

        pair = prqTree.removeByValue(new Point(256, 256));
        assertEquals(new KVPair<>("P1", new Point(256, 256)), pair);

        pair = prqTree.removeByValue(new Point(256, 0));
        assertEquals(new KVPair<>("P1", new Point(256, 0)), pair);

        pair = prqTree.removeByValue(new Point(0, 256));
        assertEquals(new KVPair<>("P1", new Point(0, 256)), pair);

        pair = prqTree.removeByValue(new Point(512, 768));
        assertEquals(new KVPair<>("P4", new Point(512, 768)), pair);

        pair = prqTree.removeByValue(new Point(768, 768));
        assertEquals(new KVPair<>("P4", new Point(768, 768)), pair);

        pair = prqTree.removeByValue(new Point(768, 512));
        assertEquals(new KVPair<>("P4", new Point(768, 512)), pair);

        dumpString = "Node at 0, 0, 1024:\n" +
                            "(P2, 512, 0)\n" +
                            "(P3, 0, 512)\n" +
                            "(P4, 512, 512)\n" +
                            "QuadTree Size: 1 QuadTree Nodes Printed.";

        assertEquals(dumpString, prqTree.dump());
    }


    /**
     * Test duplicates on empty tree
     */
    @Test
    public void testEmptyDuplicates() {
        assertEquals(0, prqTree.duplicates().size());
    }


    /**
     * Test leaf duplicates on tree with 2 entries
     */
    @Test
    public void testLeafDuplicates() {
        prqTree.insert(new KVPair<>("P1", new Point(0, 1)));
        prqTree.insert(new KVPair<>("P2", new Point(0, 1)));

        List<Point> duplicates = prqTree.duplicates();

        assertEquals(1, duplicates.size());
        assertTrue(duplicates.contains(new Point(0, 1)));
    }


    /**
     * Test leaf duplicates on empty tree
     */
    @Test
    public void testInternalMultiDuplicates() {
        testMulti2LevelInsertDump();

        for(int i = 0; i < 5; i++){
            prqTree.insert(new KVPair<>("P" + i, new Point(0, 0)));
            prqTree.insert(new KVPair<>("P" + i, new Point(512, 0)));
            prqTree.insert(new KVPair<>("P" + i, new Point(0, 512)));
            prqTree.insert(new KVPair<>("P" + i, new Point(512, 512)));
            prqTree.insert(new KVPair<>("P" + i, new Point(768, 768)));
        }

        List<Point> duplicates = prqTree.duplicates();

        assertEquals(5, duplicates.size());
        assertTrue(duplicates.contains(new Point(0, 512)));
        assertTrue(duplicates.contains(new Point(512, 0)));
        assertTrue(duplicates.contains(new Point(0, 0)));
        assertTrue(duplicates.contains(new Point(512, 512)));
        assertTrue(duplicates.contains(new Point(768, 768)));
    }


    /**
     * Test full region search on level 2 tree
     */
    @Test
    public void testLevel2RegionSearch() {
        testMulti2LevelInsertDump();
        List<KVPair<String, Point>> intersections = new ArrayList<>();
        assertEquals(13, prqTree.regionSearch(
                new Rectangle(-1, -1, 1030, 1030), intersections));

        assertEquals(10, intersections.size());
        assertTrue(intersections.contains(new KVPair<>("P1", new Point(0, 0))));
        assertTrue(intersections.contains(new KVPair<>("P1", new Point(256,
                256))));
        assertTrue(intersections.contains(new KVPair<>("P3", new Point(0,
                512))));
        assertTrue(intersections.contains(new KVPair<>("P2", new Point(512,
                0))));
        assertTrue(intersections.contains(new KVPair<>("P4", new Point(512,
                512))));
        assertTrue(intersections.contains(new KVPair<>("P4", new Point(768,
                768))));

    }
}