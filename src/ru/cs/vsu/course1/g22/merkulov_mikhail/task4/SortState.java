package ru.cs.vsu.course1.g22.merkulov_mikhail.task4;

public class SortState {
    private final int[] array;
    private final int i;
    private final int j;
    private final int minIndex;

    public SortState(int[] array, int i, int j, int minIndex) {
        this.array = array.clone();
        this.i = i;
        this.j = j;
        this.minIndex = minIndex;
    }

    public int[] getArray() {
        return array;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getMinIndex() {
        return minIndex;
    }
}
