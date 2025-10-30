// 206947996 Eyal Bouganim
package Listener;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Counter is a simple class that is used for counting things.
 */
public class Counter {

    private int count;

    /**
     * Add number to current count.
     * @param number int
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract number from current count.
     * @param number int
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Get current count.
     * @return int
     */
    public int getValue() {
        return this.count;
    }

    /**
     * Set the value of int of the counter.
     * @param number int
     */
    public void setValue(int number) {
        this.count = number;
    }

}