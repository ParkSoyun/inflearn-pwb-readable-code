package cleancode.studycafe.tobe.by_myself.io;

import cleancode.studycafe.tobe.by_myself.exception.AppException;
import cleancode.studycafe.tobe.by_myself.model.StudyCafePassType;
import cleancode.studycafe.tobe.by_myself.model.StudyCafeSeatPass;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeFromUser() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }

        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }

        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }

        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafeSeatPass getSeatPassFromUser(List<StudyCafeSeatPass> seatPassList) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return seatPassList.get(selectedIndex);
    }

    public String getLockerSelection() {
        return SCANNER.nextLine();
    }

}
