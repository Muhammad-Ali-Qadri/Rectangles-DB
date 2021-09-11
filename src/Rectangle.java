/**
 * Change the implementation of some methods of java.awt.Rectangle
 *
 * @author Muhammad Ali Qadri
 */
public class Rectangle extends java.awt.Rectangle {

    public Rectangle(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    /**
     * Override the implementation of toString() for rectangle
     *
     * @return string representation of this object, for example: "x, y, width, height"
     */
    @Override
    public String toString() {
        return x + ", " + y + ", " + width + ", " + height;
    }
}
