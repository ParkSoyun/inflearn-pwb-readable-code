package cleancode.minesweeper.tobe.lesson.minesweeper.board.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellPositionTest {

    @DisplayName("(2, 3)에 대해서 상대 위치(-1, 1)을 계산한 결과는 (1, 4)이다.")
    @Test
    void calculatePositionBy() {
        // given
        CellPosition cellPosition = CellPosition.of(2, 3);
        RelativePosition relativePosition = RelativePosition.of(-1, 1);

        // when
        CellPosition calculatedPositionBy = cellPosition.calculatePositionBy(relativePosition);

        //then
        assertThat(calculatedPositionBy.getRowIndex()).isEqualTo(1);
        assertThat(calculatedPositionBy.getColIndex()).isEqualTo(4);
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

}
