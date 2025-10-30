// 206947996 Eyal Bouganim
package Sprites;

import biuoop.DrawSurface;
import Listener.Counter;
import Game.GameLevel;
import Game.Info;
import java.awt.Color;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * In charge of displaying the current score.
 * The ScoreIndicator will hold a reference to the scores counter,
 * and will be added to the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {

    private Counter score;
    private String levelName;

    /**
     * Constructor.
     * @param score Counter that holds the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Set the name of the level.
     * @param levelName String
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Get the score counter.
     * @return Counter
     */
    public Counter getCounter() {
        return this.score;
    }

    /**
     * Draw the sprite to the screen.
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(10, 77, 102));
        d.fillRectangle(0, 0, Info.SCREEN_WIDTH, 30);
        d.setColor(new Color(232, 185, 19));

        String strScore = String.valueOf(score.getValue());
        d.drawText(200, 25, "Score:" + strScore, 25);
        d.drawText(400, 25, "Level: " + levelName, 25);
    }

    /**
     * Add this Sprite to the game.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {

    }
}
