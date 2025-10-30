package Levels;

import Collections.SpriteCollection;
import Game.Info;
import Game.LevelInformation;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Level 1 specifications and details.
 */
public class Level1 implements LevelInformation {

    private boolean lastLevel;

    /**
     * Construcor.
     * @param lastLevel boolean
     */
    public Level1(boolean lastLevel) {
        this.lastLevel = lastLevel;
    }

    /**
     * Returns boolean value to see if it's the last level.
     * @return boolean
     */
    public boolean askLastLevel() {
        return this.lastLevel;
    }

    /**
     * Returns the number of total balls in the level.
     * @return int
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     * @return List of Velocity
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -4));
        return velocities;
    }

    /**
     * The balls in the game inside a list, with all their details.
     * @return List of Ball
     */
    public List<Ball> ballList() {
        List<Ball> balls = new ArrayList<>();
        List<Velocity> velocities = this.initialBallVelocities();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Ball ball = new Ball(Info.BALL_LAUNCH, Info.BALL_SIZE, Info.BALL_COLOR);
            ball.setVelocity(velocities.get(i));
            balls.add(ball);
        }
        return balls;
    }

    /**
     * Speed of the paddle is returned.
     * @return int
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Paddle Width is returned.
     * @return int
     */
    public int paddleWidth() {
        return 80;
    }

    /**
     * The level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    public Sprite getBackground() {
        //SpriteCollection level1Background = new SpriteCollection();
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0,0, Info.SCREEN_WIDTH, Info.SCREEN_HEIGHT);
                d.setColor(Color.GREEN);
                // Circles and lines revolving the block
                // Lines
                d.drawLine(400, 125, 400, 190);
                d.drawLine(400, 240, 400, 305);
                d.drawLine(310, 215, 375, 215);
                d.drawLine(425, 215, 490, 215);
                // Blocks
                d.drawCircle(400, 215, 50);
                d.drawCircle(400, 215,70);
                d.drawCircle(400, 215,90);
            }

            @Override
            public void timePassed() {

            }
        };
        //Rectangle backgroundRec = new Rectangle(new Point(0, 0), Info.SCREEN_WIDTH, Info.SCREEN_HEIGHT);
        //return new Block(backgroundRec, new Color(0, 0, 0));
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List of Block
     */
    public List<Block> blocks() {
        List<Block> blockList = new LinkedList<>();
        Rectangle rec = new Rectangle(new Point(385, 200), 30, 30);
        Block block = new Block(rec, Color.RED);
        blockList.add(block);
        return blockList;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return int
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

}
