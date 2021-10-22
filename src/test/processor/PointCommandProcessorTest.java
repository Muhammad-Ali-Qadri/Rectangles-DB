package test.processor;

import org.junit.Before;
import org.junit.Test;
import processor.PointCommandProcessor;
import processor.Processor;
import processor.Rectangle;

import static org.junit.Assert.*;

/**
 * @author Cristian Diaz
 * @version 1
 */
public class PointCommandProcessorTest {
    private Processor processor;

    /**
     * Set up variables for testing.
     * Executed before every test case.
     */
    @Before
    public void setUp() {
        processor = new PointCommandProcessor();
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
     * Test invalid command string
     */
    @Test
    public void testABCProcess() {
        assertEquals("", processor.process("abc"));
    }


    /**
     * Test a simple valid insertion
     */
    @Test
    public void testValidInsert() {
        assertEquals("Point inserted: (A1, 1, 1)",
                processor.process("insert A1 1 1"));
    }

    /**
     * Test invalid insert
     */
    @Test
    public void testInvalidInsert() {
        assertEquals("Point rejected: (A1, -1, 1)",
                processor.process("insert A1 -1 1"));
    }

    /**
     * Test valid remove by Key
     */
    @Test
    public void testRemoveByKey() {
        processor.process("insert C1    1   1");
        String output = processor.process("remove C1");
        assertEquals("Point removed: (C1, 1, 1)", output
        );
        assertEquals(processor.process("remove C9"), "Point not removed: C9");
    }

    /**
     * Test valid remove by point
     */
    @Test
    public void testRemoveByValue() {
        processor.process("insert C1    1   1");
        String output = processor.process("remove 1 1");
        assertEquals("Point removed: (C1, 1, 1)", output
        );
        String notFound = processor.process("remove 5 2");
        String invalidPoint = processor.process("remove 2 -5");
        assertEquals(notFound, "Point not found: (5, 2)");
        assertEquals(invalidPoint, "Point rejected: (2, -5)" );
    }

    /**
     * Fully tests region search
     */
    @Test
    public void testRegionSearch() {
        processor.process("insert C1    1   1");
        processor.process("insert C2 1 1");
        String expected = "Points intersecting region (0, 0, 40, 40)\n" +
                "Point found: (C1, 1, 1)\n" +
                "Point found: (C2, 1, 1)\n" +
                "1 quadtree nodes visited";
        String actualOutput = processor.process("regionsearch 0 0 40 40");
        String rejectRect = processor.process("regionsearch 0 0 -5 40");
        String rejectRect2 = processor.process("regionsearch 0 0 5 -40");
        String rejectRect3 = processor.process("regionsearch 1023 1023 5 5");
        String rejectOutput1 =  "Rectangle " + "rejected: (0, 0, -5, 40" + ")";
        String rejectOutput2 =  "Rectangle " + "rejected: (0, 0, 5, -40" + ")";
        Rectangle region = new Rectangle(1023, 1023, 1, 1);
        String expectedOutput3;
        expectedOutput3 = "Points intersecting region (1023, 1023, 5, 5)\n" +
            "\n1 quadtree nodes visited";
        assertEquals(actualOutput, expected);
        assertEquals(rejectRect, rejectOutput1);
        assertEquals(rejectRect2, rejectOutput2);
        assertEquals(rejectRect3, expectedOutput3);
    }

    /**
     * tests search
     */
    @Test
    public void testSearch() {
        processor.process("insert C1    1   1");
        processor.process("insert C1    4   5");
        String pointNotFound = processor.process("search C4");
        String expectedOutput1 = "Point not found: C4";
        assertEquals(pointNotFound, expectedOutput1);
        String pointFound = processor.process("search C1");
        String expectedOutput2 = "Found (C1, 4, 5)\n" +
                "Found (C1, 1, 1)";
        assertEquals(pointFound, expectedOutput2);
    }

    /**
     * tests duplicates
     */
    @Test
    public void testDuplicates() {
        processor.process("insert C1    1   1");
        processor.process("insert C2    1   1");
        String pointNotFound = processor.process("duplicates");
        String expectedOutput1 = "Duplicate points:\n" +
                "(1, 1)";
        assertEquals(pointNotFound, expectedOutput1);
    }

    /**
     * Test dump method
     */
    @Test
    public void testDump() {
        processor.process("insert C1    1   1");
        processor.process("insert C2    1   1");
        String testOutput = processor.process("dump");
        boolean test;
        test = testOutput.contains("QuadTree Size: 1 QuadTree Nodes Printed.");
        assertTrue(test);
    }

}
