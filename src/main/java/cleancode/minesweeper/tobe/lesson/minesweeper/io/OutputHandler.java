package cleancode.minesweeper.tobe.lesson.minesweeper.io;

import cleancode.minesweeper.tobe.lesson.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.lesson.minesweeper.exception.GameException;

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
