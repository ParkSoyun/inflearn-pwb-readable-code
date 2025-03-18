package cleancode.minesweeper.tobe.lesson.minesweeper.board.cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Middle;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.VeryBeginner;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CellSnapshotTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("cellSnapShotAndCellSnapshotStatusProvider")
    @DisplayName("셀 스냅샷의 상태와 주어진 상태가 같다면 같은 상태이다.")
    void isSameStatus(CellSnapshot cellSnapshot, CellSnapshotStatus cellSnapshotStatus) {
        // given

        // when
        boolean isSameStatusResult = cellSnapshot.isSameStatus(cellSnapshotStatus);

        //then
        assertThat(isSameStatusResult).isTrue();
    }

    private static Stream<Arguments> cellSnapShotAndCellSnapshotStatusProvider() {
        return Stream.of(
            Arguments.of(Named.of("Empty", CellSnapshot.ofEmpty()), CellSnapshotStatus.EMPTY),
            Arguments.of(Named.of("Number", CellSnapshot.ofNumber(1)), CellSnapshotStatus.NUMBER),
            Arguments.of(Named.of("LandMine", CellSnapshot.ofLandMine()), CellSnapshotStatus.LAND_MINE),
            Arguments.of(Named.of("Flag", CellSnapshot.ofFlag()), CellSnapshotStatus.FLAG),
            Arguments.of(Named.of("Unchecked", CellSnapshot.ofUnchecked()), CellSnapshotStatus.UNCHECKED)
        );
    }
}
