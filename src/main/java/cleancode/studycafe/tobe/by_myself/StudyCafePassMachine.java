package cleancode.studycafe.tobe.by_myself;

import cleancode.studycafe.tobe.by_myself.exception.AppException;
import cleancode.studycafe.tobe.by_myself.io.InputHandler;
import cleancode.studycafe.tobe.by_myself.io.OutputHandler;
import cleancode.studycafe.tobe.by_myself.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.by_myself.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.by_myself.model.StudyCafePassBill;
import cleancode.studycafe.tobe.by_myself.model.StudyCafePassType;
import cleancode.studycafe.tobe.by_myself.model.StudyCafeSeatPass;
import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();


    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafeSeatPass seatPassBill = getSeatPassBill();
            Optional<StudyCafeLockerPass> lockerPassBill = getLockerPassBill(seatPassBill);

            StudyCafePassBill studyCafePassBill = StudyCafePassBill.of(seatPassBill, lockerPassBill.orElse(null));

            outputHandler.showPassOrderSummary(studyCafePassBill);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass getSeatPassBill() {
        StudyCafePassType studyCafePassType = getPassTypeFromUser();

        return selectedSeatPass(studyCafePassType);
    }

    private StudyCafePassType getPassTypeFromUser() {
        outputHandler.askPassTypeSelection();

        return inputHandler.getPassTypeFromUser();
    }

    private StudyCafeSeatPass selectedSeatPass(StudyCafePassType studyCafePassType) {
        List<StudyCafeSeatPass> seatPasseCandidates = getSeatPasseCandidates(studyCafePassType);

        outputHandler.showPassListForSelection(seatPasseCandidates);

        return inputHandler.getSeatPassFromUser(seatPasseCandidates);
    }

    private Optional<StudyCafeLockerPass> getLockerPassBill(StudyCafeSeatPass selectedSeatPass) {
        if (selectedSeatPass.cannotUseLocker()) {
            return Optional.empty();
        }

        StudyCafeLockerPass lockerPass = getLockerPassCandidate(selectedSeatPass);

        assert lockerPass != null;
        outputHandler.askLockerPass(lockerPass);
        inputHandler.getLockerSelection();

        return Optional.of(lockerPass);
    }

    private StudyCafeLockerPass getLockerPassCandidate(StudyCafeSeatPass selectedSeatPass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedSeatPass.getPassType()
                                && option.getDuration() == selectedSeatPass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }

    private List<StudyCafeSeatPass> getSeatPasseCandidates(StudyCafePassType studyCafePassType) {
        List<StudyCafeSeatPass> seatPasses = studyCafeFileHandler.readSeatPasses();

        return seatPasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }

}
