package cleancode.minesweeper.tobe.lesson;

import cleancode.minesweeper.tobe.lesson.gamelevel.*;
import cleancode.minesweeper.tobe.lesson.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.lesson.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.lesson.io.InputHandler;
import cleancode.minesweeper.tobe.lesson.io.OutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        Minesweeper minesweeper= new Minesweeper(gameLevel, inputHandler, outputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }

}
