// 206947996 Eyal Bouganim
package Listener;

import Game.Info;
import Sprites.Block;
import Geometry.Ball;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * ScoreTrackingListener is used to update the score counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Updates score after hit.
     * @param beingHit Block
     * @param hitter Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(Info.BLOCK_HIT_PRIZE);
    }

    /**
     * Returns the score Counter.
     * @return Counter
     */
    public Counter getScoreCounter() {
        return this.currentScore;
    }
}
