package cleancode.minesweeper.tobe.lesson.minesweeper.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.Middle;
import cleancode.minesweeper.tobe.lesson.minesweeper.gamelevel.VeryBeginner;
import cleancode.minesweeper.tobe.lesson.minesweeper.user.UserAction;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ConsoleInputHandlerTest {

//    @ParameterizedTest(name = "[{index}] {0}")
//    @MethodSource("userInputAndUserActionProvider")
//    @DisplayName("사용자가 입력한 값에 맞는 UserAction을 반환한다.")
//    void getUserActionFromUser(String userActionInput, UserAction expectedUserAction) {
//        // given
//        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
//
//        InputStream inputStream = new ByteArrayInputStream(userActionInput.getBytes());
//        System.setIn(inputStream);
//
//        // when
//        UserAction userAction = consoleInputHandler.getUserActionFromUser();
//
//        //then
//        assertThat(userAction).isEqualTo(expectedUserAction);
//    }
//
//    private static Stream<Arguments> userInputAndUserActionProvider() {
//        return Stream.of(
//            Arguments.of(Named.of("1: OPEN", "1"), UserAction.OPEN),
//            Arguments.of(Named.of("2: FLAG", "2"), UserAction.FLAG),
//            Arguments.of(Named.of("0: UNKNOWN", "0"), UserAction.UNKNOWN)
//        );
//    }

}
