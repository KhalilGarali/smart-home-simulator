package main.java.gui;

import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private File file;
    private FileWriter writer;
    public OutputPanel() {
        file = new File("consoleLog.txt");
        if (file.exists()) {
            file.delete();
        }
        try {
            boolean isCreated = file.createNewFile();
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
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

    // Append text to the output console and file
    public void writeText(String text) {
        try {
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputConsole.append(text + "\n");
    }

    public void appendText(ArrayList<String> text){
        String date = DateTime.getDate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy"));
        String time = DateTime.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        writeText(date + " " + time);
        for (String txt: text){
            writeText(txt);
        }
        // This can be subject to change if event is triggered not through GUI
        writeText("Event triggered by user " + shs.activeUser.getName());
        writeText("**************************************************************");
    }

    public static OutputPanel getInstance(){
        if (op == null){
            op = new OutputPanel();
        }
        return op;
    }
}
