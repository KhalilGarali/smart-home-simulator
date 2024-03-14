package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import main.java.logic.modules.SHS;
import main.java.model.fixtures.Temperature;

import javax.swing.JTextField;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.java.gui.ModulePanelTabs.SHCPanel;
import main.java.logic.users.*;
import main.java.model.rooms.Room;

public class ModulePanel extends JPanel {
    private JTabbedPane tabbedPane;
    private JButton editButton;
    private SHS shs = SHS.getInstance();

    public ModulePanel(JLabel usernameDisplay, JLabel locationDisplay) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Modules"));
        tabbedPane = new JTabbedPane();

        // Create Panels Here
        SHCPanel SHCPanel = new SHCPanel();
        // Create SHC Panel
        JPanel shcPanel = createShcPanel();
        JPanel shsPanel = createShsPanel(usernameDisplay, locationDisplay);

        // Module Tabs
        tabbedPane.addTab("SHS", new JScrollPane(shsPanel));
        tabbedPane.addTab("SHC", new JScrollPane(SHCPanel));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));
        // ... Add other tabs as needed

        add(tabbedPane, BorderLayout.CENTER);
    }

      private JPanel createShcPanel() {
        JPanel shcPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Items Panel
        JPanel itemsPanel = createItemsPanel();

        // Open/Close Panel
        JPanel openClosePanel = createOpenClosePanel();

        // Add the items and open/close panels to the SHC panel
        shcPanel.add(itemsPanel, gbc);
        shcPanel.add(openClosePanel, gbc);

        return shcPanel;
    }

    private JPanel createItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Items"));

        // Create and add buttons
        JToggleButton windowsButton = new JToggleButton("Windows");
        JToggleButton lightsButton = new JToggleButton("Lights");
        JToggleButton doorsButton = new JToggleButton("Doors");

        ButtonGroup itemsGroup = new ButtonGroup();
        itemsGroup.add(windowsButton);
        itemsGroup.add(lightsButton);
        itemsGroup.add(doorsButton);

        // resize buttons
        resizeButton(windowsButton);
        resizeButton(lightsButton);
        resizeButton(doorsButton);

        itemsPanel.add(windowsButton);
        itemsPanel.add(lightsButton);
        itemsPanel.add(doorsButton);

        return itemsPanel;
    }

    private JPanel createOpenClosePanel() {
        JPanel openClosePanel = new JPanel();
        openClosePanel.setLayout(new BoxLayout(openClosePanel, BoxLayout.Y_AXIS));
        openClosePanel.setBorder(BorderFactory.createTitledBorder("Open/Close"));
        
        // Create and add checkboxes
        openClosePanel.add(new JCheckBox("Garage"));
        openClosePanel.add(new JCheckBox("Living Room"));
        openClosePanel.add(new JCheckBox("Backyard"));

        return openClosePanel;
    }

    private void resizeButton(JToggleButton button) {
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
    }

    private JPanel createShsPanel(JLabel usernameDisplay, JLabel locationDisplay){
        JPanel shsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JPanel editPanel = createEditPanel(usernameDisplay, locationDisplay);
        shsPanel.add(editPanel, gbc);
        return shsPanel;
    }

    private JPanel createEditPanel(JLabel usernameDisplay, JLabel locationDisplay){

        //Hard coded user for testing purposes waiting to get Active user +++++++++++++++++++++++++++++++++++++++++
        User testUser = new Parent("Test User");
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //TODO: Change testUser by Active User once it's implemented


        JPanel editPanel = new JPanel(new GridBagLayout());
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit User Profile"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);//Add paddings

        JLabel usernameLabel = new JLabel ("Username: ");
        JTextField usernameField = new JTextField(20);
          usernameField.setText(shs.activeUser.getName());


        //Permission checkboxes
        JCheckBox windowsCheckBox = new JCheckBox("Open/Close Windows");
            windowsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.WINDOW) ? true : false);
        JCheckBox doorsCheckBox = new JCheckBox("Open/Close Doors");
            doorsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.DOOR) ? true : false);
        JCheckBox lightsCheckBox = new JCheckBox("Turn on/off the lights");
            lightsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.LIGHT) ? true : false);
        JCheckBox temperatureCheckBox = new JCheckBox("Change House Temperature");
            temperatureCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.TEMP) ? true : false);

        List<Permissions> tempPermissions = shs.activeUser.getPermissions();


        // Add checkboxes to the permissionsPanel
        editPanel.add(windowsCheckBox);
        editPanel.add(doorsCheckBox);
        editPanel.add(lightsCheckBox);
        editPanel.add(temperatureCheckBox);


        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                String newUsername = usernameField.getText();

                //Check if WINDOW permission is selected
                if (windowsCheckBox.isSelected()) {
                    if(!shs.activeUser.getPermissions().contains(Permissions.WINDOW)){
                        tempPermissions.add(Permissions.WINDOW);
                    }
                } else {
                    if(shs.activeUser.getPermissions().contains(Permissions.WINDOW)){
                        tempPermissions.remove(Permissions.WINDOW);
                    }
                }

                //Check if LIGHT permission is selected
                if (lightsCheckBox.isSelected()) {
                    if(!shs.activeUser.getPermissions().contains(Permissions.LIGHT)){
                        tempPermissions.add(Permissions.LIGHT);
                    }
                } else {
                    if(shs.activeUser.getPermissions().contains(Permissions.LIGHT)){
                        tempPermissions.remove(Permissions.LIGHT);
                    }
                }

                //Check if DOOR permission is selected
                if (doorsCheckBox.isSelected()) {
                    if(!shs.activeUser.getPermissions().contains(Permissions.DOOR)) {
                        tempPermissions.add(Permissions.DOOR);
                    }
                } else {
                    if(shs.activeUser.getPermissions().contains(Permissions.DOOR)){
                        tempPermissions.remove(Permissions.DOOR);
                    }                }

                //Check if TEMPERATURE permission is selected
                if (temperatureCheckBox.isSelected()) {
                    if(!shs.activeUser.getPermissions().contains(Permissions.TEMP)) {
                        tempPermissions.add(Permissions.TEMP);
                    }
                } else {
                    if(shs.activeUser.getPermissions().contains(Permissions.TEMP)){
                        tempPermissions.remove(Permissions.TEMP);
                    }
                }

                shs.activeUser.setName(newUsername);
                shs.activeUser.setPermissions(tempPermissions);
                //TODO: Update the user in left panel
            }
        });
        editButton = new JButton("Edit Date and Time");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSimulationParameters();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        editPanel.add(usernameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        editPanel.add(usernameField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(windowsCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(lightsCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(doorsCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(temperatureCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(editButton, gbc);
        
        return editPanel;
    }

    private void resizePanel(JTextField textfield){
        textfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, textfield.getPreferredSize().height));
    }

    // Method to handle editing simulation parameters
    private void editSimulationParameters() {
        // Create a dialog window
        JDialog editDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Date and Time", true);
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

//    private JPanel createPermissionsPanel() {
//
//        //Hard coded user for testing purposes waiting to get Active user +++++++++++++++++++++++++++++++++++++++++
//        User testUser = new Child("Test User");
//        List<Permissions> testPermissions = new ArrayList<>();
//        testPermissions.add(Permissions.WINDOW);
//        testPermissions.add(Permissions.DOOR);
//        testUser.setPermissions(testPermissions);
//        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//
//        //TODO: Change testUser by Active User once it's implemented
//
//        JPanel permissionsPanel = new JPanel();
//        permissionsPanel.setLayout(new BoxLayout(permissionsPanel, BoxLayout.Y_AXIS));
//        permissionsPanel.setBorder(BorderFactory.createTitledBorder("User Profile Permissions"));
//
//        JCheckBox windowsCheckBox = new JCheckBox("Open/Close Windows");
//        JCheckBox doorsCheckBox = new JCheckBox("Open/Close Doors");
//        JCheckBox lightsCheckBox = new JCheckBox("Turn on/off the lights");
//        JCheckBox temperatureCheckBox = new JCheckBox("Change House Temperature");
//
//        List<Permissions> tempPermissions = testUser.getPermissions();
//
//        // Add ItemListeners to the checkboxes
//        windowsCheckBox.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                if (windowsCheckBox.isSelected()) {
//                    tempPermissions.add(Permissions.WINDOW);
//                } else {
//                    if(testUser.getPermissions().contains(Permissions.WINDOW)){
//                        tempPermissions.remove(Permissions.WINDOW);
//                    }
//                }
//            }
//        });
//
//        doorsCheckBox.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                if (doorsCheckBox.isSelected()) {
//                    tempPermissions.add(Permissions.DOOR);
//                } else {
//                    if(testUser.getPermissions().contains(Permissions.DOOR)){
//                        tempPermissions.remove(Permissions.DOOR);
//                    }                }
//            }
//        });
//
//        lightsCheckBox.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                if (lightsCheckBox.isSelected()) {
//                    tempPermissions.add(Permissions.LIGHT);
//                } else {
//                    if(testUser.getPermissions().contains(Permissions.LIGHT)){
//                        tempPermissions.remove(Permissions.LIGHT);
//                    }                }
//            }
//        });
//
//        temperatureCheckBox.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                if (temperatureCheckBox.isSelected()) {
//                    tempPermissions.add(Permissions.TEMP);
//                    System.out.println(tempPermissions);
//                } else {
//                    if(testUser.getPermissions().contains(Permissions.TEMP)){
//                        tempPermissions.remove(Permissions.TEMP);
//                        System.out.println(tempPermissions);
//                    }
//                }
//            }
//        });
//
//        testUser.setPermissions(tempPermissions);
//
//        // Add checkboxes to the permissionsPanel
//        permissionsPanel.add(windowsCheckBox);
//        permissionsPanel.add(doorsCheckBox);
//        permissionsPanel.add(lightsCheckBox);
//        permissionsPanel.add(temperatureCheckBox);
//
//        return permissionsPanel;
//    }

}
