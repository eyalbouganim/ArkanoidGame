package Levels;

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
 * Level 2 specifications and details.
 */
public class Level2 implements LevelInformation {

    private boolean lastLevel;

    /**
     * Construcor.
     * @param lastLevel boolean
     */
    public Level2(boolean lastLevel) {
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
        return 10;
    }

    /**
     * The initial velocity of each ball.
     * @return List of Velocity
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        int x = 0;
        double y = 0;
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            velocities.add(new Velocity(-1 - (Math.cos(y)), -5 - y));
            y = y + 0.5;
        }

        y = 2;
        for (int j = 5; j < 10; j++) {
            velocities.add(new Velocity(1 + (Math.cos(y)), -5 - y));
            y = y - 0.5;
        }
        return velocities;
    }

    /**
     * The balls in the game inside a list, with all their details.
     * @return List of Ball
     */
    public List<Ball> ballList() {
        List<Ball> balls = new ArrayList<>();
        List<Velocity> velocities = this.initialBallVelocities();

        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Ball ball = new Ball(Info.BALL_LAUNCH, Info.BALL_SIZE, Info.BALL_COLOR);
            ball.setVelocity(velocities.get(i));
            balls.add(ball);
        }

        for (int j = 5; j < 10; j++) {
            Ball ball = new Ball(Info.BALL_LAUNCH, Info.BALL_SIZE, Info.BALL_COLOR);
            ball.setVelocity(velocities.get(j));
            balls.add(ball);
        }
        return balls;
    }

    /**
     * Speed of the paddle is returned.
     * @return int
     */
    public int paddleSpeed() {
        return 4;
    }

    /**
     * Paddle Width is returned.
     * @return int
     */
    public int paddleWidth() {
        return 400;
    }

    /**
     * The level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {

                d.setColor(new Color(180, 232, 239));
                d.fillRectangle(0,0, Info.SCREEN_WIDTH, Info.SCREEN_HEIGHT);

                d.setColor(Color.YELLOW);
                int numLines = 65;
                int sunBorderX = 120;
                int sunBorderY = 190;
                int rayHitX = 75;
                int rayHitY = 240;
                // Drawing the sunlight (sun rays)
                for (int i = 0; i < numLines; i++) {
                    d.drawLine(sunBorderX, sunBorderY, rayHitX, rayHitY);
                    rayHitX += 8;
                    sunBorderX += 0.2;
                    sunBorderY -= 0.1;
                }

                // Drawing the sun itself
                d.setColor(new Color(255, 210, 157, 255));
                d.fillCircle(150,150, 47);
                d.setColor(Color.ORANGE);
                d.fillCircle(150,150, 40);
                d.setColor(Color.YELLOW);
                d.fillCircle(150, 150, 30);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List of Block
     */
    public List<Block> blocks() {
        // Lists
        List<Block> blockList = new LinkedList<>();
        Color[] colorList = {Color.RED, Color.RED,
                Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN,
                Color.BLUE, Color.BLUE,
                Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};

        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            Point p = new Point(Info.BOUNDARIES_WIDTH + i * Info.BLOCK_WIDTH, 240);
            Rectangle rec = new Rectangle(p, Info.BLOCK_WIDTH - 0.01, Info.BLOCK_HEIGHT);
            Block block = new Block(rec, colorList[i]);
            blockList.add(block);
        }

        return blockList;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return int
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
