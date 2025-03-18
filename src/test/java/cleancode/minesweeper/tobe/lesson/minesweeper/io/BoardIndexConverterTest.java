package cleancode.minesweeper.tobe.lesson.minesweeper.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}
