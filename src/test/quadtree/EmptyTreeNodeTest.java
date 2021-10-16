package test.quadtree;

import processor.Rectangle;
import quadtree.EmptyTreeNode;
import quadtree.Point;
import quadtree.TreeNode;
import org.junit.Before;
import org.junit.Test;
import skiplist.KVPair;

import static org.junit.Assert.*;

/**
 * This is a test class responsible for testing the operations of an
 * EmptyTreeNode
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class EmptyTreeNodeTest {

    private static final int WORLD_WIDTH = 1024;
    public static final Point ROOT_START = new Point(0, 0);

    //The empty node to test on
    private TreeNode<String> emptyTreeNode;


    /**
     * Setup test
     */
    @Before
    public void setUp() {
        emptyTreeNode = EmptyTreeNode.getInstance();
    }


    /**
     * Test the dump on empty node
     */
    @Test
    public void testEmptyLevel0Dump() {
        StringBuilder sb = new StringBuilder();
        assertEquals(1,
                emptyTreeNode.dump(0, ROOT_START, WORLD_WIDTH, sb));

        assertEquals("Node at 0, 0, 1024: Empty\n", sb.toString());
    }


    /**
     * Test the dump on empty node at level 2
     */
    @Test
    public void testEmptyLevel2Dump() {
        StringBuilder sb = new StringBuilder();
        assertEquals(1,
                emptyTreeNode.dump(2, ROOT_START, WORLD_WIDTH, sb));

        assertEquals("    Node at 0, 0, 1024: Empty\n", sb.toString());
    }


    /**
     * Test inserting point into an empty node
     */
    @Test
    public void testInsert() {
        TreeNode<String> node = emptyTreeNode.insert(new KVPair<>("P1",
                new Point(0, 1)), ROOT_START, 1024);

        StringBuilder sb = new StringBuilder();
        assertEquals(1, node.dump(0, ROOT_START, WORLD_WIDTH, sb));

        assertEquals("Node at 0, 0, 1024:\n(P1, 0, 1)\n", sb.toString());
    }

    /**
     * Test remove by value on an empty node
     */
    @Test
    public void testRemoveByValue() {
        assertSame(emptyTreeNode, emptyTreeNode.removeByValue(new Point(0, 1)
                , new Point(0, 0), WORLD_WIDTH, new StringBuilder()));
    }


    /**
     * Test duplicates on empty node
     */
    @Test
    public void testDuplicates() {
        assertEquals(0, emptyTreeNode.duplicates().size());
    }

    /**
     * Test if empty tree node produces any points
     */
    @Test
    public void testGetPoints() {
        assertEquals(0, emptyTreeNode.getKeyValuePairs().size());
    }


    /**
     * Test region search on empty node
     */
    @Test
    public void testRegionSearch() {
        assertEquals(0,
                emptyTreeNode.regionSearch(
                        new Rectangle(0 ,0 ,1024, 1024),
                        ROOT_START, WORLD_WIDTH).size());
    }
}