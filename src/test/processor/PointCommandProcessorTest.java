package test.processor;

import org.junit.Before;
import org.junit.Test;
import processor.Processor;
import processor.PointCommandProcessor;

import static org.junit.Assert.*;

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
        assertEquals("Point removed: (C1, 1, 1)",output
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
        assertEquals("Point removed: (C1, 1, 1)",output
        );
        assertEquals(processor.process("remove 5 2"), "Point not found: (5, 2)");
    }

}
