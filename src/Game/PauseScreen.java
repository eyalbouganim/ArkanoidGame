package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * In charge of the pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Draws the frame of the pause screen with the correct text.
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Stops the pause screen.
     * @return booelan
     */
    public boolean shouldStop() {
        return this.stop;
    }

}