// 206947996 Eyal Bouganim
package Game;

import Sprites.Sprite;
import Sprites.ScoreIndicator;
import Sprites.Paddle;
import Sprites.Block;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Collision.Collidable;
import Listener.HitListener;
import Collections.SpriteCollection;
import Collections.GameEnvironment;
import Listener.Counter;
import Listener.BlockRemover;
import Listener.BallRemover;
import Listener.ScoreTrackingListener;
import Geometry.Rectangle;
import Geometry.Point;
import Geometry.Ball;
import java.awt.Color;
import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Contains the lists of collidables and sprites, and the GUI.
 * Adds collidables, sprites.
 * Creating the pattern of blocks, the bounds, balls, score.
 * Initializes all.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score = new Counter();
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    private String winOrLose = " ";

    /**
     * Constructor.
     * @param levelInfo LevelInformation
     * @param score Counter
     * @param gui GUI
     */
    public GameLevel(LevelInformation levelInfo, Counter score, GUI gui) {
        this.levelInfo = levelInfo;
        this.score = score;
        this.gui = gui;
    }

    /**
     * Makes the game stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Things that happen in a single frame,
     * such as drawing the sprites, checking blocks and balls number
     * to see if the game is paused, won or lost.
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        levelInfo.getBackground().drawOn(d);
        // Balls Color
        d.setColor(new Color(204, 170, 9));
        // Draw Sprites
        this.sprites.drawAllOn(d);

        // If P is pressed game is paused.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        // Loss Situation
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
            this.winOrLose = "lost";
        }

        // Win Situation
        if (levelInfo.askLastLevel() && blockCounter.getValue() == 0) {
            this.running = false;
            this.winOrLose = "win";
        }

        // Lost message for loss situation.
        if (this.winOrLose.equals("lost")) {
            runner.run(
                    new KeyPressStoppableAnimation(keyboard,
                            "space",
                            new EndScreen("Game Over. Your score is " + score.getValue())));
            this.gui.close();
        }
        // Wins message for win situation.
        if (this.winOrLose.equals("win") && levelInfo.askLastLevel()) {
            runner.run(
                    new KeyPressStoppableAnimation(keyboard,
                            "space",
                            new EndScreen("You Win! Your score is " + score.getValue())));
            this.gui.close();
        }

        // Checking Blocks and Balls numbers to see whether the game should be stopped.
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            this.running = false;
        }

        this.sprites.notifyAllTimePassed();
    }

    /**
     * Running the game using the animation runner.
     */
    public void run() {
        this.keyboard = gui.getKeyboardSensor();
        this.running = true;
        this.runner = new AnimationRunner(this.gui, Info.FPS);
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        //CountdownAnimation c = new CountdownAnimation(3, 1, sprites, runner);
    }

    /**
     * adds the collidable to the enviroment.
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds the sprite to the sprites list.
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove a collidable from the enviroment.
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove a sprite from the SpriteCollection.
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Returns the amount of balls left in the game.
     * @return int
     */
    public int ballsLeft() {
        return this.ballCounter.getValue();
    }

    /**
     * Returns the amount of blocks left in the game.
     * @return int
     */
    public int blocksLeft() {
        return this.blockCounter.getValue();
    }

    /**
     * Initialize a new game: creates the Counters, Blocks, Ball, Bounds, Score and Paddle
     * and adds them to the game.
     */
    public void initialize() {

        this.blockCounter = new Counter();
        this.blockCounter.setValue(this.levelInfo.numberOfBlocksToRemove());
          BlockRemover blockListener = new BlockRemover(this, this.blockCounter);

        this.ballCounter = new Counter();
        this.ballCounter.setValue(this.levelInfo.numberOfBalls());
        BallRemover ballListener = new BallRemover(this, this.ballCounter);

        createBalls();
        createBoundaries(ballListener);
        createPaddle();
        createBlocks(blockListener);
        createScoreDisplay();

    }

    /**
     * Creates a display for the score, and also creates
     * a listener to be notified of score changes.
     */
    public void createScoreDisplay() {
        ScoreTrackingListener listener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreDisplay = new ScoreIndicator(listener.getScoreCounter());
        scoreDisplay.setLevelName(this.levelInfo.levelName());
        scoreDisplay.addToGame(this);
    }

    /**
     * Gets the balls from the specific level which is played.
     * Adds them to the game.
     */
    public void createBalls() {
        List<Ball> balls = this.levelInfo.ballList();
        for (Ball ball: balls) {
            ball.setGameEnvi(this.environment);
            ball.addToGame(this);
        }

    }

    /**
     * Creates the paddle and making sure it's controlled by the keyboard.
     */
    public void createPaddle() {
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), this.levelInfo.paddleWidth(),
                Info.PADDLE_COLOR, this.levelInfo.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Recieves blocks from the specific level that is played.
     * Adds listeners, adds blocks to the game.
     * @param listener HitListener
     */
    public void createBlocks(HitListener listener) {
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);

        // Going through each block and adding the listeners, then adding to the game.
        for (Block block: this.levelInfo.blocks()) {
            block.addHitListener(listener);
            block.addHitListener(scoreListener);
            block.addToGame(this);
        }

    }

    /**
     * Creating the boundaries to the game.
     * The DeathRegion is also created, once it's hit, we know the hitting ball
     * was removed from the game.
     * @param listener HitListener
     */
    public void createBoundaries(HitListener listener) {
        // Creating blocks (boundaries) and adding them to the game.

        Rectangle rightRec = new Rectangle(new Point(780, 0), 20, 600);
        Block right = new Block(rightRec, Info.BOUNDS_COLOR);
        right.addToGame(this);

        Rectangle leftRec = new Rectangle(new Point(0, 0), 20, 600);
        Block left = new Block(leftRec, Info.BOUNDS_COLOR);
        left.addToGame(this);

        Rectangle upRec = new Rectangle(new Point(0, 30), 800, 20);
        Block up = new Block(upRec, Info.BOUNDS_COLOR);
        up.addToGame(this);

        // Death Block - if a ball hits it, it's out of the game.
        Rectangle deathRec = new Rectangle(new Point(0, 610), 800, 20);
        Block deathBlock = new Block(deathRec, Info.BOUNDS_COLOR);
        deathBlock.addHitListener(listener);
        deathBlock.addToGame(this);
    }

}