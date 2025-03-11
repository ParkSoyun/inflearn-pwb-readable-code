package cleancode.minesweeper.tobe.lesson.io;

import cleancode.minesweeper.tobe.lesson.GameBoard;
import cleancode.minesweeper.tobe.lesson.GameException;

public interface OutputHandler {

    void showGameStartComments();

    void showBoard(GameBoard board);

    void showGameWinningComment();

    void showGameLosingComment();

    void showCommentForSelectingCell();

    void showCommentForUserAction();

    void showExceptionMessage(GameException e);

    void showSimpleMessage(String message);

}
