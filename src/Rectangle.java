/**
 * Change the implementation of some methods of java.awt.Rectangle
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class Rectangle extends java.awt.Rectangle {

    /**
     * Initialize the rectangle
     *
     * @param x the x coordinate of the rectangle
     * @param y the y coordinate of the rectangle
     * @param w the width of the rectangle
     * @param h the height of the rectangle
     */
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
