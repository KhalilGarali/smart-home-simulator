package main.java.gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import main.java.logic.dashboard.DateTime;
import main.java.model.fixtures.Temperature;

import javax.swing.JTextField;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.java.gui.ModulePanelTabs.SHCPanel;

public class ModulePanel extends JPanel {
    private JTabbedPane tabbedPane;
    private JButton editButton;

    public ModulePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Modules"));
        tabbedPane = new JTabbedPane();

        // Create Panels Here 
        SHCPanel SHCPanel = new SHCPanel();
        JPanel shsPanel = createShsPanel();

        // Module Tabs
        tabbedPane.addTab("SHC", new JScrollPane(SHCPanel));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));
        tabbedPane.addTab("SHS", new JScrollPane(shsPanel));
        // ... Add other tabs as needed

        add(tabbedPane, BorderLayout.CENTER);
    }


    private JPanel createShsPanel(){
        JPanel shsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel editPanel = createEditPanel();
        shsPanel.add(editPanel, gbc);

        return shsPanel;
    }

    private JPanel createEditPanel(){
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit User Profile"));
    
        JTextField usernameField = new JTextField("username");
        JTextField locationField = new JTextField("location");
    
        editButton = new JButton("Edit Date and Time");
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editButton.setMaximumSize(new Dimension(150, 40)); // Similar to the toggle, ensures the button size
        // editButton.setIcon(new ImageIcon("src\\main\\resources\\editIcon.png"));
        editButton.addActionListener(e -> {
            editSimulationParameters(); // Call method to open edit dialog
        });
    
        editPanel.add(usernameField);
        editPanel.add(locationField);
        editPanel.add(editButton); // Add the edit button to the panel
    
        resizePanel(usernameField);
        resizePanel(locationField);
    
        return editPanel;
    }

    private void resizePanel(JTextField textfield){
        textfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, textfield.getPreferredSize().height));
    }

    // Method to handle editing simulation parameters
    private void editSimulationParameters() {
        // Create a dialog window
        JDialog editDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Parameters", true);
        editDialog.setLayout(new BorderLayout());
    
        // Panel to hold components
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(6, 2, 5, 5));
    
        // Date Labels and TextFields
        JLabel dateEditLabel1 = new JLabel("Year:");
        JTextField dateEditField1 = new JTextField();
        JLabel dateEditLabel2 = new JLabel("Month:");
        JTextField dateEditField2 = new JTextField();
        JLabel dateEditLabel3 = new JLabel("Day:");
        JTextField dateEditField3 = new JTextField();
    
        // Time Label and TextField
        JLabel timeEditLabel1 = new JLabel("Hour:");
        JTextField timeEditField1 = new JTextField();
        JLabel timeEditLabel2 = new JLabel("Minute:");
        JTextField timeEditField2 = new JTextField();
        JLabel timeEditLabel3 = new JLabel("Second:");
        JTextField timeEditField3 = new JTextField();
    
        // Add components to edit panel
        editPanel.add(dateEditLabel1);
        editPanel.add(dateEditField1);
        editPanel.add(dateEditLabel2);
        editPanel.add(dateEditField2);
        editPanel.add(dateEditLabel3);
        editPanel.add(dateEditField3);
        editPanel.add(timeEditLabel1);
        editPanel.add(timeEditField1);
        editPanel.add(timeEditLabel2);
        editPanel.add(timeEditField2);
        editPanel.add(timeEditLabel3);
        editPanel.add(timeEditField3);
    
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
    
        // Add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the entered values to integers
                    int year = Integer.parseInt(dateEditField1.getText());
                    int month = Integer.parseInt(dateEditField2.getText());
                    int day = Integer.parseInt(dateEditField3.getText());

                    if (month == 5  || month == 6 || month == 8 || month == 9) {
                        Temperature.setTemperature(25);
                    } else if (month == 1 || month == 2 || month == 12) {
                        Temperature.setTemperature(0);
                    } else if (month == 3 || month == 4 || month == 10 || month == 11) {
                        Temperature.setTemperature(10);
                    } else if (month == 7) {
                        Temperature.setTemperature(30);
                    } else {
                        Temperature.setTemperature(10);
                    }
    
                    // Check if the date is valid
                    if (isValidDate(year, month, day)) {
                        int hour = Integer.parseInt(timeEditField1.getText());
                        int minute = Integer.parseInt(timeEditField2.getText());
                        int second = Integer.parseInt(timeEditField3.getText());
    
                        // Update date and time labels with user input
                        DateTime.setDate(year, month, day);
                        DateTime.setTime(hour, minute, second);
    
                        // Close the dialog
                        editDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(editDialog, "Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Display an error message if parsing fails
                    JOptionPane.showMessageDialog(editDialog, "Please enter valid integers for date fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without saving changes
                editDialog.dispose();
            }
        });
    
        // Add buttons to button panel
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
    
        // Add panels to dialog
        editDialog.add(editPanel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);
    
        // Set dialog properties
        editDialog.setSize(300, 200);
        editDialog.setLocationRelativeTo(null); // Center the dialog
        editDialog.setVisible(true); // Display the dialog
    }

    // Method to validate if the given year, month, and day form a valid date
    private boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
