package cleancode.minesweeper.tobe.lesson.minesweeper.io.sign;

import cleancode.minesweeper.tobe.lesson.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.lesson.minesweeper.board.cell.CellSnapshotStatus;

public class EmptyCellSignProvider implements CellSignProvidable {

    private static final String EMPTY_SIGN = "■";

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(CellSnapshotStatus.EMPTY);
    }

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return EMPTY_SIGN;
    }

}
