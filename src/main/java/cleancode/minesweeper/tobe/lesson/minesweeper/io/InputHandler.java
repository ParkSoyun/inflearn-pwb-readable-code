package cleancode.minesweeper.tobe.lesson.minesweeper.io;

import cleancode.minesweeper.tobe.lesson.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.lesson.minesweeper.user.UserAction;

public interface InputHandler {

    UserAction getUserActionFromUser();

    CellPosition getCellPositionFromUser();

}
