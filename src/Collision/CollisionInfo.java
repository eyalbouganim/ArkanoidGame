// 206947996 Eyal Bouganim
package Collision;

import Geometry.Point;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Contains the collision point and the collision object.
 * Contains getters.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * the point at which the collision occurs.
     * @return Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return Collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Constructor.
     * @param collisionPoint Point
     * @param collisionObject Collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }
}