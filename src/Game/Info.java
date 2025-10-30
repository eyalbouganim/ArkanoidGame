// 206947996 Eyal Bouganim
package Game;

import java.awt.Color;
import Geometry.Point;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Contains all the info about parameters, data, in an orgainsed way.
 */
public final class Info {

    // Screen and Boundaries Details:
    public static final double BOUNDARIES_WIDTH = 20;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int FPS = 60;

    // Paddle Dedatils:
    public static final Color PADDLE_COLOR = Color.RED;

    // Blocks Details:
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final Color BOUNDS_COLOR = Color.LIGHT_GRAY;
    public static final int BLOCK_HIT_PRIZE = 5;

    // Ball Details
    public static final int BALL_SIZE = 5;
    public static final Color BALL_COLOR = Color.GRAY;
    public static final Point BALL_LAUNCH = new Point(400, 550);
}
