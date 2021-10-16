package test.quadtree;

import quadtree.EmptyTreeNode;
import quadtree.LeafTreeNode;
import quadtree.Point;
import quadtree.TreeNode;
import org.junit.Before;
import org.junit.Test;
import skiplist.KVPair;

import static org.junit.Assert.*;

/**
 * This is a test class responsible for testing the operations of an
 * leaf tree node
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class LeafTreeNodeTest {

    private static final int WORLD_WIDTH = 1024;
    private static final Point ROOT_START = new Point(0, 0);

    //The empty node to test on
    private TreeNode<String> leafNode;


    /**
     * Setup test
     */
    @Before
    public void setUp() {
        leafNode = new LeafTreeNode<>();
    }


    /**
     * Test the dump and insert on node with point (P1, 0, 0)
     */
    @Test
    public void testSingleInsertDump() {

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 0)), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024:\n" +
                            "(P1, 0, 0)\n";

        testAssertDump(leafNode, 0, ROOT_START, WORLD_WIDTH, 1, dumpString);
    }

    /**
     * Test the dump and insert on node with points (P1, 0, 0), (P1, 0, 1),
     * (P1, 0, 2)
     */
    @Test
    public void testMultiInsertDump() {

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 0)), ROOT_START, WORLD_WIDTH);

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 1)), ROOT_START, WORLD_WIDTH);

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 2)), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024:\n" +
                            "(P1, 0, 0)\n" +
                            "(P1, 0, 1)\n" +
                            "(P1, 0, 2)\n";

        testAssertDump(leafNode, 0, ROOT_START, WORLD_WIDTH, 1, dumpString);
    }


    /**
     * Test the dump and insert on node with duplicate points (P1, 0, 0),
     * (P1, 0, 1),
     * (P1, 0, 2) x 5
     */
    @Test
    public void testMultiDuplicateInsertDump() {

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 0)), ROOT_START, WORLD_WIDTH);

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 1)), ROOT_START, WORLD_WIDTH);

        for(int i = 2; i <= 6; i++){
            leafNode = leafNode.insert(new KVPair<>("P" + i,
                    new Point(0, 2)), ROOT_START, WORLD_WIDTH);
        }

        String dumpString = "Node at 0, 0, 1024:\n" +
                            "(P1, 0, 0)\n" +
                            "(P1, 0, 1)\n" +
                            "(P2, 0, 2)\n" +
                            "(P3, 0, 2)\n" +
                            "(P4, 0, 2)\n" +
                            "(P5, 0, 2)\n" +
                            "(P6, 0, 2)\n";

        testAssertDump(leafNode, 0, ROOT_START, WORLD_WIDTH, 1, dumpString);
    }


    /**
     * Test the dump and insert on node with points (P1, 0, 0),
     * (P1, 512, 0),
     * (P1, 0, 512),
     * (P1, 512, 512)
     * Will split into internal node, with 1 point in each region
     */
    @Test
    public void testConvertToInternalInsertDump() {

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 0)), ROOT_START, WORLD_WIDTH);

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(512, 0)), ROOT_START, WORLD_WIDTH);

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 512)), ROOT_START, WORLD_WIDTH);

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(512, 512)), ROOT_START, WORLD_WIDTH);

        String dumpString = "Node at 0, 0, 1024: Internal\n" +
                            "  Node at 0, 0, 512:\n" +
                            "  (P1, 0, 0)\n" +
                            "  Node at 512, 0, 512:\n" +
                            "  (P1, 512, 0)\n" +
                            "  Node at 0, 512, 512:\n" +
                            "  (P1, 0, 512)\n" +
                            "  Node at 512, 512, 512:\n" +
                            "  (P1, 512, 512)\n";

        testAssertDump(leafNode, 0, ROOT_START, WORLD_WIDTH, 5, dumpString);
    }


    /**
     * Test if leaf tree node produces any points
     */
    @Test
    public void testGetPoints() {
        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(0, 0)), ROOT_START, WORLD_WIDTH);

        leafNode = leafNode.insert(new KVPair<>("P1",
                new Point(512, 0)), ROOT_START, WORLD_WIDTH);
        assertEquals(2, leafNode.getPoints().size());
    }

    /**
     * Test remove by value on an empty leaf node to make it empty node
     */
    @Test
    public void testMultiRemoveByValue() {
        Point p1 = new Point(0, 0);
        leafNode = leafNode.insert(new KVPair<>("P1",
                p1), ROOT_START, WORLD_WIDTH);

        Point p2 = new Point(512, 0);
        leafNode = leafNode.insert(new KVPair<>("P2",
                p2), ROOT_START, WORLD_WIDTH);

        StringBuilder sb = new StringBuilder();
        leafNode = leafNode.removeByValue(p1, ROOT_START, WORLD_WIDTH, sb);

        assertEquals("P1", sb.toString());

        String dumpString = "Node at 0, 0, 1024:\n" +
                            "(P2, 512, 0)\n";

        testAssertDump(leafNode, 0, ROOT_START, WORLD_WIDTH, 1, dumpString);

        sb = new StringBuilder();

        leafNode = leafNode.removeByValue(p2, ROOT_START, WORLD_WIDTH, sb);

        assertEquals("P2", sb.toString());

        dumpString = "Node at 0, 0, 1024: Empty\n";

        testAssertDump(leafNode, 0, ROOT_START, WORLD_WIDTH, 1, dumpString);
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