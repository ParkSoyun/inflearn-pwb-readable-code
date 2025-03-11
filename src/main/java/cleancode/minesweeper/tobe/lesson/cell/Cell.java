package cleancode.minesweeper.tobe.lesson.cell;

public interface Cell {

    String FLAG_SIGN = "⚑";
    String UNCHECKED_SIGN = "□";

    boolean isLandMine();

    boolean hasLandMineCount();

    String getSign();

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();

}
