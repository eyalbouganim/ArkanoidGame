// 206947996 Eyal Bouganim
package Listener;

import Sprites.Block;
import Game.GameLevel;
import Geometry.Ball;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Ball Remover implements the HitListener interface.
 * In charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param gameLevel Game
     * @param remainingBalls Counter
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Balls that hit the death region should be removed from the game.
     * @param beingHit Block
     * @param hitter Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSprite(beingHit);
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }

}
