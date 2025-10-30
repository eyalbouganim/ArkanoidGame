// 206947996 Eyal Bouganim
package Sprites;

import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import Collision.Collidable;
import Listener.HitListener;
import Listener.HitNotifier;
import Game.GameLevel;
import Geometry.Ball;
import Geometry.Velocity;
import java.util.ArrayList;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Class Block contains members rectangle, color.
 * Draws the block, set color of block, add to game, time passed,
 * getter for the rectangle and hit method that operates when a hit occurs.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // Fields
    private List<HitListener> hitListeners = new LinkedList<>();
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     * @param rectangle Rectangle.
     * @param color Color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Draws the block on the draw surface.
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();
        surface.setColor(this.color);
        surface.fillRectangle((int) upperLeftX, (int) upperLeftY,
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Sets the color of the block.
     * @param color Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Adds the block to the Sprites and Collidables in the game.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes the block from the Sprites and Collidables in the game.
     * @param gameLevel Game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * For now empty, in later assignments will be written.
     */
    public void timePassed() {

    }


    /**
     * Return the "collision shape" of the object.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Sets the rectangle for the block.
     * @param upperLeft Point
     * @param width double
     * @param height double
     */
    public void setRectangle(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
    }

    /**
     * Notify the object that we collided with is at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint Point
     * @param currentVelocity Velocity
     * @param hitter Ball
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Rectangle myRec = this.rectangle;
        // if the distance between the collision point and the block is close enough
        // then allowed to consider it as a hit and execute the following changes in velocity
        double distDiff = Math.pow(10, -4);

        if ((Math.abs(collisionPoint.getX() - myRec.getLowerLeft().getX()) < distDiff && dx > 0)
        || (Math.abs(collisionPoint.getX() - myRec.getLowerRight().getX()) < distDiff && dx < 0)) {
            dx = -dx;
        }

        if ((Math.abs(collisionPoint.getY() - myRec.getUpperLeft().getY()) < distDiff && dy > 0)
        || (Math.abs(collisionPoint.getY() - myRec.getLowerLeft().getY()) < distDiff && dy < 0)) {
            dy = -dy;
        }

        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl HitListener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl HitListener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies the hit to all the listeners in the list.
     * @param hitter Ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
