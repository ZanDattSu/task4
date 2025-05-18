package ru.cs.vsu.course1.g22.merkulov_mikhail.task4;

import java.util.ArrayList;
import java.util.List;

public class SelectionSorter {
    private SelectionSorter() {
    }

    public static List<SortState> getSortStates(int[] array) {
        List<SortState> states = new ArrayList<>();
        int[] a = array.clone();
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            states.add(new SortState(a, i, -1, min));

            for (int j = i + 1; j < n; j++) {
                states.add(new SortState(a, i, j, min));

                if (a[j] < a[min]) {
                    min = j;
                    states.add(new SortState(a, i, j, min));
                }
            }

            swap(a, i, min);
            states.add(new SortState(a, i, -1, min));
        }

        states.add(new SortState(a, n - 1, -1, -1));
        return states;
    }

    private static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
}
