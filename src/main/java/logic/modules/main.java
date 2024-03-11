package main.java.logic.modules;

import javax.swing.*;
import main.java.gui.HomeSimulatorFrame;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeSimulatorFrame frame = new HomeSimulatorFrame();
            frame.setVisible(true);
        });
    }
}
