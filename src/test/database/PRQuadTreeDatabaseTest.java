package test.database;

import database.PRQuadTreeDatabase;
import org.junit.Before;
import org.junit.Test;
import quadtree.Point;
import skiplist.KVPair;

import java.util.List;

import static org.junit.Assert.*;

public class PRQuadTreeDatabaseTest {
    private PRQuadTreeDatabase quadDB;
    private Point addMe;
    private KVPair<String, Point> pair1;

    /**
     * Set up variables for testing.
     * Executed before every test case.
     */
    @Before
    public void setUp() {
        quadDB = new PRQuadTreeDatabase();
        addMe = new Point(10, 20);
        Point addMe2 = new Point(11, 20);
        Point duplicate = new Point(10, 20);
        Point duplicate2 = new Point(11, 20);
        pair1 = new KVPair<>("A", addMe);
        KVPair<String, Point> pair2 = new KVPair<>("B", addMe2);
        KVPair<String, Point> pair3 = new KVPair<>("C", duplicate);
        KVPair<String, Point> pair4 = new KVPair<>("D", duplicate2);
        quadDB.insert(pair1);
        quadDB.insert(pair2);
        quadDB.insert(pair3);
        quadDB.insert(pair4);
    }

    /**
     * test inserting a pair with same name... and point values
     * and one with different point but same key
     */
    @Test
    public void testInsert() {
        Point addMe = new Point(10, 20);
        Point addMe2 = new Point(15, 20);
        KVPair<String, Point> fail = new KVPair<>("A", addMe);
        KVPair<String, Point> pass = new KVPair<>("A", addMe2);
        assertFalse(quadDB.insert(fail));
        assertTrue(quadDB.insert(pass));

    }

    @Test
    public void testInsertionHelper() {
        Point addMe = new Point(-2, 38);
        Point actuallyValid = new Point(53, 30);
        KVPair<String, Point> fail = new KVPair<>("E", addMe);
        KVPair<String, Point> pass = new KVPair<>("E", actuallyValid);
        assertFalse(quadDB.insert(fail));
        assertTrue(quadDB.insert(pass));
    }

    /**
     * Tests quadDB removal when key is specified
     */
    @Test
    public void removeKeyTest() {
        KVPair<String, Point> valid = quadDB.remove("A");
        assertEquals(valid, pair1);
        assertNull(quadDB.remove("IDontExist"));
    }

    /**
     * tests remove by value
     */
    @Test
    public void removeByValueTests() {
        Point invalid = new Point(35, 20);
        assertNull(quadDB.removeByValue(invalid));
        assertEquals(quadDB.removeByValue(addMe), pair1);
    }

    /**
     * Test regionSearch
     */
    @Test
    public void regionSearch() {
        StringBuilder sendMe = new StringBuilder();
        quadDB.regionSearch(0, 0, 30, 30, sendMe);
        assertEquals(sendMe.toString(), "1");
    }

    /**
     * Test intersections
     */
    @Test
    public void testIntersections() {
        assertNull(quadDB.intersections());
    }

    /**
     * tests Dump
     */
    @Test
    public void testDump() {
        String checkMeOut = "QuadTree Size: 1 QuadTree Nodes Printed.";
        assertTrue(quadDB.dump().contains(checkMeOut));
    }

    /**
     * Testing duplicates
     */
    @Test
    public void testDuplicates() {
        List<Point> items = quadDB.duplicates();
        assertEquals(items.size(), 2);
    }

    /**
     * Test search
     */
    @Test
    public void testSearch() {
        List<KVPair<String, Point>> result = quadDB.search("A");
        assertEquals(result.size(), 1);
    }

    /**
     * Test validate rectangle
     */
    @Test
    public void testValidateV() {
        Point fail = new Point(-1, 20); //fails cause x
        Point fail2 = new Point(0, -1); // fails cause y
        Point fail3 = new Point(1025, 0); // fails cause x too big
        Point fail4 = new Point(0, 1025); // fails cause y too big
        assertFalse(quadDB.validateV(fail));
        assertFalse(quadDB.validateV(fail2));
        assertFalse(quadDB.validateV(fail3));
        assertFalse(quadDB.validateV(fail4));
        assertFalse(quadDB.validateV(null));
    }

}
