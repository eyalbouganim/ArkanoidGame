// 206947996 Eyal Bouganim
package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Class Rectangle contains members upperLeft, width, height.
 * Checks intersction points with a line, getters.
 */
public class Rectangle {

    private final Point upperLeft;
    private final double width;
    private final double height;


    /**Create a new rectangle with location and width/height. (Constructor)
     *
     * @param upperLeft Point
     * @param width double
     * @param height double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line Line to check intersection with
     * @return List of Points
     */
    public List<Point> intersectionPoints(Line line) {
        // Creating a new Array List, storing the lines of the rectangle there
        List<Point> myList = new ArrayList<>();
        Line upperLine = new Line(this.upperLeft, this.getUpperRight());
        Line rightLine = new Line(this.getUpperRight(), this.getLowerRight());
        Line lowerLine = new Line(this.getLowerRight(), this.getLowerLeft());
        Line leftLine = new Line(this.getLowerLeft(), this.upperLeft);

        // checking if there's intersection of the line with each line of the rectangle
        if (upperLine.intersectionWith(line) != null) {
            myList.add(upperLine.intersectionWith(line));
        }

        if (rightLine.intersectionWith(line) != null && !myList.contains(rightLine.intersectionWith(line))) {
            myList.add(rightLine.intersectionWith(line));
        }

        if (lowerLine.intersectionWith(line) != null && !myList.contains(lowerLine.intersectionWith(line))) {
            myList.add(lowerLine.intersectionWith(line));
        }

        if (leftLine.intersectionWith(line) != null && !myList.contains(leftLine.intersectionWith(line))) {
            myList.add(leftLine.intersectionWith(line));
        }

        return myList;
    }


    /**
     * Get the width of the rectangle.
     * @return double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the rectangle.
     * @return double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return Point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-right point of the rectangle.
     * @return Point
     */
    public Point getUpperRight() {
        Point upperLeft = getUpperLeft();
        return new Point(upperLeft.getX() + this.width, upperLeft.getY());
    }

    /**
     * Returns the lower-left point of the rectangle.
     * @return Point
     */
    public Point getLowerLeft() {
        Point upperLeft = getUpperLeft();
        return new Point(upperLeft.getX(), upperLeft.getY() + this.height);
    }

    /**
     * Returns the lower-right point of the rectangle.
     * @return Point
     */
    public Point getLowerRight() {
        Point lowerLeft = this.getLowerLeft();
        return new Point(lowerLeft.getX() + this.width, lowerLeft.getY());
    }

}