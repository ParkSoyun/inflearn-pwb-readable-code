package cleancode.minesweeper.tobe.lesson;

import cleancode.minesweeper.tobe.lesson.config.GameConfig;
import cleancode.minesweeper.tobe.lesson.gamelevel.*;
import cleancode.minesweeper.tobe.lesson.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.lesson.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.lesson.io.InputHandler;
import cleancode.minesweeper.tobe.lesson.io.OutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(
                new Beginner(),
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()
        );

        Minesweeper minesweeper= new Minesweeper(gameConfig);
        minesweeper.initialize();
        minesweeper.run();
    }

}
