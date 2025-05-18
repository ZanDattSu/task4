package ru.cs.vsu.course1.g22.merkulov_mikhail.task4;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelVisualizer extends JPanel {
    public static final int TIMER_DELAY_MS = 200;

    private final List<SortState> states;
    private int currentIndex = 0;
    private final Timer timer;

    public PanelVisualizer(int[] array) {
        this.states = SelectionSorter.getSortStates(array);
        setPreferredSize(new Dimension(1080, 720));
        timer = new Timer(TIMER_DELAY_MS, _ -> nextState());
    }

    private void nextState() {
        if (currentIndex < states.size() - 1) {
            currentIndex++;
            repaint();
        } else {
            timer.stop();
        }
    }

    private void prevState() {
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

    public static void frameSetup(PanelVisualizer panel) {
        JFrame frame = getJFrame(panel);

        JPanel buttons = getButtonsPanel(panel);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JFrame getJFrame(PanelVisualizer panel) {
        JFrame frame = new JFrame("Selection Sort Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        return frame;
    }

    private static JPanel getButtonsPanel(PanelVisualizer panel) {
        JPanel buttons = new JPanel();
        JButton btnPrev = new JButton("Previous");
        JButton btnNext = new JButton("Next");
        JToggleButton btnPlay = new JToggleButton("Play");

        btnPrev.addActionListener(_ -> panel.prevState());
        btnNext.addActionListener(_ -> panel.nextState());
        btnPlay.addItemListener(_ -> {
            if (btnPlay.isSelected()) {
                panel.timer.start();
                btnPlay.setText("Pause");
            } else {
                panel.timer.stop();
                btnPlay.setText("Play");
            }
        });

        buttons.add(btnPrev);
        buttons.add(btnPlay);
        buttons.add(btnNext);
        return buttons;
    }
}
