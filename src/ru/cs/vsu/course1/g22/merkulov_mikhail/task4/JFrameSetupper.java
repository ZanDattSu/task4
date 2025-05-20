package ru.cs.vsu.course1.g22.merkulov_mikhail.task4;

import javax.swing.*;
import java.awt.*;

public class JFrameSetupper {

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
                panel.getTimer().start();
                btnPlay.setText("Pause");
            } else {
                panel.getTimer().stop();
                btnPlay.setText("Play");
            }
        });

        buttons.add(btnPrev);
        buttons.add(btnPlay);
        buttons.add(btnNext);
        return buttons;
    }
}
