// 206947996 Eyal Bouganim
package Listener;

import Sprites.Block;
import Game.GameLevel;
import Geometry.Ball;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel Game
     * @param remainingBlocks Counter
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit Block
     * @param hitter Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSprite(beingHit);
        this.gameLevel.removeCollidable(beingHit);
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
    }

}