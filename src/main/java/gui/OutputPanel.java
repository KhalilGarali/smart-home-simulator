package main.java.gui;

import java.awt.Dimension;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.java.logic.dashboard.DateTime;
import main.java.logic.modules.SHS;

public class OutputPanel extends JPanel {
    private JTextArea outputConsole;
    private static OutputPanel op;
    private SHS shs = SHS.getInstance();
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
        // String date = DateTime.getDate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy"));
        // String time = DateTime.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        // outputConsole.append(date + " " + time + "\n");
        outputConsole.append(text + "\n");
    }

    public void appendText(ArrayList<String> text){
        String date = DateTime.getDate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy"));
        String time = DateTime.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        outputConsole.append(date + " " + time + "\n");
        for (String txt: text){
            outputConsole.append(txt + "\n");
        }
        // This can be subject to change if event is triggered not through GUI
        outputConsole.append("Event triggered by user " + shs.activeUser.getName() + "\n");
        outputConsole.append("**************************************************************\n");
    }
    public static OutputPanel getInstance(){
        if (op == null){
            op = new OutputPanel();
        }
        return op;
    }
}
