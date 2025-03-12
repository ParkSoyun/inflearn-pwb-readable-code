package cleancode.minesweeper.tobe.lesson.io;

import cleancode.minesweeper.tobe.lesson.position.CellPosition;
import cleancode.minesweeper.tobe.lesson.user.UserAction;

public interface InputHandler {

    UserAction getUserActionFromUser();

    CellPosition getCellPositionFromUser();

}
