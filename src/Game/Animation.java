package Game;

import biuoop.DrawSurface;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Animation Interface
 * doOneFrame creates a frame with desired implementation.
 * shouldStop stops animation when desired.
 */
public interface Animation {

    /**
     * Actions to be done in a single frame.
     * @param d DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Makes the animation stop.
     * @return boolean
     */
    boolean shouldStop();
}