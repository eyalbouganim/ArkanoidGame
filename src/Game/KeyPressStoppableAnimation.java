package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Contains the logic for the passage screens.
 * (If the game is won or lost)
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;
    private boolean running = true;

    /**
     * Constructor.
     * @param sensor KeyboardSensor
     * @param key String
     * @param animation Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * Operates onde frame and checks whether the user already typed
     * the key for the passage screen.
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        if (sensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.running = false;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * Stops (false).
     * @return boolean
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }
}
