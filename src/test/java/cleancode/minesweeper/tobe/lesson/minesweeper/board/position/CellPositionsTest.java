package cleancode.minesweeper.tobe.lesson.minesweeper.board.position;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.lesson.minesweeper.board.cell.Cell;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Middle;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.VeryBeginner;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CellPositionsTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("gameLevelProvider")
    @DisplayName("각 게임 레벨의 지뢰 수에 맞게 랜덤으로 지뢰의 위치가 추출된다.")
    void extractRandomPositions(GameLevel gameLevel) {
        // given
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSize();

        Cell[][] board = new Cell[rowSize][colSize];

        CellPositions cellPositions = CellPositions.from(board);

        int landMineCount = gameLevel.getLandMineCount();

        // when
        List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);

        //then
        assertThat(landMinePositions).hasSize(landMineCount);
    }

    private static Stream<Arguments> gameLevelProvider() {
        return Stream.of(
            Arguments.of(Named.of("Very Beginner", new VeryBeginner()), 4, 5),
            Arguments.of(Named.of("Beginner", new Beginner()), 8, 10),
            Arguments.of(Named.of("Middle", new Middle()), 14, 18),
            Arguments.of(Named.of("Advanced", new Advanced()), 20, 24)
        );
    }

}
