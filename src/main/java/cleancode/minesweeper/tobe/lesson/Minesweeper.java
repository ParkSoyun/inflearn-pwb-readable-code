package cleancode.minesweeper.tobe.lesson;

import cleancode.minesweeper.tobe.lesson.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.lesson.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.lesson.io.ConsoleOutputHandler;

public class Minesweeper {

    private final GameBoard gameBoard;
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
    private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public Minesweeper(GameLevel gameLevel) {
        gameBoard = new GameBoard(gameLevel);
    }

    public void run() {
        consoleOutputHandler.showGameStartComments();
        gameBoard.initializeGame();

        while (true) {
            try {
                consoleOutputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    consoleOutputHandler.printGameWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    consoleOutputHandler.printGameLosingComment();
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) { // 의도한 예외
                consoleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) { // 의도하지 않아서 개발자가 처리해야 하는 예외
                consoleOutputHandler.printSimpleMessage("프로그램에 문제가 생겼습니다.");
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
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    // 좌표를 입력받는다.
    private String getCellInputFromUser() {
        consoleOutputHandler.printCommentForSelectingCell();
        return consoleInputHandler.getUserInput();
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
