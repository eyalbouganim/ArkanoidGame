package Game;

import Collections.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.*;

public class CountdownAnimation implements Animation {

    private AnimationRunner runner;
    private boolean running;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;


    /**
     * Constructor.
     * @param numOfSeconds double
     * @param countFrom int
     * @param gameScreen SpriteCollection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, AnimationRunner runner) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.runner = runner;
        this.running = true;
    }

    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(this.numOfSeconds, this.countFrom, this.gameScreen, this.runner));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        this.runner.run(this);
    }

    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
    }

    public boolean shouldStop() {
      return false;
    }
}
