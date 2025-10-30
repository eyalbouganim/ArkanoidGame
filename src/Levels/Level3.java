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
 * Level 3 specifications and details.
 */
public class Level3 implements LevelInformation {

    private boolean lastLevel;

    /**
     * Construcor.
     * @param lastLevel boolean
     */
    public Level3(boolean lastLevel) {
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
        return 2;
    }

    /**
     * The initial velocity of each ball.
     * @return List of Velocity
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int y = 1;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(new Velocity(-2 * y, -6));
            y = -2;
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
        return 8;
    }

    /**
     * Paddle Width is returned.
     * @return int
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * The level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(38, 148, 82));
                d.fillRectangle(0,0, Info.SCREEN_WIDTH, Info.SCREEN_HEIGHT);

                // Drawing Building itself (windows)
                // White Background
                d.setColor(Color.WHITE);
                d.fillRectangle(75, 400, 125, 600);

                // Black rectangles
                d.setColor(Color.BLACK);
                // Vertical
                int widthChange = 75;
                for (int i = 0; i < 5; i++) {
                    d.fillRectangle(widthChange, 400, 10, 200);
                    widthChange += 30;
                }

                // Horizontial
                int heightChange = 400;
                for (int i = 0; i < 5; i ++) {
                    d.fillRectangle(75, heightChange, 125, 10);
                    heightChange += 40;
                }

                // Drawing the antenna over the building
                d.setColor(new Color(54, 51, 47));
                d.fillRectangle(130, 300, 25, 100);
                d.setColor(Color.darkGray);
                d.fillRectangle(138, 150, 10, 150);
                d.setColor(new Color(213, 76, 6));
                d.fillCircle(143, 150, 12);

                // Drawing the rays from the antenna
                d.setColor(new Color(232, 185, 19));
                d.drawCircle(143, 150, 25);
                d.drawCircle(143, 150, 40);
                d.drawCircle(143, 150, 60);
                d.drawCircle(143, 150, 90);

            }

            @Override
            public void timePassed() {

            }
        };
        //Rectangle backgroundRec = new Rectangle(new Point(0, 0), Info.SCREEN_WIDTH, Info.SCREEN_HEIGHT);
        //return new Block(backgroundRec, new Color(8, 105, 71));
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List of Block
     */
    public List<Block> blocks() {
        // Lists
        List<Block> blockList = new LinkedList<>();
        Color[] colorList = {Color.RED, Color.ORANGE, Color.YELLOW,
        Color.PINK, Color.CYAN, Color.BLUE };

        int blocksTopLine = 130;
        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 10 - i; j++) {

                Point p = new Point(280 + (j * Info.BLOCK_WIDTH) + (i * Info.BLOCK_WIDTH),
                        blocksTopLine + (i * Info.BLOCK_HEIGHT));
                Rectangle rec = new Rectangle(p, Info.BLOCK_WIDTH - 0.01, Info.BLOCK_HEIGHT);
                Block block = new Block(rec, colorList[i]);
                blockList.add(block);
            }
        }

        return blockList;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return int
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
