package cleancode.studycafe.tobe.by_myself.model;

public class StudyCafePassBill {

    private final StudyCafeSeatPass seatPassBill;
    private final StudyCafeLockerPass lockerPassBill;

    private StudyCafePassBill(StudyCafeSeatPass seatPassBill, StudyCafeLockerPass lockerPassBill) {
        this.seatPassBill = seatPassBill;
        this.lockerPassBill = lockerPassBill;
    }

    public static StudyCafePassBill of(StudyCafeSeatPass seatPassBill, StudyCafeLockerPass lockerPassBill) {
        return new StudyCafePassBill(seatPassBill, lockerPassBill);
    }

    public StudyCafeSeatPass getSeatPassBill() {
        return seatPassBill;
    }

    public StudyCafeLockerPass getLockerPassBill() {
        return lockerPassBill;
    }
}
