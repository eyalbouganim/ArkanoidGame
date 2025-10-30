// 206947996 Eyal Bouganim
package Collections;

import java.util.ArrayList;
import Collision.CollisionInfo;
import Collision.Collidable;
import Geometry.Point;
import Geometry.Line;
import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Contains a list of the collidables, add collidables,
 * returns collision info regarding the closest collision.
 */
public class GameEnvironment {

    // Creating an Array List of collidables.
    private final List<Collidable> collidables = new ArrayList<>();

    /**
     * add the given collidable to the environment.
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove a collidable from the list.
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory Line
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // Checking length of the trajectory
        double checkDistance = trajectory.length();
        Point closestCollisionPoint = null;
        Collidable closestCollidable = null;

        List<Collidable> collidablesCopy = new ArrayList<>(collidables);
        // finding the closest collision point of the collisions
        for (Collidable collidable: collidablesCopy) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                double distance = collisionPoint.distance(trajectory.start());
                // if the distance is shorter, assign this value (storing the closest point)
                if (distance < checkDistance) {
                    checkDistance = distance;
                    closestCollisionPoint = collisionPoint;
                    closestCollidable = collidable;
                }
            }
        }
        // if there's indeed a collision point return it (the closest one)
        if (closestCollisionPoint != null) {
            return new CollisionInfo(closestCollisionPoint, closestCollidable);
        }
        return null;
    }

}