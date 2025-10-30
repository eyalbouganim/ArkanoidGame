package Game;

import Listener.Counter;
import Sprites.ScoreIndicator;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * GameFlow is in charge of running the levels.
 * Keeps the score for the entire match, and it runs for each level.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private ScoreIndicator scoreIndicator = new ScoreIndicator(new Counter());
    private GUI gui;

    /**
     * Creating a GUI SCREEN.
     * Going through the levels using a "for each" loop to go through the levels,
     * initialize and run them.
     * @param levels List of LevelInformation
     */
    public void runLevels(List<LevelInformation> levels) {
        GUI gui = new GUI("Arkanoid", Info.SCREEN_WIDTH, Info.SCREEN_HEIGHT);

        // For Each Loop
        for (LevelInformation levelInfo : levels) {
            scoreIndicator.setLevelName(levelInfo.levelName());

            GameLevel level = new GameLevel(levelInfo, scoreIndicator.getCounter(), gui);
            level.initialize();

            while (level.ballsLeft() > 0 && level.blocksLeft() > 0) {
                level.run();
            }

            if (level.ballsLeft() == 0) {
                break;
            }

        }
    }
}