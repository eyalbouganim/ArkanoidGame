// 206947996 Eyal Bouganim
package Collections;

import biuoop.DrawSurface;
import java.util.LinkedList;
import java.util.List;
import Sprites.Sprite;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Stores the sprites in a linked list
 * Adds them, draws them, notifies time passed.
 */
public class SpriteCollection {

    // Creating a linked list to store the sprites.
    private final List<Sprite> sprites = new LinkedList<>();

    /**
     * Adding a sprite to the linked list.
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove a sprite from the spriteCollection.
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new LinkedList<>(this.sprites);
        for (Sprite s: spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: sprites) {
            s.drawOn(d);
        }
    }
}