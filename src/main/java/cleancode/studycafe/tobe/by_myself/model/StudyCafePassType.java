package cleancode.studycafe.tobe.by_myself.model;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권", LockerRule.CANNOT_USE),
    WEEKLY("주 단위 이용권", LockerRule.CANNOT_USE),
    FIXED("1인 고정석", LockerRule.CAN_USE);

    private final String description;
    private final LockerRule lockerRule;

    StudyCafePassType(String description, LockerRule lockerRule) {
        this.description = description;
        this.lockerRule = lockerRule;
    }

    public boolean cannotUseLocker() {
        return this.lockerRule == LockerRule.CANNOT_USE;
    }

}
