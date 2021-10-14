package test.quadtree;

import quadtree.IntTreeNode;
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
                new Point(0, 0)), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                new Point(511, 511)), ROOT_START, WORLD_WIDTH);

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
                new Point(512, 0)), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                new Point(1023, 511)), ROOT_START, WORLD_WIDTH);

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
                new Point(0, 512)), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                new Point(511, 1023)), ROOT_START, WORLD_WIDTH);

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
                new Point(512, 512)), ROOT_START, WORLD_WIDTH);

        internalNode = internalNode.insert(new KVPair<>("P1",
                new Point(1023, 1023)), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512: Empty\n" +
                            "  Node at 512, 0, 512: Empty\n" +
                            "  Node at 0, 512, 512: Empty\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P1, 512, 512)\n" +
                            "  (P1, 1023, 1023)\n";

        testAssertDump(internalNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }

    private void testAssertDump(TreeNode<String> node, int level, Point start
            , int width, int assertNodes, String assertDump) {

        StringBuilder sb = new StringBuilder();
        assertEquals(assertNodes,
                node.dump(level, start, width, sb));

        assertEquals(assertDump, sb.toString());
    }
}