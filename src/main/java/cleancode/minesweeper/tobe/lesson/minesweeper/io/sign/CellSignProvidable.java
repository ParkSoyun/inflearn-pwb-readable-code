package cleancode.minesweeper.tobe.lesson.minesweeper.io.sign;

import cleancode.minesweeper.tobe.lesson.minesweeper.board.cell.CellSnapshot;

public interface CellSignProvidable {

    boolean supports(CellSnapshot cellSnapshot);

    String provide(CellSnapshot cellSnapshot);

}
