package main.java.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutputPanel extends JPanel {
    private JTextArea outputConsole;

    public OutputPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Console Output"));
        outputConsole = new JTextArea();
        outputConsole.setLineWrap(true);
        outputConsole.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputConsole);
        scrollPane.setPreferredSize(new Dimension(getWidth(), 100)); // Set the preferred height to 100 pixels
        add(scrollPane);
    }

     // Provide a method to append text to the output console
     public void appendText(String text) {
        outputConsole.append(text);
    }

}
