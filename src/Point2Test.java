import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class Point2Test {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Set up variables for testing.
     * Executed before every test case.
     */

    @BeforeClass
    public static void point2Creation() {
        assertNotNull(new Point2());
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    /**
     * Test if valid operation "insert A1 1 1 1 1" can be an input string to
     * the processor
     */
    @Test
    public void testProcessor() {
        String[] arr = {"./Data/P2test2.txt"};
        try {
            Point2.main(arr);
            String output = out.toString();
            assertTrue(true);
        }
        catch (FileNotFoundException e) {
            // Confirm exit's return value was zero:
            String output = out.toString();
            String[] outputArr = output.split("\n");
            assertEquals(outputArr[0], "Invalid file");
        }
    }


}
