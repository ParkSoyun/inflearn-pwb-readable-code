package cleancode.minesweeper.tobe.lesson;

import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {

    private static String[][] board = new String[8][10];
    private static Integer[][] landMineCounts = new Integer[8][10];
    private static boolean[][] landMines = new boolean[8][10];
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Scanner scanner = new Scanner(System.in);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = "□";
            }
        } // 10*8 크기의 게임 보드 판을 만든다.
        for (int i = 0; i < 10; i++) {
            int col = new Random().nextInt(10);
            int row = new Random().nextInt(8);
            landMines[row][col] = true;
        } // 10개의 지뢰를 보드 판에 랜덤하게 생성한다.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                int count = 0;
                if (!landMines[row][col]) { // 지뢰가 있는 칸이 아니라면
                    if (row - 1 >= 0 && col - 1 >= 0 && landMines[row - 1][col - 1]) {
                        count++;
                    }
                    if (row - 1 >= 0 && landMines[row - 1][col]) {
                        count++;
                    }
                    if (row - 1 >= 0 && col + 1 < 10 && landMines[row - 1][col + 1]) {
                        count++;
                    }
                    if (col - 1 >= 0 && landMines[row][col - 1]) {
                        count++;
                    }
                    if (col + 1 < 10 && landMines[row][col + 1]) {
                        count++;
                    }
                    if (row + 1 < 8 && col - 1 >= 0 && landMines[row + 1][col - 1]) {
                        count++;
                    }
                    if (row + 1 < 8 && landMines[row + 1][col]) {
                        count++;
                    }
                    if (row + 1 < 8 && col + 1 < 10 && landMines[row + 1][col + 1]) {
                        count++;
                    }
                    landMineCounts[row][col] = count;
                    continue;
                } // 본인 기준으로 둘러싸고 있는 8개의 칸에 지뢰가 몇 개인지 세어서 landMineCounts 배열에 넣어준다.
                landMineCounts[row][col] = 0;
            }
        }
        while (true) {
            System.out.println("   a b c d e f g h i j");
            for (int row = 0; row < 8; row++) {
                System.out.printf("%d  ", row + 1);
                for (int col = 0; col < 10; col++) {
                    System.out.print(board[row][col] + " ");
                }
                System.out.println();
            } // 화면에 게임 보드 판을 그린다.
            if (gameStatus == 1) { // 게임에서 승리한 경우
                System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                break;
            }
            if (gameStatus == -1) { // 게임에서 패배한 경우
                System.out.println("지뢰를 밟았습니다. GAME OVER!");
                break;
            }
            System.out.println();
            System.out.println("선택할 좌표를 입력하세요. (예: a1)");
            String cellInput = scanner.nextLine();
            System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
            String userActionInput = scanner.nextLine();
            char cellInputCol = cellInput.charAt(0);
            char cellInputRow = cellInput.charAt(1);
            int selectedColIndex;
            switch (cellInputCol) { // Board 배열의 인덱스 값으로 치환해 준다.
                case 'a':
                    selectedColIndex = 0;
                    break;
                case 'b':
                    selectedColIndex = 1;
                    break;
                case 'c':
                    selectedColIndex = 2;
                    break;
                case 'd':
                    selectedColIndex = 3;
                    break;
                case 'e':
                    selectedColIndex = 4;
                    break;
                case 'f':
                    selectedColIndex = 5;
                    break;
                case 'g':
                    selectedColIndex = 6;
                    break;
                case 'h':
                    selectedColIndex = 7;
                    break;
                case 'i':
                    selectedColIndex = 8;
                    break;
                case 'j':
                    selectedColIndex = 9;
                    break;
                default:
                    selectedColIndex = -1;
                    break;
            }
            int selectedRowIndex = Character.getNumericValue(cellInputRow) - 1; // Board 배열의 인덱스 값으로 치환해 준다.
            if (userActionInput.equals("2")) { // 깃발을 꽂는 경우
                board[selectedRowIndex][selectedColIndex] = "⚑";
                boolean isAllOpened = true;
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 10; col++) {
                        if (board[row][col].equals("□")) {
                            isAllOpened = false;
                        }
                    }
                } // 보드 판 전체를 돌면서 아직 open 되지 않은 칸이 있는지 확인한다.
                if (isAllOpened) {
                    gameStatus = 1;
                }
            } else if (userActionInput.equals("1")) { // open하는 경우
                if (landMines[selectedRowIndex][selectedColIndex]) { // 지뢰가 있는 칸인 경우
                    board[selectedRowIndex][selectedColIndex] = "☼";
                    gameStatus = -1;
                    continue;
                } else { // 지뢰가 있는 칸이 아닌 경우
                    open(selectedRowIndex, selectedColIndex);
                }
                boolean isAllOpened = true;
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 10; col++) {
                        if (board[row][col].equals("□")) {
                            isAllOpened = false;
                        }
                    }
                } // 보드 판 전체를 돌면서 아직 open 되지 않은 칸이 있는지 확인한다.
                if (isAllOpened) {
                    gameStatus = 1;
                }
            } else {
                System.out.println("잘못된 번호를 선택하셨습니다.");
            }
        }
    }

    // 해당 보드 칸을 open한다.
    private static void open(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 10) { // 보드 판을 벗어나는 경우
            return;
        }
        if (!board[row][col].equals("□")) { // 이미 open되어 있는 칸인 경우
            return;
        }
        if (landMines[row][col]) { // 지뢰가 있는 칸인 경우
            return;
        }
        if (landMineCounts[row][col] != 0) { // 본인을 둘러싸고 있는 8개의 칸에 존재하는 지뢰 개수가 0이 아닌 경우
            board[row][col] = String.valueOf(landMineCounts[row][col]);
            return;
        } else { // 본인을 둘러싸고 있는 8개의 칸에 존재하는 지뢰 개수가 0인 경우
            board[row][col] = "■";
        }
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

}
