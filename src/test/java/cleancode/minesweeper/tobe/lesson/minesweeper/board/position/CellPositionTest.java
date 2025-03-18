package cleancode.minesweeper.tobe.lesson.minesweeper.board.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CellPositionTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("relativePositionInRangeProvider")
    @DisplayName("(0, 0)에 대해서 상대 위치(0, 1), (1, 0), (1, 1)을 계산한 좌표는 (0, 1), (1, 0), (1, 1)이다.")
    void calculatePositionToInRange(RelativePosition relativePositionInRange, int expectedRowIndex, int expectedColIndex) {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        CellPosition calculatedPositionBy = cellPosition.calculatePositionBy(relativePositionInRange);

        //then
        assertThat(calculatedPositionBy.getRowIndex()).isEqualTo(expectedRowIndex);
        assertThat(calculatedPositionBy.getColIndex()).isEqualTo(expectedColIndex);
    }

    @DisplayName("(0, 0)에 대해서 상대 위치(-1, -1)을 계산한 좌표는 이동이 불가능한 좌표다.")
    @Test
    void calculatePositionToOutOfRange() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);
        RelativePosition relativePosition = RelativePosition.of(-1, -1);

        // when

        //then
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(relativePosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("움직일 수 있는 좌표가 아닙니다.");
    }

    private static Stream<Arguments> relativePositionInRangeProvider() {
        return Stream.of(
            Arguments.of(Named.of("상대 위치 : (0, 1)", RelativePosition.of(0, 1)), 0, 1),
            Arguments.of(Named.of("상대 위치 : (1, 0)", RelativePosition.of(1, 0)), 1, 0),
            Arguments.of(Named.of("상대 위치 : (1, 1)", RelativePosition.of(1, 1)), 1, 1)
        );
    }

}
