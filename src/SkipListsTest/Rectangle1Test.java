package SkipListsTest;

import SkipLists.Rectangle1;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * This class is used to test the Rectangle1 class and its ability to read
 * commands from file and write them to output
 */
public class Rectangle1Test {

    //Stream to read the output in assertion from
    private final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();
    //The original output value
    private final PrintStream originalOut = System.out;


    /**
     * Check that Rectangle1 class can be created
     * */
    @BeforeClass
    public static void Rectangle1Creation() {
        assertNotNull(new Rectangle1());
    }

    /**
     * Set the system.setOut to write to the outContent variable before each
     * test
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }


    /**
     * Reset the system.setOut to the original output stream after each test
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    /**
     * Test if null file writes "File expected" to output window
     */
    @Test
    public void testNullFile() {
        String[] str = new String[1];
        str[0] = null;
        Rectangle1.main(str);

        String outString = outContent.toString();
        assertTrue(outString.contains("File expected"));
    }


    /**
     * Test if invalid "a" file writes "Invalid file: a" and stack error to
     * output window
     */
    @Test
    public void testInvalidFile() {
        String[] str = new String[1];
        str[0] = "a";
        Rectangle1.main(str);

        String outString = outContent.toString();
        assertTrue(outString.contains("Invalid file: a"));
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
        Rectangle1.main(str);

        deleteFile(fileName);

        assertEquals("", outContent.toString());
    }


    /**
     * Test if valid file "testInsertFile" gives empty console output
     *
     * @throws IOException if the file for test cannot be written on
     */
    @Test
    public void testInsertFile() throws IOException {
        String fileName = "testInsertFile.txt";
        createFile(fileName);

        FileWriter fr = new FileWriter(fileName);
        fr.write("insert A1 1 1 1 1");
        fr.close();

        String[] str = new String[1];
        str[0] = fileName;
        Rectangle1.main(str);

        deleteFile(fileName);

        assertTrue(outContent.toString().
                contains("Rectangle inserted: (A1, 1, 1, 1, 1)"));
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
        fr.write("insert A1 1 1 1 1");
        fr.write("\ndump");
        fr.close();

        String[] str = new String[1];
        str[0] = fileName;
        Rectangle1.main(str);

        deleteFile(fileName);

        String out = outContent.toString();
        assertTrue(out.contains("Rectangle inserted: (A1, 1, 1, 1, 1)"));
        assertTrue(out.contains("SkipList dump:"));
        assertTrue(out.contains("Value (null)"));
        assertTrue(out.contains("Value (A1, 1, 1, 1, 1)"));
        assertTrue(out.contains("SkipList size is: 1"));
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