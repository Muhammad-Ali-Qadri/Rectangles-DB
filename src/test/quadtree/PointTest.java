package test.quadtree;

import org.junit.Before;
import org.junit.Test;
import quadtree.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test point class
 */
public class PointTest {

    //point under test
    private Point point;


    /**
     * Apply default value to point
     */
    @Before
    public void setup() {
        point = new Point(0, 0);
    }


    /**
     * check point string: x, y
     */
    @Test
    public void testToString() {
        assertEquals("0, 0", point.toString());
    }

    /**
     * test X coordinate
     */
    @Test
    public void testGetX() {
        assertEquals(0, point.getX());
    }


    /**
     * test y coordinate
     */
    @Test
    public void testGetY() {
        assertEquals(0, point.getY());
    }


    /**
     * test if string equals Point
     */
    @Test
    public void testPointStringEquals() {
        assertNotEquals("", point);
    }


    /**
     * test if different point with different x,y equals Point
     */
    @Test
    public void testPointDifferentEquals() {
        assertNotEquals(new Point(1, 0), point);
    }


    /**
     * test if same point equals itself
     */
    @Test
    public void testPointSameEquals() {
        assertEquals(point, point);
    }

    /**
     * test if another point equals point
     */
    @Test
    public void testPointTrueEqualEquals() {
        assertEquals(new Point(0, 0), point);
    }
}