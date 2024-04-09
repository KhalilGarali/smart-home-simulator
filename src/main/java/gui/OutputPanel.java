package main.java.gui;

import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
    // Log file to be written to
    private static final Logger logger = Logger.getLogger(OutputPanel.class.getName());
    public OutputPanel() {
        // Adding handler to logger
        try {
            FileHandler fileHandler = new FileHandler("consoleLog.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating log file", e);
        }

        // Creating console output area with text area
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Console Output"));
        outputConsole = new JTextArea();
        outputConsole.setLineWrap(true);
        outputConsole.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputConsole);
        // Set the preferred height to 100 pixels
        scrollPane.setPreferredSize(new Dimension(getWidth(), 100));

        add(scrollPane);
    }

    // Function to log text to file and print to console
    public void appendText(ArrayList<String> text){
        StringBuilder builder = new StringBuilder();
        String date = DateTime.getDate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy"));
        String time = DateTime.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Log date and time
        builder.append(date + " " + time + "\n");
        outputConsole.append(date + " " + time + "\n");

        // Log text sent from commands
        for (String txt: text){
            outputConsole.append(txt + "\n");
            builder.append(txt + "\n");
        }

        // Log info event is triggered by
        outputConsole.append("Event triggered by user " + shs.activeUser.getClass().getSimpleName() + " " + shs.activeUser.getName() + "\n");
        builder.append("Event triggered by user " + shs.activeUser.getClass().getSimpleName() + " " + shs.activeUser.getName() + "\n");
        outputConsole.append("**************************************************************\n");
        builder.append("**************************************************************");
        logger.log(Level.INFO, builder.toString());
    }

    public static OutputPanel getInstance(){
        if (op == null){
            op = new OutputPanel();
        }
        return op;
    }
}
