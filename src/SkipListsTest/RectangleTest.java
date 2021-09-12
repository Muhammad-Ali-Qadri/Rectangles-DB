package SkipListsTest;

import SkipLists.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class is responsible for testing each scenario of each method in
 * the SkipLists.Rectangle class
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class RectangleTest {

    private Rectangle rectangle; //contains rectangle with x=10, y=20, width=30,
    // height=40

    /**
     * Set up the pair objects with values to test before
     * every test case execution.
     */
    @Before
    public void setUp() {
        rectangle = new Rectangle(10, 20, 30, 40);
    }

    /**
     * Tests if the rectangle produces the correct string representation
     */
    @Test
    public void testToString() {
        assertEquals("10, 20, 30, 40", rectangle.toString());
    }
}