// 206947996 Eyal Bouganim
package Geometry;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Class Point contains members x,y.
 * Distance measuring, points-equal check, getters.
 */
public class Point {
    // Fields
    private double x;
    private double y;

    /**
     * Constructor for the point class.
     * @param x x of point
     * @param y y of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance between current point to a different point.
     * @param other point to check distance with
     * @return double
     */
    public double distance(Point other) {
        double xDist = this.x - other.getX();
        double yDist = this.y - other.getY();
        return Math.sqrt((xDist * xDist) + (yDist * yDist));
    }

    /**
     * Checks weather the current point and a different point are equal.
     * @param other point to check if equal
     * @return boolean
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * returns the x of the point.
     * @return double
     */
    public double getX() {
        return this.x;
    }
    /**
     * returns the y of the point.
     * @return double
     */
    public double getY() {
        return this.y;
    }

    /**
     * method to set the x value of the point.
     * @param x double
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * method to set the y value of the point.
     * @param y double
     */
    public void setY(double y) {
        this.y = y;
    }

}