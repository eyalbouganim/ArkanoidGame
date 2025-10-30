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
 * Level 4 specifications and details.
 */
public class Level4 implements LevelInformation {

    private boolean lastLevel;

    // Constants
    private final int blockWidth = 50;

    /**
     * Construcor.
     * @param lastLevel boolean
     */
    public Level4(boolean lastLevel) {
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
        return 3;
    }

    /**
     * The initial velocity of each ball.
     * @return List of Velocity
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        velocities.add(new Velocity(0, -6));
        velocities.add(new Velocity(-0.5, -5));
        velocities.add(new Velocity(0.5, -5));

        return velocities;
    }

    /**
     * The balls in the game inside a list, with all their details.
     * @return List of Ball
     */
    public List<Ball> ballList() {
        List<Ball> balls = new ArrayList<>();
        List<Velocity> velocities = this.initialBallVelocities();
        int y = 0;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Ball ball = new Ball(Info.BALL_LAUNCH, Info.BALL_SIZE, Info.BALL_COLOR);
            ball.setVelocity(velocities.get(i));
            balls.add(ball);
            y++;
        }

        return balls;
    }

    /**
     * Speed of the paddle is returned.
     * @return int
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Paddle Width is returned.
     * @return int
     */
    public int paddleWidth() {
        return 140;
    }

    /**
     * The level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(24, 154, 164));
                d.fillRectangle(0,0, Info.SCREEN_WIDTH, Info.SCREEN_HEIGHT);
                d.setColor(new Color(194, 183, 232));
                d.drawText(150,410, "13", 400);

                // Clouds
                d.setColor(Color.WHITE);
                d.fillCircle(210, 400, 30);
                d.fillCircle(180, 400, 30);
                d.fillCircle(155, 410, 22);
                d.fillCircle(230, 410, 22);

                d.fillCircle(610, 430, 30);
                d.fillCircle(580, 430, 30);
                d.fillCircle(555, 440, 22);
                d.fillCircle(630, 440, 22);


                // Raindrops
                // Left Cloud
                for (int i = 0; i < 100; i += 20) {
                    for (int j = 0; j  < 150; j += 10) {
                        d.drawLine(140 + i, 440 + j, 175 + i, 450 + j);
                        j += 10;
                    }
                }

                // Right Cloud
                for (int i = 0; i < 100; i += 20) {
                    for (int j = 0; j  < 150; j += 10) {
                        d.drawLine(535 + i, 470 + j, 570 + i, 480 + j);
                        j += 10;
                    }
                }
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
        Color[] colorList = {Color.WHITE, Color.ORANGE, Color.GRAY,
                Color.PINK, Color.CYAN, Color.BLUE, Color.GREEN };

        int blocksTopLine = 115;
        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 15; j++) {

                Point p = new Point(Info.BOUNDARIES_WIDTH + 5 + (j * blockWidth),
                        blocksTopLine + (i * Info.BLOCK_HEIGHT));
                Rectangle rec = new Rectangle(p, blockWidth - 0.8, Info.BLOCK_HEIGHT);
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
        return 105;
    }
}
