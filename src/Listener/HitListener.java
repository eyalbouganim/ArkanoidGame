// 206947996 Eyal Bouganim
package Listener;

import Sprites.Block;
import Geometry.Ball;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * HitListener interface.
 * Objects that want to be notified of hit events, should implement the HitListener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit Block
     * @param hitter Ball
     */
    void hitEvent(Block beingHit, Ball hitter);

}
