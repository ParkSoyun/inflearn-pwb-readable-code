package cleancode.minesweeper.tobe.lesson;

import cleancode.minesweeper.tobe.lesson.game.GameInitializable;
import cleancode.minesweeper.tobe.lesson.game.GameRunnable;
import cleancode.minesweeper.tobe.lesson.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.lesson.io.InputHandler;
import cleancode.minesweeper.tobe.lesson.io.OutputHandler;
import cleancode.minesweeper.tobe.lesson.position.CellPosition;
import cleancode.minesweeper.tobe.lesson.user.UserAction;

public class Minesweeper implements GameInitializable, GameRunnable {

    private final GameBoard gameBoard;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public Minesweeper(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler) {
        gameBoard = new GameBoard(gameLevel);
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    @Override
    public void run() {
        outputHandler.showGameStartComments();

        while (true) {
            try {
                outputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    outputHandler.showGameWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    outputHandler.showGameLosingComment();
                    break;
                }

                CellPosition cellInput = getCellInputFromUser();
                UserAction userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) { // 의도한 예외
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) { // 의도하지 않아서 개발자가 처리해야 하는 예외
                outputHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
            }
        }
    }

    // 사용자에게 입력받은 좌표에 입력받은 행위를 수행한다.
    private void actOnCell(CellPosition cellPosition, UserAction userActionInput) {
        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flagAt(cellPosition);
            checkIfGameIsOver();
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (gameBoard.isLandMineCellAt(cellPosition)) {
                gameBoard.openAt(cellPosition);
                changGameStatusToLose();
                return;
            }

            gameBoard.openSurroundedCells(cellPosition);
            checkIfGameIsOver();
            return;
        }
        throw new GameException("잘못된 번호를 선택하셨습니다."); // userActionInput 값에 대한 예외 처리
    }

    // 게임 상태를 패배로 변경한다.
    private void changGameStatusToLose() {
        gameStatus = -1;
    }

    // 사용자가 open하기를 선택했는지 여부를 반환한다.
    private boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;
    }

    // 사용자가 깃발을 꽂기를 선택했는지 여부를 반환한다.
    private boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }

    // action을 입력받는다.
    private UserAction getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }

    // 좌표를 입력받는다.
    private CellPosition getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다.");
        }

        return cellPosition;
    }

    // 게임 패배 여부를 반환한다.
    private boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    // 게임 승리 여부를 반환한다.
    private boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    // 모든 칸이 open 된 경우 승리 처리한다.
    private void checkIfGameIsOver() {
        if (gameBoard.isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    // 게임 상태를 승리로 변경한다.
    private void changeGameStatusToWin() {
        gameStatus = 1;
    }

}
