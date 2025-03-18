package cleancode.minesweeper.tobe.lesson.minesweeper.board;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Middle;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.VeryBeginner;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameBoardTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("gameLevelProvider")
    @DisplayName("각 게임 레벨에 맞는 게임 보드가 생성된다.")
    void createGameBoardWithGameLevel(GameLevel gameLevel, int expectedRowSize, int expectedColSize) {
        // given

        // when
        GameBoard gameBoard = new GameBoard(gameLevel);

        //then
        assertThat(gameBoard.getRowSize()).isEqualTo(expectedRowSize);
        assertThat(gameBoard.getColSize()).isEqualTo(expectedColSize);
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
