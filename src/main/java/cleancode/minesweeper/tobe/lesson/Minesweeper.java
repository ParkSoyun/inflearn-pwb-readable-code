package cleancode.minesweeper.tobe.lesson;

import cleancode.minesweeper.tobe.lesson.game.GameInitializable;
import cleancode.minesweeper.tobe.lesson.game.GameRunnable;
import cleancode.minesweeper.tobe.lesson.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.lesson.io.InputHandler;
import cleancode.minesweeper.tobe.lesson.io.OutputHandler;

public class Minesweeper implements GameInitializable, GameRunnable {

    private final GameBoard gameBoard;
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
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

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) { // 의도한 예외
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) { // 의도하지 않아서 개발자가 처리해야 하는 예외
                outputHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
            }
        }
    }

    // 사용자에게 입력받은 좌표에 입력받은 행위를 수행한다.
    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(cellInput, gameBoard.getColSize());
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flag(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (gameBoard.isLandMineCell(selectedRowIndex, selectedColIndex)) {
                gameBoard.open(selectedRowIndex, selectedColIndex);
                changGameStatusToLose();
                return;
            }

            gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);
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
    private boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    // 사용자가 깃발을 꽂기를 선택했는지 여부를 반환한다.
    private boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    // action을 입력받는다.
    private String getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserInput();
    }

    // 좌표를 입력받는다.
    private String getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        return inputHandler.getUserInput();
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
