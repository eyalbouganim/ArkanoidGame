package Game;

import Geometry.Ball;
import Geometry.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Interface to contain the information about a single level.
 * In other words, every level must contain this kind of info
 * with its specific details.
 */
public interface LevelInformation {

    /**
     * Returns the number of total balls in the level.
     * @return int
     */
    int numberOfBalls();

    /**
     * Checks if this level is the last in the game.
     * @return boolean
     */
    boolean askLastLevel();

    /**
     * The initial velocity of each ball.
     * @return List of Velocity
     */
    List<Velocity> initialBallVelocities();

    /**
     * The balls in the game inside a list, with all their details.
     * @return List of Ball
     */
    List<Ball> ballList();

    /**
     * Speed of the paddle is returned.
     * @return int
     */
    int paddleSpeed();

    /**
     * Paddle Width is returned.
     * @return int
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     * @return String
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List of Block
     */
    List<Block> blocks();


    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return int
     */
    int numberOfBlocksToRemove();

}
