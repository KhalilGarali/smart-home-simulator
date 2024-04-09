package main.java.gui;

import javax.swing.*;
import java.awt.*;

public class LogInFrame extends JFrame{
    public LogInPanel loginPanel;
    public LogInFrame()
    {
        setTitle("Smart Home Simulator");
        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loginPanel = new LogInPanel(this);
        add(loginPanel, BorderLayout.CENTER);
    }
}
