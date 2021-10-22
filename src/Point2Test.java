import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author Cristian Diaz
 * @version 1
 * Tests Point2 entry point on a few scenarios
 */
public class Point2Test {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Set up class for testing.
     * Executed before every test case.
     */

    @BeforeClass
    public static void point2Creation() {
        assertNotNull(new Point2());
    }

    /**
     * Set up variables for testing.
     * Executed before every test case.
     */
    @Before
    public void setUp() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    /**
     * After each test, restore outputstreams to original state
     */
    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Test if null file writes "File expected" to output window
     */
    @Test
    public void testNullFile() {
        String[] str = new String[1];
        str[0] = null;
        try {
            Point2.main(str);
            String outString = out.toString();
            assertTrue(outString.contains("File expected"));
        }
        catch (Exception e) {
            String output = out.toString();
            assertTrue(output.contains("Invalid file"));
        }
    }


    /**
     * Test if invalid "a" file writes "Invalid file: a" and stack error to
     * output window
     */
    @Test
    public void testInvalidFile() {
        String[] str = new String[1];
        str[0] = "a";
        try {
            Point2.main(str);
            String outString = out.toString();
            assertTrue(outString.contains("Invalid file: a"));
        }
        catch (FileNotFoundException e) {
            String output = out.toString();
            assertTrue(output.contains("Invalid file"));
        }
    }


    /**
     * Test if valid file "testEmptyFile" gives empty console output
     */
    @Test
    public void testEmptyFile() {
        String fileName = "testInsertFile.txt";
        createFile(fileName);

        String[] str = new String[1];
        str[0] = fileName;
        try {
            Point2.main(str);

            deleteFile(fileName);

            assertEquals("", out.toString());
        }
        catch (Exception e) {
            String output = out.toString();
            assertTrue(output.contains("Invalid file"));
        }
    }


    /**
     * Test if valid file "testInsertFile" gives console output for inserting
     * a rectangle
     *
     * @throws IOException if the file for test cannot be written on
     */
    @Test
    public void testInsertFile() throws IOException {
        String fileName = "testInsertFile.txt";
        createFile(fileName);

        FileWriter fr = new FileWriter(fileName);
        fr.write("insert A1 1 1");
        fr.close();

        String[] str = new String[1];
        str[0] = fileName;
        Point2.main(str);

        deleteFile(fileName);

        assertTrue(out.toString().
                contains("Point inserted: (A1, 1, 1)"));
    }


    /**
     * Test if valid file "testInsertFile" gives console output for inserting
     * a rectangle
     *
     * @throws IOException if the file for test cannot be written on
     */
    @Test
    public void testInsertFileWithEmptyNextLine() throws IOException {
        String fileName = "testInsertFileWithEmptyNextLine.txt";
        createFile(fileName);

        FileWriter fr = new FileWriter(fileName);
        fr.write("insert A1 1 1\r\n  ");
        fr.close();

        String[] str = new String[1];
        str[0] = fileName;
        Point2.main(str);

        deleteFile(fileName);

        assertTrue(out.toString().
                contains("Point inserted: (A1, 1, 1)"));
    }


    /**
     * Test if valid file "testInsertFile" gives empty console output
     *
     * @throws IOException if the file for test cannot be written on
     */
    @Test
    public void testInsertDumpFile() throws IOException {
        String fileName = "testInsertDumpFile.txt";
        createFile(fileName);

        FileWriter fr = new FileWriter(fileName);
        fr.write("insert A1 1 1");
        fr.write("\ndump");
        fr.close();

        String[] str = new String[1];
        str[0] = fileName;
        Point2.main(str);

        deleteFile(fileName);

        String output = out.toString();
        assertTrue(output.contains("Point inserted: (A1, 1, 1)"));
        assertTrue(output.contains("SkipList dump:"));
        assertTrue(output.contains("Value (null)"));
        assertTrue(output.contains("Value (A1, 1, 1)"));
        assertTrue(output.contains("SkipList size is: 1"));
    }

    /**
     * Creates a file for testing
     *
     * @param name the name of the file to be created
     */
    private void createFile(String name) {
        try {
            File file = new File(name);
            file.createNewFile();
        }
        catch (IOException ex) {
            fail();
        }

    }

    /**
     * Deletes a file for testing
     *
     * @param name the name of the file to be deleted
     */
    private void deleteFile(String name) {
        File file = new File(name);
        file.delete();
    }


}
