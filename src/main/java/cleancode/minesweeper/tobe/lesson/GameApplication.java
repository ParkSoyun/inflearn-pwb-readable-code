package cleancode.minesweeper.tobe.lesson;

import cleancode.minesweeper.tobe.lesson.gamelevel.*;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();

        Minesweeper minesweeper= new Minesweeper(gameLevel);
        minesweeper.run();
    }

}
