package ru.cs.vsu.course1.g22.merkulov_mikhail.task4;

import java.util.Random;

// min - красный, i - зеленый, j - оранжевый, остальные - серый

public class Task {
    public static void main(String[] args) {
        PanelVisualizer panel = new PanelVisualizer(getRandomData());
        panel.setup();
    }

    private static int[] getRandomData() {
        Random random = new Random();
        int size = random.nextInt(8, 12);
        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(-50, 50);
        }
        return data;
    }
}