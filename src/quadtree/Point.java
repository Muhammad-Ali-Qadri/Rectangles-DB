package quadtree;

import java.util.Objects;
//TODO: Write test cases

/**
 * Class representing a point on an x,y plain
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class Point {
    private final int x; // x coordinate
    private final int y; // y coordinate

    /**
     * Represents the constructor for this class. Initializes the x and y
     * coordinates
     *
     * @param xCord x coordinate
     * @param yCord y coordinate
     */
    public Point(int xCord, int yCord) {
        x = xCord;
        y = yCord;
    }

    /**
     * Converts this object into its string representation. If x = 0, y = 0.
     * Then its string would result in (0, 0)
     *
     * @return string representation of this object.
     */
    @Override
    public String toString() {
        return x + ", " + y;
    }

    /**
     * Gets x coordinate
     *
     * @return value in x coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * Gets y coordinate
     *
     * @return value in y coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * Checks if the input object is equal to this object
     *
     * @param o the object to compare this with
     * @return returns true if passed object is equal to this otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    /**
     * Generates hashcode of current object.
     *
     * @return the hashcode of current object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
