// 206947996 Eyal Bouganim
package Geometry;

import biuoop.DrawSurface;
import Sprites.Sprite;
import Collections.GameEnvironment;
import Game.GameLevel;
import Collision.CollisionInfo;
import java.awt.Color;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Ball contains radius, center-point, color, velocity.
 * Moves the ball and checks if the ball is hitting any objects.
 * Adding the ball to the game enviroment, draws the ball.
 */
public class Ball implements Sprite {

    private static final double RADIUSFACTOR = 1.8;
    private Point center;
    private int r;
    private Color color;
    private Velocity v;
    private GameEnvironment gameEnvi;

    /**
     * Constructor.
     *
     * @param center circle center
     * @param r      radius
     * @param color  color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Returns radius.
     *
     * @return int (radius)
     */
    public int getR() {
        return this.r;
    }

    /**
     * get x.
     *
     * @return int
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get y.
     *
     * @return int
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * returns the color of the ball.
     *
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Setting the game enviroment.
     *
     * @param gameEnvi Game Enviroment.
     */
    public void setGameEnvi(GameEnvironment gameEnvi) {
        this.gameEnvi = gameEnvi;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface (DrawSurface)
     */

    /**
     * Draws the ball (implemens a sprite method).
     *
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * Sets the velocity of the ball (directly with velocity).
     *
     * @param v Velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball using dx,dy.
     *
     * @param dx double
     * @param dy double
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.v = v;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return Velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * adds the ball (sprite) to the game.
     *
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball (sprite) from the game.
     *
     * @param g Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Executes moveOneStep (for time loss calculations).
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Computing the trajectory of the ball to check for incoming objects.
     * Applies movement to the ball, while checking the ball doesn't hit any objects.
     * In case it reaches some object, the hit method turns the ball the other way.
     */
    public void moveOneStep() {
        // compute trajectory
        Point trajStart = this.center;
        double dx = v.getDx() / Math.abs(v.getDx());
        double dy = v.getDy() / Math.abs(v.getDy());
        Point trajEnd = new Point(this.center.getX() + this.getVelocity().getDx(),
                this.center.getY() + this.getVelocity().getDy());
        Line trajectory = new Line(trajStart, trajEnd);
        //Velocity velo = new Velocity(dx, dy);
        //this.center = velo.applyToPoint(this.center);

        CollisionInfo collision = this.gameEnvi.getClosestCollision(trajectory.extendLine(2 * r));
        if (collision == null) {
            // move the ball to the end of the trajectory
            this.center = trajEnd;
        } else {
            setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), v));
        }

    }


}
