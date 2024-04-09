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
    private static final Logger logger = Logger.getLogger(OutputPanel.class.getName());
    public OutputPanel() {
        try {
            FileHandler fileHandler = new FileHandler("consoleLog.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating log file", e);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Console Output"));
        outputConsole = new JTextArea();
        outputConsole.setLineWrap(true);
        outputConsole.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputConsole);
        scrollPane.setPreferredSize(new Dimension(getWidth(), 100)); // Set the preferred height to 100 pixels
        add(scrollPane);
    }

    public void appendText(ArrayList<String> text){
        String date = DateTime.getDate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy"));
        String time = DateTime.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        StringBuilder builder = new StringBuilder();
        builder.append(date + " " + time + "\n");
        outputConsole.append(date + " " + time + "\n");
        for (String txt: text){
            outputConsole.append(txt + "\n");
            builder.append(txt + "\n");
        }
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
