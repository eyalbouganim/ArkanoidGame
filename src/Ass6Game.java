// 206947996 Eyal Bouganim

import Game.GameFlow;
import Game.LevelInformation;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eyal Bouganim
 * @version 1.0
 * Creates a new game.
 * Initializes it, then runs it.
 */
public class Ass6Game {
    /**
     * Main function.
     * Creates a new game.
     * Checks if the users entered levels of his own choice,
     * if so, the game is operated according to these levels.
     * Otherwise, the game plays levels 1-4.
     * Initializes it, then runs it.
     * @param args String[]
     */
    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();

        boolean lastLevel = false;
        if (args.length >= 1) {

            for (int i = 0; i < args.length; i++) {

                int numCheck = 0;

                try {
                    numCheck = Integer.parseInt(args[i]);
                } catch (Exception e) {
                    System.out.println("Argument entered is not a number/level.");
                }

                if (numCheck < 1 || numCheck > 4) {
                    continue;
                }

                if (i == args.length - 1) {
                    lastLevel = true;
                }

                if (args[i].equals("1")) {
                    levels.add(new Level1(lastLevel));
                }
                if (args[i].equals("2")) {
                    levels.add(new Level2(lastLevel));
                }
                if (args[i].equals("3")) {
                    levels.add(new Level3(lastLevel));
                }
                if (args[i].equals("4")) {
                    levels.add(new Level4(lastLevel));
                }
            }
        }

        if (levels.size() == 0) {
            levels.add(new Level1(false));
            levels.add(new Level2(false));
            levels.add(new Level3(false));
            levels.add(new Level4(true));
        }
        gameFlow.runLevels(levels);

//        List<LevelInformation> levelTest = new ArrayList<>();
//        levelTest.add(new Level4(true));
//        gameFlow.runLevels(levelTest);
    }
}
