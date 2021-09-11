import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    Rectangle rectangle;

    @Before
    public void setUp() {
        rectangle = new Rectangle(10, 20, 30, 40);
    }

    @Test
    public void testToString() {
        assertEquals("10, 20, 30, 40", rectangle.toString());
    }
}