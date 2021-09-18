import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class is responsible for testing each scenario of each method in
 * the RectangleCommandProcessor class
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class RectangleCommandProcessorTest {

    // The processor under test
    private Processor processor;

    /**
     * Set up variables for testing.
     * Executed before every test case.
     */
    @Before
    public void setUp() {
        processor = new RectangleCommandProcessor();
    }


    /**
     * Test if null can be an input string to the processor
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullProcess() {
        processor.process(null);
        fail();
    }


    /**
     * Test if invalid string "abc" can be an input string to the processor
     */
    @Test
    public void testABCProcess() {
        assertEquals("", processor.process("abc"));
    }


    /**
     * Test if valid operation "insert A1 1 1 1 1" can be an input string to
     * the processor
     */
    @Test
    public void testValidInsertProcess() {
        assertEquals("Rectangle inserted: (A1, 1, 1, 1, 1)",
                processor.process("insert A1 1 1 1 1"));
    }


    /**
     * Test if invalid operation "insert B1 -1 1 1 1" can be an input string to
     * the processor
     */
    @Test
    public void testInvalidInsertProcess() {
        assertEquals("Rectangle rejected: (B1, -1, 1, 1, 1)",
                processor.process("insert B1 -1 1 1 1"));
    }


    /**
     * Test if valid operation "insert C1    1   1   1     1" can be an input
     * string to the processor
     */
    @Test
    public void testValidSpacedInsertProcess() {
        assertEquals("Rectangle inserted: (C1, 1, 1, 1, 1)",
                processor.process("insert C1    1   1   1     1"));
    }


    /**
     * Test if invalid operation "remove C1" can be an input string to the
     * processor
     */
    @Test
    public void testValidNotFoundRemoveProcess() {
        assertEquals("Rectangle not removed: (C1)",
                processor.process("remove C1"));
    }


    /**
     * Test if valid operation "remove C1" can be an input string to the
     * processor
     */
    @Test
    public void testValidRemoveProcess() {
        processor.process("insert C1    1   1   1     1");
        assertEquals("Rectangle removed: (C1, 1, 1, 1, 1)",
                processor.process("remove C1"));
    }


    /**
     * Test if invalid operation "remove 1, 1, 1, 1" can be an input string to
     * the processor
     */
    @Test
    public void testValidNotFoundRemoveByValueProcess() {
        assertEquals("Rectangle not removed: (1, 1, 1, 1)",
                processor.process("remove 1 1 1 1"));
    }


    /**
     * Test if invalid operation "remove -1, -1, 1, 1" can be an input string to
     * the processor
     */
    @Test
    public void testInvalidRemoveByValueProcess() {
        assertEquals("Rectangle rejected: (-1, -1, 1, 1)",
                processor.process("remove -1 -1 1 1"));
    }


    /**
     * Test if valid operation "remove 1, 1, 1, 1" can be an input string to the
     * processor
     */
    @Test
    public void testValidRemoveByValueProcess() {
        processor.process("insert C1    1   1   1     1");
        assertEquals("Rectangle removed: (C1, 1, 1, 1, 1)",
                processor.process("remove 1 1 1 1"));
    }


    /**
     * Test if valid operation "dump" can be an input string to the
     * processor
     */
    @Test
    public void testEmptyDumpProcess() {
        String dump = processor.process("dump");
        assertTrue(dump.contains("SkipList dump:"));
        assertTrue(dump.contains("Node has depth 1, Value (null)"));
        assertTrue(dump.contains("SkipList size is: 0"));
    }


    /**
     * Test if valid operation "dump" can be an input string to the
     * processor with 1 rectangle
     */
    @Test
    public void testSingleRectangleDumpProcess() {
        processor.process("insert C1    1   1   1     1");

        String dump = processor.process("dump");
        assertTrue(dump.contains("SkipList dump:"));
        assertTrue(dump.contains("Value (null)"));
        assertTrue(dump.contains("Value (C1, 1, 1, 1, 1)"));
        assertTrue(dump.contains("SkipList size is: 1"));
    }


    /**
     * Test if valid operation "search A1" can be an input string to the
     * processor with no rectangles
     */
    @Test
    public void testValidNotFoundSearchProcess() {
        assertEquals("Rectangle not found: A1",
                processor.process("Search A1"));
    }


    /**
     * Test if valid operation "search A1" can be an input string to the
     * processor with 1 rectangles
     */
    @Test
    public void testValidSearchProcess() {
        processor.process("insert A1 1 1 1 1");
        assertEquals("Rectangles found:\n(A1, 1, 1, 1, 1)",
                processor.process("Search A1"));
    }


    /**
     * Test if valid operation "search A1" can be an input string to the
     * processor with 2 rectangles having A1 key
     */
    @Test
    public void testValidSearchDuplicateProcess() {
        processor.process("insert A1 1 1 1 1");
        processor.process("insert A1 2 2 1 1");
        assertEquals("Rectangles found:\n(A1, 2, 2, 1, 1)\n(A1, 1, 1, 1, 1)",
                processor.process("Search A1"));
    }


    /**
     * Test if valid operation "intersections A1" can be an input string to the
     * processor with empty processor
     */
    @Test
    public void testValidEmptyIntersectionsProcess() {
        assertEquals("Intersections pairs:",
                processor.process("intersections"));
    }


    /**
     * Test if valid operation "intersections A1" can be an input string to the
     * processor with 2 rectangles
     */
    @Test
    public void testValidTwoIntersectionsProcess() {
        processor.process("insert A1 1 1 2 2");
        processor.process("insert A2 2 2 2 2");
        assertEquals("Intersections " +
                     "pairs:\n(A1, 1, 1, 2, 2 | A2, 2, 2, 2, 2)" +
                     "\n(A2, 2, 2, 2, 2 | A1, 1, 1, 2, 2)",
                processor.process("intersections"));
    }


    /**
     * Test if invalid operation "regionsearch 1, 1, -1, 1" can be an input
     * string to the processor empty processor
     */
    @Test
    public void testInvalidRegionSearchProcess() {
        assertEquals("Rectangle rejected: (1, 1, -1, 1)",
                processor.process("regionsearch 1 1 -1 1"));
    }


    /**
     * Test if valid operation "regionsearch 1, 1, 1, 1" can be an input
     * string to the processor empty processor
     */
    @Test
    public void testValidEmptyRegionSearchProcess() {
        assertEquals("Rectangles intersecting region (1, 1, 1, 1):",
                processor.process("regionsearch 1 1 1 1"));
    }


    /**
     * Test if valid operation "regionsearch 0, 0, 10, 10" can be an input
     * string to the processor 1 rectangle
     */
    @Test
    public void testValidSingleRegionSearchProcess() {
        processor.process("insert A2 2 2 2 2");

        assertEquals("Rectangles intersecting region (0, 0, 10, 10):" +
                     "\n(A2, 2, 2, 2, 2)",
                processor.process("regionsearch 0 0 10 10"));
    }
}