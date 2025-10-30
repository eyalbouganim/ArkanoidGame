// 206947996 Eyal Bouganim
package Sprites;

import Collision.Collidable;
import Geometry.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Game.GameLevel;
import Geometry.Ball;
import Geometry.Rectangle;
import java.awt.Color;
import Game.Info;
import Geometry.Velocity;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Keyboard, Rectangle shape, color and motion speed of the paddle.
 * Right&Left Movements, Set the Rectangle, time passed, draw paddle,
 * adds paddle to the game, paddle hit mechanism.
 * Paddle implements Sprite, Collidable.
 */
public class Paddle implements Sprite, Collidable {

    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int motion;

    /**
     * Constructor.
     * @param keyboard KeyboardSensor
     * @param width int
     * @param color Color
     * @param motion int
     */
    public Paddle(KeyboardSensor keyboard, int width, Color color, int motion) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(360, 560), width, 20);
        this.color = color;
        this.motion = motion;
    }

    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
        Point motionPoint = new Point(Math.max(Info.BOUNDARIES_WIDTH, rectangle.getUpperLeft().getX() - motion),
                rectangle.getUpperLeft().getY());

        Rectangle myRec = new Rectangle(motionPoint, rectangle.getWidth(),
                rectangle.getHeight());
        setPaddleRectangle(myRec);
    }

    /**
     * Moves the paddle right.
     */
    public void moveRight() {
        Point motionPoint = new Point(Math.min(Info.SCREEN_WIDTH - Info.BOUNDARIES_WIDTH - rectangle.getWidth(),
                rectangle.getUpperLeft().getX() + motion), rectangle.getUpperLeft().getY());

        Rectangle myRec = new Rectangle(motionPoint, rectangle.getWidth(),
                rectangle.getHeight());
        setPaddleRectangle(myRec);
    }

    /**
     * Sets this rectangle as the paddle's shape.
     * @param rectangle Rectangle
     */
    public void setPaddleRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }


    /**
     * Sprite.
     */
    public void timePassed() {

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the draw surface.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();
        d.fillRectangle((int) upperLeftX, (int) upperLeftY,
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Collidable.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * New hitting mechanism according to regions 1-5 described in the assignment.
     * @param collisionPoint Point
     * @param currentVelocity Velocity
     * @param hitter Ball
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double x = collisionPoint.getX();
        double upperLeftX = rectangle.getUpperLeft().getX();
        double recWidth = rectangle.getWidth();
        // Calculating speed
        double speed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        // Hit in each region of the game is treated differently (angle-wise)

        if (x <= upperLeftX + (recWidth / 5)) {
            return currentVelocity.fromAngleAndSpeed(300, speed);
        }

        if (x <= upperLeftX + (2 * recWidth / 5)) {
            return currentVelocity.fromAngleAndSpeed(330, speed);
        }

        if (x >= upperLeftX + (4 * recWidth / 5)) {
            return currentVelocity.fromAngleAndSpeed(60, speed);
        }

        if (x >= upperLeftX + (3 * recWidth / 5)) {
            return currentVelocity.fromAngleAndSpeed(30, speed);
        }

        // If its in region 3 (the middle) acts normaly
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    // Add this paddle to the game.

    /**
     * Add this paddle to the game.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}