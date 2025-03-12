package cleancode.minesweeper.tobe.lesson;

import cleancode.minesweeper.tobe.lesson.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.lesson.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.VeryBeginner;
import cleancode.minesweeper.tobe.lesson.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.lesson.minesweeper.io.ConsoleOutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(
                new VeryBeginner(),
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()
        );

        Minesweeper minesweeper= new Minesweeper(gameConfig);
        minesweeper.initialize();
        minesweeper.run();
    }

}
