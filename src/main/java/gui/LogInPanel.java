package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;

    public LogInPanel(LogInFrame frame, HomeSimulatorFrame home) {
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
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameField = username.getText();
                String passwordField = new String(password.getPassword());

                if (usernameField.equals("username") && passwordField.equals("password")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    LogInPanel.goToHome(frame, home);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            }
        });
    }

    public static void goToHome(LogInFrame frame, HomeSimulatorFrame home) {
        home.setVisible(true);
        frame.dispose();
    }
}
