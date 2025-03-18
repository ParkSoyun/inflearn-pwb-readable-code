package cleancode.minesweeper.tobe.lesson.minesweeper.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import cleancode.minesweeper.tobe.lesson.minesweeper.exception.GameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardIndexConverterTest {

    @DisplayName("사용자가 입력한 좌표 값을 보드의 index 값으로 변경한다.")
    @Test
    void boardIndexConvertFromUserInput() {
        // given
        BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
        String userInput = "a1";

        // when
        int rowIndexConverterResult = boardIndexConverter.getSelectedRowIndex(userInput);
        int colIndexConverterResult = boardIndexConverter.getSelectedColIndex(userInput);

        //then
        assertThat(rowIndexConverterResult).isEqualTo(0);
        assertThat(colIndexConverterResult).isEqualTo(0);
    }

    @DisplayName("사용자가 범위 밖의 좌표 값을 입력하면 GameException을 발생시킨다.")
    @Test
    void boardIndexConvertFromOutOfRangeUserInput() {
        // given
        BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
        String userInput = "Z0";

        // when

        //then
        assertThatThrownBy(() -> boardIndexConverter.getSelectedRowIndex(userInput))
            .isInstanceOf(GameException.class)
            .hasMessage("잘못된 입력입니다.");
        assertThatThrownBy(() -> boardIndexConverter.getSelectedColIndex(userInput))
            .isInstanceOf(GameException.class)
            .hasMessage("잘못된 입력입니다.");
    }
}
