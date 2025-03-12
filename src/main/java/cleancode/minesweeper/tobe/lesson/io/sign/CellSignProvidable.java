package cleancode.minesweeper.tobe.lesson.io.sign;

import cleancode.minesweeper.tobe.lesson.cell.CellSnapshot;

public interface CellSignProvidable {

    boolean supports(CellSnapshot cellSnapshot);

    String provide(CellSnapshot cellSnapshot);

}
