package Game;

import Listener.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Implementing the Animation interface,
 * EndScreen contains a text (win or lose case)
 * and in charge of the displayed screen
 * in case of Win or Loss.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private String text;

    /**
     * Construcor.
     * @param text String
     */
    public EndScreen(String text) {
        this.text = text;
    }

    /**
     * Draws the text to the passage screen.
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(200, d.getHeight() / 2, text, 32);
    }

    /**
     * Returns false.
     * @return false (boolean)
     */
    public boolean shouldStop() {
        return false;
    }
}
