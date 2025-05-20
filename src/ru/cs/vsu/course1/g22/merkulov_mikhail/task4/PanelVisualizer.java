package ru.cs.vsu.course1.g22.merkulov_mikhail.task4;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelVisualizer extends JPanel {
    public static final int STEP_DELAY_MS = 300;

    private final List<SortState> states;
    private int currentIndex = 0;
    private final Timer timer;

    public PanelVisualizer(int[] array) {
        this.states = SelectionSorter.getSortStates(array);
        setPreferredSize(new Dimension(1080, 720));
        timer = new Timer(STEP_DELAY_MS, _ -> nextState());
    }

    public Timer getTimer() {
        return timer;
    }

    public void nextState() {
        if (currentIndex < states.size() - 1) {
            currentIndex++;
            repaint();
        } else {
            timer.stop();
        }
    }

    public void prevState() {
        if (currentIndex > 0) {
            currentIndex--;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (states.isEmpty()) return;

        SortState state = states.get(currentIndex);
        int[] a = state.getArray();
        int max = a[0];
        int min = a[0];
        for (int el : a) {
            if (el < min) min = el;
            if (el > max) max = el;
        }

        int range = max != min ? max - min : 1;
        int zeroY = computeZeroY(max, min);

        int colWidth = getWidth() / a.length;
        for (int k = 0; k < a.length; k++) {
            int colHeight = (getHeight() - 40) * Math.abs(a[k]) / range;

            int x = k * colWidth;
            int y = (a[k] >= 0) ? zeroY - colHeight : zeroY;

            setColor(g, k, state);
            g.fillRect(x, y, colWidth - 2, colHeight);

            drawNumber(g, a, k, x, colWidth, y, colHeight);
        }
        g.drawLine(0, zeroY, getWidth(), zeroY);
    }

    private int computeZeroY(int max, int min) {
        int range = max != min ? max - min : 1;
        double percentFromTop = (double) max / range;
        return (int) (getHeight() * percentFromTop);
    }

    private static void setColor(Graphics g, int k, SortState state) {
        if (k == state.getMinIndex()) {
            g.setColor(Color.RED);
        } else if (k == state.getI()) {
            g.setColor(Color.GREEN);
        } else if (k == state.getJ()) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
    }

    private static void drawNumber(Graphics g, int[] a, int k, int x, int colWidth, int y, int colHeight) {
        String valueStr = String.valueOf(a[k]);
        FontMetrics fm = g.getFontMetrics();
        int strWidth = fm.stringWidth(valueStr);
        int strX = x + (colWidth - strWidth) / 2;
        int strY = (a[k] >= 0) ? y - 5 : y + colHeight + fm.getAscent() + 2;

        g.setColor(Color.BLACK);
        g.drawString(valueStr, strX, strY);
    }

    public void setup() {
        JFrameSetupper.frameSetup(this);
    }
}
