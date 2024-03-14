package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends JPanel{
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    public LogInPanel(LogInFrame frame, HomeSimulatorFrame home) {
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridLayout(3, 2));
        setBorder(BorderFactory.createTitledBorder("Log In"));
        username = new JTextField(20);
        password = new JPasswordField(20);
        loginButton = new JButton("Login");
        // Add the SHC panel to the tabbed pane
        add(new JLabel("Enter your name: "));
        add(username);
        add(new JLabel("Enter your password: "));
        add(password);
        add(new JLabel());
        add(loginButton);
        // ... Add other tabs as needed

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameField = username.getText();
                String passwordField = new String(password.getPassword());

                // Perform authentication (dummy check)
                if (usernameField.equals("username") && passwordField.equals("password")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    LogInPanel.goTohome(frame, home);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            }
        });
    }
    public static void goTohome(LogInFrame frame, HomeSimulatorFrame home)
    {
        home.setVisible(true);
        frame.dispose();
    }
}
