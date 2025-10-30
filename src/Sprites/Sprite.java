// 206947996 Eyal Bouganim
package Sprites;

import biuoop.DrawSurface;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Sprite Interface, Implemnted in Ball, Block, Paddle.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}