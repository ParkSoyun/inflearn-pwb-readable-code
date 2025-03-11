package cleancode.minesweeper.tobe.lesson.io;

import cleancode.minesweeper.tobe.lesson.position.CellPosition;

public interface InputHandler {

    String getUserInput();

    CellPosition getCellPositionFromUser();

}
