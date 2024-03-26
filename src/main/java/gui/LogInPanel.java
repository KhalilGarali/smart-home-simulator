package main.java.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.java.logic.layout.House;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LogInPanel extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton, uploadButton;
    private File uploadedFile;
    private String houseLayoutFilePath;

    public LogInPanel(LogInFrame frame) {
        setLayout(new GridBagLayout()); // Use GridBagLayout for more flexibility
        setBorder(BorderFactory.createTitledBorder("Log In"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        username = new JTextField(20);
        password = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Configure grid bag constraints and add components
        gbc.insets = new Insets(10, 10, 5, 10); // top, left, bottom, right padding
        add(new JLabel("Enter your name: "), gbc);

        gbc.insets = new Insets(5, 10, 10, 10); // Adjust the insets as needed
        add(username, gbc);

        add(new JLabel("Enter your password: "), gbc);
        add(password, gbc);

        gbc.insets = new Insets(10, 10, 10, 10); // Extra padding before the button
        

        // Add an "Upload House Layout" button and label before the "Login" button
        uploadButton = new JButton("Upload House Layout");
        gbc.insets = new Insets(15, 10, 15, 10); // Adjust the insets as needed
        add(uploadButton, gbc);
        add(loginButton, gbc);

        // // Listener for the file upload button
        // uploadButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         JFileChooser fileChooser = new JFileChooser();
        //         fileChooser.setDialogTitle("Select House Layout File");
        //         fileChooser.setAcceptAllFileFilterUsed(false);
        //         fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));


        //         int result = fileChooser.showOpenDialog(null);
        //         if (result == JFileChooser.APPROVE_OPTION) {
        //             uploadedFile = fileChooser.getSelectedFile();
        //             JOptionPane.showMessageDialog(null, "File selected: " + uploadedFile.getAbsolutePath());
        //             // Here you would add the code to handle the uploaded file
        //         } else if (result == JFileChooser.CANCEL_OPTION) {
        //             JOptionPane.showMessageDialog(null, "No file was selected.");
        //         }
        //     }
        // });

         // Action listener for the upload button
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select House Layout File");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
            fileChooser.addChoosableFileFilter(filter);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                houseLayoutFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                JOptionPane.showMessageDialog(null, "File selected: " + houseLayoutFilePath);
                // Here you would pass the file path to the House class, which will handle it
                // For example:
                House.getInstance().loadLayoutFromFile(houseLayoutFilePath);
            }
        });


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameField = username.getText();
                String passwordField = new String(password.getPassword());

                if (usernameField.equals("username") && passwordField.equals("password")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    LogInPanel.goToHome(frame);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            }
        });
    }

    public static void goToHome(LogInFrame frame) {
        HomeSimulatorFrame home = new HomeSimulatorFrame(); // Create a new HomeSimulatorFrame here
        home.setVisible(true);
        frame.dispose();
    }
}
