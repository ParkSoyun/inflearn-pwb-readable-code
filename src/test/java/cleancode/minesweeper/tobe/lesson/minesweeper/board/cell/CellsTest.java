package cleancode.minesweeper.tobe.lesson.minesweeper.board.cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellsTest {

    @DisplayName("빈 셀과 숫자 셀에 대하여 모든 셀이 open 되었을 때 모든 셀이 체크된 것이 맞는지 확인한다.")
    @Test
    void isAllEmptyCellNumberCellChecked() {
        // given
        List<Cell> cellList = List.of(
            new EmptyCell(),
            new NumberCell(1),
            new EmptyCell(),
            new NumberCell(2)
        );

        Cells cells = Cells.of(cellList);

        for(Cell cell : cellList) {
            cell.open();
        }

        // when
        boolean isAllCheckedResult = cells.isAllChecked();

        //then
        assertThat(isAllCheckedResult).isTrue();
    }


    @DisplayName("지뢰 셀이 flag 되지 않았다면 모든 셀이 체크된 것이 아니다.")
    @Test
    void isAllCheckedExceptLandMineCell() {
        // given
        List<Cell> cellList = List.of(
            new LandMineCell(),
            new EmptyCell(),
            new EmptyCell()
        );

        Cells cells = Cells.of(cellList);

        for(Cell cell : cellList) {
            if(cell.isLandMine()) {
                continue;
            }

            cell.open();
        }

        // when
        boolean isAllCheckResult = cells.isAllChecked();

        //then
        assertThat(isAllCheckResult).isFalse();
    }

}
