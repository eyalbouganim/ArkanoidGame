// 206947996 Eyal Bouganim
package Geometry;

import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Line is class that contains members Start and End points.
 * Length of line, mid-point, start&end points,
 * checks intersection of lines, returns intersection point,
 * checks line-equality
 */
public class Line {
    // Fields
    private final Point start;
    private final Point end;

    /**
     * Constructor to define Start & End points.
     * @param start defines start point
     * @param end defines end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor to define a line, constructing the points too.
     * @param x1 x coordinate
     * @param y1 y coordinate
     * @param x2 x coordinate
     * @param y2 y coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }


    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect Rectangle
     * @return Point (the closest intersection point)
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> myList = rect.intersectionPoints(this);

        // If the list is empty returning null
        if (myList.isEmpty()) {
            return null;
        }

        // Going through the intersection points and finding the closest one
        Point closestPoint = new Point(0, 0);
        for (int i = 0; i < myList.size(); i++) {
            Point p = myList.get(i);
            if (i == 0) {
                closestPoint.setX(p.getX());
                closestPoint.setY(p.getY());
            }
            // checking new distance, if its shorter, then this is the new value
            if (i > 0 && p.distance(this.start) <= myList.get(i - 1).distance(this.start)) {
                closestPoint.setX(p.getX());
                closestPoint.setY(p.getY());
            }
        }

        return closestPoint;
    }

    /**
     * Returns the length of the line.
     * @return double
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Extending the length of this line.
     * Used for further calculations of trajectory.
     * @param ext double
     * @return Line
     */
    public Line extendLine(double ext) {
        double extLength = ext + length();
        double x = ((end.getX() * extLength) - ext * start.getX()) / length();
        double y = ((end.getY() * extLength) - ext * start.getY()) / length();
        Point p = new Point(x, y);
        return new Line(start, p);
    }

    /**
     * Returns the mid-point of a line.
     * @return Point
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * Returns the start point.
     * @return Point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point.
     * @return Point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks whether two lines intersect, returns true/false.
     * @param other does this line intersect with our line?
     * @return boolean
     */
    public boolean isIntersecting(Line other) {

        // Checking orientation (method to check intersection)
        double thisLead1 = this.orientation(other.start);
        double thisLead2 = this.orientation(other.end);
        double otherLead1 = other.orientation(this.start);
        double otherLead2 = other.orientation(this.end);

        // Collinear, clockwise or anti-clockwise, thereby knowing if there's intersection
        if (thisLead1 != thisLead2 && otherLead1 != otherLead2) {
            return true;
        }
        if (thisLead1 == 0 && this.isPointOnLine(other.start)) {
            return true;
        }
        if (thisLead2 == 0 && this.isPointOnLine(other.end)) {
            return true;
        }
        if (otherLead1 == 0 && other.isPointOnLine(this.start)) {
            return true;
        }
        if (otherLead2 == 0 && other.isPointOnLine(this.end)) {
            return true;
        }

        return false;
    }

    /**
     * Returns the intersection point of two lines
     * if there's no intersection or there's infinite intersection points, returns null.
     * @param other what's the intersection point of the line with ours?
     * @return Point (intersection point)
     */
    public Point intersectionWith(Line other) {
        // Initialization
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        // Checks to see if lines are not intersecting at all
        // or intersecting infinitely
        if (!this.isIntersecting(other)) {
            return null;
        }
        if (this.equals(other)) {
            return null;
        }
        if (this.multipleIntersections(other) && this.isIntersecting(other)) {
            return null;
        }

        // using a matrix method to calculate
        double alpha1 = y2 - y1;
        double beta1 = x1 - x2;
        double gama1 = alpha1 * x1 + beta1 * y1;

        double alpha2 = y4 - y3;
        double beta2 = x3 - x4;
        double gama2 = alpha2 * x3 + beta2 * y3;

        // determinant
        double delta = alpha1 * beta2 - alpha2 * beta1;

        if (delta == 0) {
            return null;
        } else {
            double myX = (beta2 * gama1 - beta1 * gama2) / delta;
            double myY = (alpha1 * gama2 - alpha2 * gama1) / delta;

            Point p = new Point(myX, myY);

            // making sure the point is on the line
            // (an intersection point must be on the line of course)
            if (!this.isPointOnLine(p)) {
                return null;
            }
            return p;
        }
    }


    /**
     * Checks if two lines are equal.
     * @param other two lines to see if they're equal
     * @return boolean
     */
    public boolean equals(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // checking all values to see if lines are equal
        if (Math.max(x1, x2) == Math.max(x3, x4) && Math.min(x1, x2) == Math.min(x3, x4)
                && Math.max(y1, y2) == Math.max(y3, y4) && Math.min(y1, y2) == Math.min(y3, y4)) {
            return true;
        }
        return false;
    }

    /**
     *  Checks if two lines intersect in more than one point (infinite points).
     * @param other two lines to check if there are multiple intersection points
     * @return boolean
     */
    public boolean multipleIntersections(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        // checking if its collinear
        if ((x3 - x2) * (y2 - y1) - (x2 - x1) * (y3 - y2) != 0) {
            return false;
        }

        double xl1max = Math.max(x1, x2);
        double xl2max = Math.max(x3, x4);
        double xl1min = Math.min(x1, x2);
        double xl2min = Math.min(x3, x4);

        if (Math.min(xl1max, xl2max) > Math.max(xl1min, xl2min)) {
            return true;
        }
        return false;
    }

    /**
     * Checks the orientation of a line and a point,
     * clockwise, anti-clockwise, collinear.
     * Helps to check intersection.
     * @param p3 checks the orientation of our line with this point
     * @return int
     */
    public int orientation(Point p3) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = p3.getX();
        double y3 = p3.getY();

        // m is the slope
        // m1 = (y2 - y1) / (x2 - x1);
        // m2 = (y3 - y2) / (x3 - x2);
        // Expression can cause division of zero, so
        // we change it mathematically to this expression:
        double res = (x3 - x2) * (y2 - y1) - (x2 - x1) * (y3 - y2);

        // Clockwise = 1, CounterClockwise = -1, Collinear = 0;
        if (res > 0) {
            return 1;
        } else if (res == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Checks if a given point exists on a given line.
     * @param p3 checks if this point is on our line
     * @return boolean
     */
    public boolean isPointOnLine(Point p3) {
        Point p1 = this.start;
        Point p2 = this.end;

        // checking if the point is on the line
        if (p3.getX() <= Math.max(p1.getX(), p2.getX()) && p3.getX() >= Math.min(p1.getX(), p2.getX())
                && p3.getY() <= Math.max(p1.getY(), p2.getY()) && p3.getY() >= Math.min(p1.getY(), p2.getY())) {
            return true;
        }
        return false;
    }
}
