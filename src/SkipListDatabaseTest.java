import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This test class is responsible for testing each scenario of each method in
 * the SkipListDatabase class
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class SkipListDatabaseTest {

    //Database to store rectangles and test them out
    private SkipListDatabase rect3;
    //Empty Database to store new rectangles and test them out
    private SkipListDatabase rectEmpty;
    //Empty Database to store new rectangles and test them out,
    // only contains the middle rectangle
    private SkipListDatabase rectMid;
    //A KV pair with key: "A", x = 0, y = 0, width = 10, height = 10, also
    // known as the upper left
    private KVPair<String, Rectangle> rectAUpperLeft;
    //A KV pair with key: "B", x = 10, y = 10, width = 990, height = 990,
    // also known as the middle
    private KVPair<String, Rectangle> rectBMiddle;
    //A KV pair with key: "C", x = 1000, y = 1000, width = 24, height = 24,
    // also known as the bottom right
    private KVPair<String, Rectangle> rectCBottomRight;


    /**
     * Set up variables for testing.
     * Executed before every test case.
     */
    @Before
    public void setUp() {
        rectAUpperLeft = new KVPair<>("A",
                new Rectangle(0, 0, 10, 10));

        rectBMiddle = new KVPair<>("B",
                new Rectangle(10, 10, 990, 990));

        rectCBottomRight = new KVPair<>("C",
                new Rectangle(1000, 1000, 24, 24));

        rectEmpty = new SkipListDatabase();

        rectMid = new SkipListDatabase();
        rectMid.insert(rectBMiddle);

        rect3 = new SkipListDatabase();

        rect3.insert(rectAUpperLeft);
        rect3.insert(rectBMiddle);
        rect3.insert(rectCBottomRight);
    }

    /**
     * Tests if null value can be inserted
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRect3NullInsert() {
        rect3.insert(null);
        fail();
    }


    /**
     * Tests if invalid key "1" can be inserted
     */
    @Test
    public void testRect3InvalidKey1Insert() {
        assertFalse(rect3.insert(new KVPair<>("1",
                new Rectangle(10, 10, 10, 10))));
    }


    /**
     * Tests if invalid key "1_" can be inserted
     */
    @Test
    public void testRect3InvalidKey1SpecialCharInsert() {
        assertFalse(rect3.insert(new KVPair<>("1_",
                new Rectangle(10, 10, 10, 10))));
    }


    /**
     * Tests if invalid key "1A" can be inserted
     */
    @Test
    public void testRect3InvalidKey1AInsert() {
        assertFalse(rect3.insert(new KVPair<>("1A",
                new Rectangle(10, 10, 10, 10))));
    }


    /**
     * Tests if invalid key "A1!" can be inserted
     */
    @Test
    public void testRect3InvalidKeyA1SpecialInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1!",
                new Rectangle(10, 10, 10, 10))));
    }


    /**
     * Tests if valid key "A1" can be inserted
     */
    @Test
    public void testRect3ValidKeyA1Insert() {
        assertTrue(rect3.insert(new KVPair<>("A1",
                new Rectangle(10, 10, 10, 10))));
    }


    /**
     * Tests if valid key "A1_" can be inserted
     */
    @Test
    public void testRect3ValidKeyA1SpecialCharInsert() {
        assertTrue(rect3.insert(new KVPair<>("A1_",
                new Rectangle(10, 10, 10, 10))));
    }


    /**
     * Tests if invalid x value "-1" can be inserted
     */
    @Test
    public void testRect3InvalidXInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(-1, 10, 10, 10))));
    }


    /**
     * Tests if invalid y value "-1" can be inserted
     */
    @Test
    public void testRect3InvalidYInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, -1, 10, 10))));
    }


    /**
     * Tests if invalid width value "-1" can be inserted
     */
    @Test
    public void testRect3InvalidWidthInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, 0, -1, 10))));
    }

    /**
     * Tests if invalid height value "-1" can be inserted
     */
    @Test
    public void testRect3InvalidHeightInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, 0, 10, -10))));
    }


    /**
     * Tests if invalid height value "1025" can be inserted
     */
    @Test
    public void testRect3InvalidHeightExcessInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, 0, 10, 1025))));
    }


    /**
     * Tests if invalid width value "1025" can be inserted
     */
    @Test
    public void testRect3InvalidWidthExcessInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, 0, 1025, 10))));
    }


    /**
     * Tests if invalid x value "1025" can be inserted
     */
    @Test
    public void testRect3InvalidXExcessInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(1025, 0, 1, 1))));
    }


    /**
     * Tests if invalid y value "1025" can be inserted
     */
    @Test
    public void testRect3InvalidYExcessInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, 1025, 1, 1))));
    }


    /**
     * Tests if invalid y value "1025" can be inserted
     */
    @Test
    public void testRect3ValidYExcessInsert() {
        assertFalse(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, 1025, 1, 1))));
    }


    /**
     * Tests if valid rectangle inserted with coordinate (0, 0) width = 10,
     * height = 10
     */
    @Test
    public void testEmptyValidUpperLeftInsert() {
        assertTrue(rectEmpty.insert(rectAUpperLeft));
    }


    /**
     * Tests if valid rectangle inserted with coordinate (1000, 0) width =
     * 24,
     * height = 24
     */
    @Test
    public void testEmptyValidUpperRightInsert() {
        assertTrue(rectEmpty.insert(new KVPair<>("A1_",
                new Rectangle(1000, 0, 24, 24))));
    }


    /**
     * Tests if valid rectangle inserted with coordinate (0, 1000) width =
     * 24,
     * height = 24
     */
    @Test
    public void testEmptyValidBottomLeftInsert() {
        assertTrue(rectEmpty.insert(new KVPair<>("A1_",
                new Rectangle(0, 1000, 24, 24))));
    }


    /**
     * Tests if valid rectangle inserted with coordinate (1000, 1000) width =
     * 24,
     * height = 24
     */
    @Test
    public void testEmptyValidBottomRightInsert() {
        assertTrue(rectEmpty.insert(rectCBottomRight));
    }


    /**
     * Tests if valid rectangle inserted with coordinate (10, 10) width =
     * 990,
     * height = 990
     */
    @Test
    public void testEmptyValidMiddleInsert() {
        assertTrue(rectEmpty.insert(rectBMiddle));
    }


    /**
     * Tests if valid duplicate rectangle inserted with coordinate (10, 10)
     * width = 990, height = 990 and Key = A1_
     */
    @Test
    public void testRect3ValidDuplicateValueInsert() {
        assertTrue(rect3.insert(new KVPair<>("A1_",
                new Rectangle(0, 0, 10, 10))));
    }


    /**
     * Tests if valid rectangle inserted with coordinate (10, 10)
     * width = 990, height = 990 and duplicated Key = A
     */
    @Test
    public void testRect3ValidDuplicateKeyInsert() {
        assertTrue(rect3.insert(new KVPair<>("A",
                new Rectangle(0, 0, 10, 10))));
    }


    /**
     * Tests if multiple valid rectangles inserted
     */
    @Test
    public void testRect3ValidMultipleInsert() {
        assertTrue(rect3.insert(new KVPair<>("A",
                new Rectangle(0, 0, 10, 10))));
        assertTrue(rect3.insert(new KVPair<>("B1_",
                new Rectangle(0, 1000, 24, 24))));
        assertTrue(rectEmpty.insert(new KVPair<>("C1_",
                new Rectangle(1000, 0, 24, 24))));
    }


    /**
     * Tests exception is thrown when search is done for null key
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRect3NullSearch() {
        rect3.search(null);
        fail();
    }


    /**
     * Tests search can be done for an invalid key "1A"
     */
    @Test
    public void testRect3InvalidKeySearch() {
        assertNull(rect3.search("1A"));
    }


    /**
     * Tests search can be done for valid "D" key in an empty database
     */
    @Test
    public void testEmptyASearch() {
        assertNull(rectEmpty.search("A"));
    }


    /**
     * Tests search can be done for invalid key "D" in a database with 3
     * rectangles
     */
    @Test
    public void testRect3DSearch() {
        assertNull(rect3.search("D"));
    }


    /**
     * Tests search returns pair for valid key "A" in a database with 3
     * rectangles
     */
    @Test
    public void testRect3ASearch() {
        List<KVPair<String, Rectangle>> list = new ArrayList<>();
        list.add(new KVPair<>("A",
                new Rectangle(0, 0, 10, 10)));

        assertEquals(list, rect3.search("A"));
    }


    /**
     * Tests search returns pair for valid key "A" in a database with 3
     * rectangles with key "A"
     */
    @Test
    public void testRect3ADuplicateKeySearch() {

        rect3.insert(new KVPair<>("A",
                new Rectangle(0, 0, 10, 10)));
        rect3.insert(new KVPair<>("A",
                new Rectangle(1, 1, 11, 11)));
        rect3.insert(new KVPair<>("A",
                new Rectangle(2, 2, 12, 12)));

        List<KVPair<String, Rectangle>> list = new ArrayList<>();
        list.add(new KVPair<>("A",
                new Rectangle(2, 2, 12, 12)));
        list.add(new KVPair<>("A",
                new Rectangle(1, 1, 11, 11)));
        list.add(new KVPair<>("A",
                new Rectangle(0, 0, 10, 10)));
        list.add(new KVPair<>("A",
                new Rectangle(0, 0, 10, 10)));

        assertEquals(list, rect3.search("A"));
    }


    /**
     * Tests exception is thrown when remove is done for null key
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRect3NullKeyRemove() {
        rect3.remove(null);
        fail();
    }


    /**
     * Tests when remove is done for an invalid "1A" key
     */
    @Test
    public void testRect3InvalidKeyRemove() {
        assertNull(rect3.remove("1A"));
    }


    /**
     * Tests when remove is done for a valid "A" key on an empty database
     */
    @Test
    public void testEmptyValidAKeyRemove() {
        assertNull(rectEmpty.remove("1A"));
    }


    /**
     * Tests when remove is done for a valid non-existent "D" key on a
     * database with 3 rectangles
     */
    @Test
    public void testRect3ValidDKeyRemove() {
        assertNull(rect3.remove("D"));
    }


    /**
     * Tests when remove is done for a valid existent "A" key on a database
     * with 3 rectangles
     */
    @Test
    public void testRect3ValidAKeyRemove() {
        assertEquals(new KVPair<>("A",
                        new Rectangle(0, 0, 10, 10)),
                rect3.remove("A"));

        assertNull(rect3.search("A"));
    }


    /**
     * Tests when multiple remove is done for a valid existent "A" key on a
     * database with 3 rectangles
     */
    @Test
    public void testRect3MultipleValidAKeyRemove() {
        assertEquals(new KVPair<>("A",
                        new Rectangle(0, 0, 10, 10)),
                rect3.remove("A"));

        assertNull(rect3.search("A"));

        assertEquals(new KVPair<>("B",
                        new Rectangle(10, 10, 990, 990)),
                rect3.remove("B"));

        assertNull(rect3.search("B"));

        assertEquals(new KVPair<>("C",
                        new Rectangle(1000, 1000, 24, 24)),
                rect3.remove("C"));

        assertNull(rect3.search("C"));
    }


    /**
     * Tests exception is thrown when remove is done for null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRect3NullRemoveByValue() {
        rect3.removeByValue(null);
        fail();
    }


    /**
     * Tests when remove is done for an invalid rectangle x = -1
     */
    @Test
    public void testRect3InvalidRemoveByValue() {
        assertNull(rect3.removeByValue(
                new Rectangle(-1, 0, 10, 10)));
    }


    /**
     * Tests when remove is done for a valid rectangle (0, 0, 10, 10) on an
     * empty database
     */
    @Test
    public void testEmptyValidRectRemoveByValue() {
        assertNull(rectEmpty.removeByValue(
                new Rectangle(0, 0, 10, 10)));
    }


    /**
     * Tests when remove is done for a valid non-existent rectangle (0, 0, 50,
     * 50) on database with 3 rectangles
     */
    @Test
    public void testRect3ValidRectNonExistRemoveByValue() {
        assertNull(rect3.removeByValue(
                new Rectangle(0, 0, 50, 50)));
    }


    /**
     * Tests when remove is done for a valid existent (0, 0, 10, 10) rectangle
     * on a database with 3 rectangles
     */
    @Test
    public void testRect3ValidRectRemoveByValue() {
        assertEquals(new KVPair<>("A",
                        new Rectangle(0, 0, 10, 10)),
                rect3.removeByValue(new Rectangle(0, 0, 10, 10)));

        assertNull(rect3.search("A"));
    }


    /**
     * Tests when remove is done for a valid existent (0, 0, 10, 10) and
     * (10, 10, 990, 990) and (1000, 1000, 24, 24) rectangles on a database
     * with 3 rectangles
     */
    @Test
    public void testRect3MultipleValidRectRemoveByValue() {
        assertEquals(new KVPair<>("A",
                        new Rectangle(0, 0, 10, 10)),
                rect3.removeByValue(new Rectangle(0, 0, 10, 10)));

        assertNull(rect3.search("A"));

        assertEquals(new KVPair<>("B",
                        new Rectangle(10, 10, 990, 990)),
                rect3.removeByValue(new Rectangle(10, 10, 990, 990)));

        assertNull(rect3.search("B"));

        assertEquals(new KVPair<>("C",
                        new Rectangle(1000, 1000, 24, 24)),
                rect3.removeByValue(new Rectangle(1000, 1000, 24, 24)));

        assertNull(rect3.search("C"));
    }


    /**
     * Tests if region search is performed for an invalid region (0, 0, -10,
     * 10) on an empty database
     */
    @Test
    public void testEmptyInvalidWidthRegionSearch() {
        assertNull(rectEmpty.regionSearch(0, 0, -10, 10));
    }


    /**
     * Tests if region search is performed for an invalid region (0, 0, -10,
     * 10) on an empty database
     */
    @Test
    public void testEmptyInvalidHeightRegionSearch() {
        assertNull(rectEmpty.regionSearch(0, 0, 10, -10));
    }


    /**
     * Tests if region search is performed for a valid outside region (1025,
     * 1025, 20, 10) on an empty database
     */
    @Test
    public void testEmptyValidOutsideRegionSearch() {
        assertEquals(0,
                rectEmpty.regionSearch(1025, 1025, 20, 10).size());
    }


    /**
     * Tests if region search is performed for a valid outside region (-1025,
     * -1025, 20, 10) on an empty database
     */
    @Test
    public void testEmptyInValidOutsideRegionSearch() {
        assertEquals(0,
                rectEmpty.regionSearch(-1025, -1025, 20, 10).size());
    }


    /**
     * Tests if region search is performed for a valid outside region (-1025,
     * -1025, 2000, 2000) on an empty database
     */
    @Test
    public void testEmptyValidBigInsideRegionSearch() {
        assertEquals(0,
                rectEmpty.regionSearch(-1025, -1025, 2000, 2000).size());
    }

    /**
     * Tests if region search is performed for a valid region (0, 0, 20, 20)
     * on an empty database
     */
    @Test
    public void testEmptyValidInsideRegionSearch() {
        assertEquals(0,
                rectEmpty.regionSearch(0, 0, 20, 20).size());
    }


    /**
     * Tests if region search is performed for a valid region (0, 0, 10, 10)
     * on a database with 3 rectangles (0, 0, 10, 10) and (10, 10, 990,
     * 990) and (1000, 1000, 24, 24) to get (0, 0, 10, 10)
     */
    @Test
    public void testRect3ValidUpperLeftRegionSearch() {
        List<KVPair<String, Rectangle>> found =
                rect3.regionSearch(0, 0, 10, 10);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("A",
                new Rectangle(0, 0, 10, 10))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 3 rectangles (0, 0, 10, 10) and (10, 10, 990,
     * 990) and (1000, 1000, 24, 24) to get (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMiddleRegionSearch() {
        List<KVPair<String, Rectangle>> found =
                rect3.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("B",
                new Rectangle(10, 10, 990, 990))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 3 rectangles (0, 0, 10, 10) and (10, 10, 990,
     * 990) and (1000, 1000, 24, 24) to get (1000, 1000, 24, 24)
     */
    @Test
    public void testRect3ValidBottomRightRegionSearch() {
        List<KVPair<String, Rectangle>> found =
                rect3.regionSearch(1000, 1000, 24, 24);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("C",
                new Rectangle(1000, 1000, 24, 24))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (0, 0, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithUpperLeftRegionSearch() {
        rectEmpty.insert(new KVPair<>("UL",
                new Rectangle(0, 0, 10, 10)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (1000, 0, 24, 24) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithUpperRightRegionSearch() {
        rectEmpty.insert(new KVPair<>("UR",
                new Rectangle(1000, 0, 24, 24)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (0, 1000, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithBottomLeftRegionSearch() {
        rectEmpty.insert(new KVPair<>("BL",
                new Rectangle(0, 1000, 10, 10)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (1000, 1000, 24, 24) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithBottomRightRegionSearch() {
        rectEmpty.insert(new KVPair<>("BR",
                new Rectangle(1000, 1000, 24, 24)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (0, 100, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidLeftRegionSearch() {
        rectEmpty.insert(new KVPair<>("ML",
                new Rectangle(0, 100, 10, 10)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (100, 0, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidUpperRegionSearch() {
        rectEmpty.insert(new KVPair<>("MU",
                new Rectangle(100, 0, 10, 10)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (1000, 100, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidRightRegionSearch() {
        rectEmpty.insert(new KVPair<>("MR",
                new Rectangle(1000, 100, 10, 10)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (100, 1000, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidBottomRegionSearch() {
        rectEmpty.insert(new KVPair<>("MB",
                new Rectangle(100, 1000, 10, 10)));

        assertEquals(0,
                rectEmpty.regionSearch(10, 10, 990, 990).size());
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (0, 0, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithUpperLeftIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("ULI",
                new Rectangle(0, 0, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("ULI",
                new Rectangle(0, 0, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (990, 0, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithUpperRightIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("URI",
                new Rectangle(990, 0, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("URI",
                new Rectangle(990, 0, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (0, 990, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithBottomLeftIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("BLI",
                new Rectangle(0, 990, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("BLI",
                new Rectangle(0, 990, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (990, 990, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithBottomRightIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("BRI",
                new Rectangle(990, 990, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("BRI",
                new Rectangle(990, 990, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (5, 100, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidLeftIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("MLI",
                new Rectangle(5, 100, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("MLI",
                new Rectangle(5, 100, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (15, 0, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidUpperIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("MUI",
                new Rectangle(15, 0, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("MUI",
                new Rectangle(15, 0, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (990, 100, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidRightIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("MRI",
                new Rectangle(990, 100, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("MRI",
                new Rectangle(990, 100, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (100, 990, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMidBottomIntersectRegionSearch() {
        rectEmpty.insert(new KVPair<>("MBI",
                new Rectangle(100, 990, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("MBI",
                new Rectangle(100, 990, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (10, 10, 15, 15) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithUpperLeftInsideRegionSearch() {
        rectEmpty.insert(new KVPair<>("ULIN",
                new Rectangle(10, 10, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("ULIN",
                new Rectangle(10, 10, 15, 15))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (990, 10, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithUpperRightInsideRegionSearch() {
        rectEmpty.insert(new KVPair<>("URIN",
                new Rectangle(990, 10, 10, 10)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("URIN",
                new Rectangle(990, 10, 10, 10))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (10, 990, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithBottomLeftInsideRegionSearch() {
        rectEmpty.insert(new KVPair<>("BLIN",
                new Rectangle(10, 990, 10, 10)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("BLIN",
                new Rectangle(10, 990, 10, 10))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle  (990, 990, 10, 10) to get not found
     * and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithBottomRightInsideRegionSearch() {
        rectEmpty.insert(new KVPair<>("BRIN",
                new Rectangle(990, 990, 10, 10)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("BRIN",
                new Rectangle(990, 990, 10, 10))));
    }

    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 1 rectangle (20, 20, 10, 10) and region (10, 10,
     * 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithInsideRegionSearch() {
        rectEmpty.insert(new KVPair<>("IN",
                new Rectangle(20, 20, 10, 10)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(1, found.size());
        assertTrue(found.contains(new KVPair<>("IN",
                new Rectangle(20, 20, 10, 10))));
    }


    /**
     * Tests if region search is performed for a valid region (10, 10, 990, 990)
     * on a database with 3 rectangle (20, 20, 10, 10), (0, 0, 10, 10) and
     * (990, 990, 15, 15) and region (10, 10, 990, 990)
     */
    @Test
    public void testRect3ValidMidRegionWithMultipleRegionSearch() {
        rectEmpty.insert(new KVPair<>("IN",
                new Rectangle(20, 20, 10, 10)));

        rectEmpty.insert(new KVPair<>("UL",
                new Rectangle(0, 0, 10, 10)));

        rectEmpty.insert(new KVPair<>("BRI",
                new Rectangle(990, 990, 15, 15)));

        List<KVPair<String, Rectangle>> found =
                rectEmpty.regionSearch(10, 10, 990, 990);

        assertEquals(2, found.size());
        assertTrue(found.contains(new KVPair<>("IN",
                new Rectangle(20, 20, 10, 10))));
        assertTrue(found.contains(new KVPair<>("BRI",
                new Rectangle(990, 990, 15, 15))));
    }

    /**
     * Tests if intersection is performed for an empty database
     */
    @Test
    public void testEmptyIntersection() {
        assertEquals(0, rectEmpty.intersections().size());
    }

    /**
     * Tests if intersection is performed for database with 3 rectangles  (0,
     * 0, 10, 10) and (10, 10, 990, 990) and (1000, 1000, 24, 24)
     */
    @Test
    public void testRect3Intersection() {
        assertEquals(0, rect3.intersections().size());
    }

    /**
     * Tests if intersection is performed for database with 1 rectangle (10,
     * 10, 990, 990)
     */
    @Test
    public void testRectMidIntersection() {
        assertEquals(0, rectMid.intersections().size());
    }

    /**
     * Tests if intersection is performed for database with 2 rectangle (10,
     * 10, 990, 990) and (0, 0, 15, 15)
     */
    @Test
    public void testRectMidWithUpperLeftIntersectIntersection() {

        rectMid.insert(new KVPair<>("ULI",
                new Rectangle(0, 0, 15, 15)));

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>> pairs =
                rectMid.intersections();

        assertEquals(2, pairs.size());
        assertTrue(pairs.contains(new Pair<>(new KVPair<>("ULI",
                new Rectangle(0, 0, 15, 15)),
                new KVPair<>("B",
                        new Rectangle(10, 10, 990, 990)))));
    }


    /**
     * Tests if intersection is performed for database with 2 rectangle (10,
     * 10, 990, 990) and (990, 990, 15, 15)
     */
    @Test
    public void testRectMidWithBottomRightIntersectIntersection() {

        rectMid.insert(new KVPair<>("BRI",
                new Rectangle(990, 990, 15, 15)));

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>> pairs =
                rectMid.intersections();

        assertEquals(2, pairs.size());
        assertTrue(pairs.contains(new Pair<>(new KVPair<>("BRI",
                new Rectangle(990, 990, 15, 15)),
                new KVPair<>("B",
                        new Rectangle(10, 10, 990, 990)))));
    }


    /**
     * Tests if intersection is performed for database with 2 rectangle (10,
     * 10, 990, 990) and (0, 100, 15, 15)
     */
    @Test
    public void testRectMidWithMidLeftIntersectIntersection() {

        rectMid.insert(new KVPair<>("MLI",
                new Rectangle(0, 100, 15, 15)));

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>> pairs =
                rectMid.intersections();

        assertEquals(2, pairs.size());
        assertTrue(pairs.contains(new Pair<>(new KVPair<>("MLI",
                new Rectangle(0, 100, 15, 15)),
                new KVPair<>("B",
                        new Rectangle(10, 10, 990, 990)))));
    }


    /**
     * Tests if intersection is performed for database with 2 rectangle (10,
     * 10, 990, 990) and (990, 100, 15, 15)
     */
    @Test
    public void testRectMidWithMidRightIntersectIntersection() {

        rectMid.insert(new KVPair<>("MRI",
                new Rectangle(990, 100, 15, 15)));

        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>> pairs =
                rectMid.intersections();

        assertEquals(2, pairs.size());
        assertTrue(pairs.contains(new Pair<>(new KVPair<>("MRI",
                new Rectangle(990, 100, 15, 15)),
                new KVPair<>("B",
                        new Rectangle(10, 10, 990, 990)))));
    }


//    /**Tests if intersection is performed for database with 2 rectangle (10,
//     * 10, 990, 990) and (10, 10, 15, 15)*/
//    @Test
//    public void testRectMidWithUpperLeftInsideMidIntersection(){
//        rect_mid.insert(new KVPair<>("ULIN",
//                new Rectangle(10, 10, 15, 15)));
//
//        assertEquals(0, rect_mid.intersections().size());
//    }


    /**
     * Tests if intersection is performed for database with 2 rectangle (10,
     * 10, 990, 990) and (1000, 100, 15, 15)
     */
    @Test
    public void testRectMidWithMidRightIntersection() {
        rectMid.insert(new KVPair<>("MR",
                new Rectangle(1000, 100, 15, 15)));

        assertEquals(0, rectMid.intersections().size());
    }


    /**
     * Tests if intersection is performed for database with 2 rectangle (10,
     * 10, 990, 990) and (1000, 100, 15, 15)
     */
    @Test
    public void testRectMidWithMidLeftIntersection() {
        rectMid.insert(new KVPair<>("ML",
                new Rectangle(0, 100, 10, 10)));

        assertEquals(0, rectMid.intersections().size());
    }


//    /**Tests if intersection is performed for database with 2 rectangle (10,
//     * 10, 990, 990) and (990, 990, 10, 10)*/
//    @Test
//    public void testRectMidWithBottomRightInsideMidIntersection(){
//
//        rect_mid.insert(new KVPair<>("BRIN",
//                new Rectangle(990, 990, 10, 10)));
//
//        assertEquals(0, rect_mid.intersections().size());
//    }


//    /**Tests if intersection is performed for database with 2 rectangle (10,
//     * 10, 990, 990) and (20, 20, 10, 10)*/
//    @Test
//    public void testRectMidWithInsideMidIntersection(){
//
//        rect_mid.insert(new KVPair<>("IN",
//                new Rectangle(20, 20, 10, 10)));
//
//        assertEquals(0, rect_mid.intersections().size());
//    }


//    /**Tests if intersection is performed for database with 6 rectangle (10,
//     * 10, 990, 990), (990, 100, 15, 15), (0, 0, 10, 10), (1000, 1000, 24, 24)
//     *, (990, 10, 10, 10) and (0, 990, 15, 15)*/
//    @Test
//    public void testRectMidWithMultipleIntersection(){
//
//        rect_3.insert(new KVPair<>("MRI",
//                new Rectangle(990, 100, 15, 15)));
//
//        rect_3.insert(new KVPair<>("BLI",
//                new Rectangle(0, 990, 15, 15)));
//
//        rect_3.insert(new KVPair<>("URIN",
//                new Rectangle(990, 10, 10, 10)));
//
//        List<Pair<KVPair<String, Rectangle>, KVPair<String, Rectangle>>> pairs =
//                rect_3.intersections();
//
//        assertEquals(4, pairs.size());
//
//        assertTrue(pairs.contains(new Pair<>(new KVPair<>("BLI",
//                new Rectangle(0, 990, 15, 15)),
//                new KVPair<>("B",
//                        new Rectangle(10, 10, 990, 990)))));
//
//        assertTrue(pairs.contains(new Pair<>(new KVPair<>("MRI",
//                new Rectangle(990, 100, 15, 15)),
//                new KVPair<>("B",
//                        new Rectangle(10, 10, 990, 990)))));
//    }


    /**
     * Test if dump of empty database produces correct string
     */
    @Test
    public void testEmptyDump() {
        String dump = rectEmpty.dump();
        assertTrue(dump.contains("SkipList dump:"));
        assertTrue(dump.contains("Value (null)"));
        assertTrue(dump.contains("SkipList size is: 0"));
    }


    /**
     * Test if dump of database with 1 rectangle produces correct string
     */
    @Test
    public void testMidDump() {
        String dump = rectMid.dump();
        assertTrue(dump.contains("SkipList dump:"));
        assertTrue(dump.contains("Node has depth"));
        assertTrue(dump.contains("Value (B, 10, 10, 990, 990)"));
        assertTrue(dump.contains("SkipList size is: 1"));
    }


    /**
     * Test if dump of database with 3 rectangles produces correct string
     */
    @Test
    public void testRect3Dump() {
        String dump = rect3.dump();
        assertTrue(dump.contains("SkipList dump:"));
        assertTrue(dump.contains("Node has depth"));
        assertTrue(dump.contains("Value (A, 0, 0, 10, 10)"));
        assertTrue(dump.contains("Value (B, 10, 10, 990, 990)"));
        assertTrue(dump.contains("Value (C, 1000, 1000, 24, 24)"));
        assertTrue(dump.contains("SkipList size is: 3"));
    }
}