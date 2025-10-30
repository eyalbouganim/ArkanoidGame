// 206947996 Eyal Bouganim
package Collision;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Ball;
import Geometry.Velocity;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Collidable Interface, implemnted in block, and paddle.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return Rectangle
     */
    Rectangle getCollisionRectangle();

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
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}