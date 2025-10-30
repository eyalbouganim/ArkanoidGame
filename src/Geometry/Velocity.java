// 206947996 Eyal Bouganim
package Geometry;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Velocity specifies speed and direction. (angle&speed or dx/dy)
 * Creates velocity from dx,dy or from angle,speed.
 * Applies velocity to a point.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx
     * @param dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Takes angle and speed and converts them to dx,dy, then returns velocity.
     * @param angle
     * @param speed
     * @return Velocity returns the calculated velocity.
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * dx Getter.
     * @return double (dx)
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * dy Getter.
     * @return double (dy)
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point.
     * with position (x+dx, y+dy)
     * @param p
     * @return Point (new point after velocity application)
     */
    public Point applyToPoint(Point p) {
        double xdx = p.getX() + this.dx;
        double ydy = p.getY() + this.dy;
        return new Point(xdx, ydy);
    }
}