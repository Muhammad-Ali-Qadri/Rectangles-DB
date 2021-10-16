package test.quadtree;

import quadtree.IntTreeNode;
import quadtree.LeafTreeNode;
import quadtree.Point;
import quadtree.TreeNode;
import org.junit.Before;
import org.junit.Test;
import skiplist.KVPair;

import static org.junit.Assert.*;

/**
 * This is a test class responsible for testing the operations of an
 * internal tree node
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class IntTreeNodeTest {

    private static final int WORLD_WIDTH = 1024;
    private static final Point ROOT_START = new Point(0, 0);
    public static final Point NW_TOP_LEFT_CORNER = new Point(0, 0);
    public static final Point NW_BOTTOM_RIGHT_CORNER = new Point(511, 511);
    public static final Point NE_TOP_LEFT_CORNER = new Point(512, 0);
    public static final Point NE_BOTTOM_RIGHT_CORNER = new Point(1023, 511);
    public static final Point SW_TOP_LEFT_CORNER = new Point(0, 512);
    public static final Point SW_BOTTOM_RIGHT_CORNER = new Point(511, 1023);
    public static final Point SE_TOP_LEFT_CORNER = new Point(512, 512);
    public static final Point SE_BOTTOM_RIGHT_CORNER = new Point(1023, 1023);

    //The empty node to test on
    private TreeNode<String> internalNode;


    /**
     * Setup test
     */
    @Before
    public void setUp() {
        internalNode = new IntTreeNode<>();
    }


    /**
     * Test the dump on empty node
     */
    @Test
    public void testEmptyInternalLevel0Dump() {
        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512: Empty\n" +
                            "  Node at 512, 0, 512: Empty\n" +
                            "  Node at 0, 512, 512: Empty\n" +
                            "  Node at 512, 512, 512: Empty\n";

        testAssertDump(internalNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }


    /**
     * Test the dump and insert on node with point in North-west region on
     * edge case points (0, 0), (511, 511)
     */
    @Test
    public void testNWInsertDump() {

        internalNode = internalNode.insert(new KVPair<>("P1",
                NW_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                NW_BOTTOM_RIGHT_CORNER), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  (P1, 511, 511)\n" +
                            "  Node at 512, 0, 512: Empty\n" +
                            "  Node at 0, 512, 512: Empty\n" +
                            "  Node at 512, 512, 512: Empty\n";

        testAssertDump(internalNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }

    /**
     * Test the dump and insert on node with point in North-east region on
     * edge case points (512, 0), (1023, 511)
     */
    @Test
    public void testNEInsertDump() {

        internalNode = internalNode.insert(new KVPair<>("P1",
                NE_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                NE_BOTTOM_RIGHT_CORNER), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512: Empty\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P1, 512, 0)\n" +
                            "  (P1, 1023, 511)\n" +
                            "  Node at 0, 512, 512: Empty\n" +
                            "  Node at 512, 512, 512: Empty\n";

        testAssertDump(internalNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }


    /**
     * Test the dump and insert on node with point in South-west region on
     * edge case points edge case points (0, 512), (511, 1023)
     */
    @Test
    public void testSWInsertDump() {

        internalNode = internalNode.insert(new KVPair<>("P1",
                SW_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                SW_BOTTOM_RIGHT_CORNER), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512: Empty\n" +
                            "  Node at 512, 0, 512: Empty\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P1, 0, 512)\n" +
                            "  (P1, 511, 1023)\n" +
                            "  Node at 512, 512, 512: Empty\n";

        testAssertDump(internalNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }


    /**
     * Test the dump and insert on node with point in South-east region on
     * edge case points edge case points (512, 512), (1023, 1023)
     */
    @Test
    public void testSEInsertDump() {

        internalNode = internalNode.insert(new KVPair<>("P1",
                SE_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                SE_BOTTOM_RIGHT_CORNER), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512: Empty\n" +
                            "  Node at 512, 0, 512: Empty\n" +
                            "  Node at 0, 512, 512: Empty\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P1, 512, 512)\n" +
                            "  (P1, 1023, 1023)\n";

        testAssertDump(internalNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }

    /**
     * Test the dump and insert on node with point in South-east region on
     * edge case points edge case points (512, 512), (1023, 1023)
     */
    @Test
    public void testMaxInsertDump() {
        internalNode = internalNode.insert(new KVPair<>("P1",
                NW_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                NW_BOTTOM_RIGHT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P2",
                NE_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P3",
                SW_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P3",
                SW_BOTTOM_RIGHT_CORNER), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P4",
                SE_TOP_LEFT_CORNER), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  (P1, 511, 511)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  (P3, 511, 1023)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P4, 512, 512)\n";

        testAssertDump(internalNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }


    /**
     * Test reduce max internal node to empty NW node
     */
    @Test
    public void testEmptyNWRemoveByValue() {
        testMaxInsertDump();
        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 511, 511)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  (P3, 511, 1023)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P4, 512, 512)\n";

        testAssertRemoveDump(NW_TOP_LEFT_CORNER, ROOT_START, WORLD_WIDTH,
                0, 5, "P1", dumpString);

        dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512: Empty\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  (P3, 511, 1023)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P4, 512, 512)\n";

        testAssertRemoveDump(NW_BOTTOM_RIGHT_CORNER, ROOT_START, WORLD_WIDTH,
                0, 5, "P1", dumpString);
    }

    /**
     * Test reduce max internal node to empty NE node
     */
    @Test
    public void testEmptyNERemoveByValue() {
        testMaxInsertDump();
        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  (P1, 511, 511)\n" +
                            "  Node at 512, 0, 512: Empty\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  (P3, 511, 1023)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P4, 512, 512)\n";

        testAssertRemoveDump(NE_TOP_LEFT_CORNER, ROOT_START, WORLD_WIDTH,
                0, 5, "P2", dumpString);
    }

    /**
     * Test reduce max internal node to empty SW node
     */
    @Test
    public void testEmptySWRemoveByValue() {
        testMaxInsertDump();
        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  (P1, 511, 511)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 511, 1023)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P4, 512, 512)\n";

        testAssertRemoveDump(SW_TOP_LEFT_CORNER, ROOT_START, WORLD_WIDTH,
                0, 5, "P3", dumpString);

        dumpString = "Node at 0, 0, 1024: Internal\n" +
                     "  Node at 0, 0, 512:\n" +
                     "  (P1, 0, 0)\n" +
                     "  (P1, 511, 511)\n" +
                     "  Node at 512, 0, 512:\n" +
                     "  (P2, 512, 0)\n" +
                     "  Node at 0, 512, 512: Empty\n" +
                     "  Node at 512, 512, 512:\n" +
                     "  (P4, 512, 512)\n";

        testAssertRemoveDump(SW_BOTTOM_RIGHT_CORNER, ROOT_START, WORLD_WIDTH,
                0, 5, "P3", dumpString);
    }

    /**
     * Test reduce max internal node to empty SE node
     */
    @Test
    public void testEmptySERemoveByValue() {
        testMaxInsertDump();
        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  (P1, 511, 511)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P2, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P3, 0, 512)\n" +
                            "  (P3, 511, 1023)\n" +
                            "  Node at 512, 512, 512: Empty\n";

        testAssertRemoveDump(SE_TOP_LEFT_CORNER, ROOT_START, WORLD_WIDTH,
                0, 5, "P4", dumpString);
    }


    /**
     * Test reduce max internal node to leaf
     */
    @Test
    public void testReduceToLeafRemoveByValue() {
        testMaxInsertDump();

        StringBuilder sb = new StringBuilder();
        internalNode = internalNode.removeByValue(NE_TOP_LEFT_CORNER,
                ROOT_START, WORLD_WIDTH, sb);

        internalNode = internalNode.removeByValue(SE_TOP_LEFT_CORNER,
                ROOT_START, WORLD_WIDTH, sb);

        assertEquals("P2P4", sb.toString());

        String dumpString = "Node at 0, 0, 1024:\n" +
                            "(P1, 511, 511)\n" +
                            "(P3, 0, 512)\n" +
                            "(P3, 511, 1023)\n";

        testAssertRemoveDump(NW_TOP_LEFT_CORNER, ROOT_START, WORLD_WIDTH,
                0, 1, "P1", dumpString);

        assertTrue(internalNode instanceof LeafTreeNode);
    }

    /**
     * Test if internal tree node produces any points
     */
    @Test
    public void testGetPoints() {
        assertEquals(0, internalNode.getPoints().size());
    }

    private void testAssertRemoveDump(Point point, Point start, int width,
                                      int level, int nodes, String key,
                                      String dumpString){
        StringBuilder sb = new StringBuilder();
        internalNode = internalNode.removeByValue(point, start, width,
                sb);

        assertEquals(key, sb.toString());

        testAssertDump(internalNode, level, start, width, nodes, dumpString);
    }


    //Assert the dump values
    private void testAssertDump(TreeNode<String> node, int level, Point start
            , int width, int assertNodes, String assertDump) {

        StringBuilder sb = new StringBuilder();
        assertEquals(assertNodes,
                node.dump(level, start, width, sb));

        assertEquals(assertDump, sb.toString());
    }
}