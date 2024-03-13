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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JTextField;

import main.java.gui.ModulePanelTabs.SHCPanel;
import main.java.logic.users.Child;
import main.java.logic.users.Permissions;
import main.java.logic.users.User;
import main.java.model.rooms.Room;

public class ModulePanel extends JPanel {
    private JTabbedPane tabbedPane;

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
        tabbedPane.addTab("SHC", new JScrollPane(SHCPanel));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));
        tabbedPane.addTab("SHS", new JScrollPane(shsPanel));
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
        JPanel permissionsPanel = createPermissionsPanel();
        shsPanel.add(editPanel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        shsPanel.add(permissionsPanel, gbc);
        return shsPanel;
    }

    private JPanel createEditPanel(JLabel usernameDisplay, JLabel locationDisplay){
        JPanel editPanel = new JPanel(new GridBagLayout());
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit User Profile"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);//Add paddings

        JLabel usernameLabel = new JLabel ("Username: ");
        JLabel locationLabel = new JLabel ("Location: ");
        JLabel passwordLabel = new JLabel ("Password: ");
        JTextField usernameField = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                String newUsername = usernameField.getText();
                String newLocation = locationField.getText();
                if (!newUsername.equals("")) {
                    usernameDisplay.setText("User: " + newUsername);
                }
                if (!newLocation.equals(""))
                {
                    locationDisplay.setText("Location " + newLocation);
                }
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
        gbc.anchor = GridBagConstraints.WEST;
        editPanel.add(locationLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        editPanel.add(locationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        editPanel.add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        editPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(submitButton, gbc);
        
        return editPanel;
    }

    private JPanel createPermissionsPanel() {

        //Hard coded user for testing purposes waiting to get Active user +++++++++++++++++++++++++++++++++++++++++
        User testUser = new Child("Test User");
        List<Permissions> testPermissions = new ArrayList<>();
        testPermissions.add(Permissions.WINDOW);
        testPermissions.add(Permissions.DOOR);
        testUser.setPermissions(testPermissions);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //TODO: Change testUser by Active User once it's implemented

        JPanel permissionsPanel = new JPanel();
        permissionsPanel.setLayout(new BoxLayout(permissionsPanel, BoxLayout.Y_AXIS));
        permissionsPanel.setBorder(BorderFactory.createTitledBorder("User Profile Permissions"));

        JCheckBox windowsCheckBox = new JCheckBox("Open/Close Windows");
        JCheckBox doorsCheckBox = new JCheckBox("Open/Close Doors");
        JCheckBox lightsCheckBox = new JCheckBox("Turn on/off the lights");
        JCheckBox temperatureCheckBox = new JCheckBox("Change House Temperature");

        List<Permissions> tempPermissions = testUser.getPermissions();

        // Add ItemListeners to the checkboxes
        windowsCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (windowsCheckBox.isSelected()) {
                    tempPermissions.add(Permissions.WINDOW);
                } else {
                    if(testUser.getPermissions().contains(Permissions.WINDOW)){
                        tempPermissions.remove(Permissions.WINDOW);
                    }
                }
            }
        });

        doorsCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (doorsCheckBox.isSelected()) {
                    tempPermissions.add(Permissions.DOOR);
                } else {
                    if(testUser.getPermissions().contains(Permissions.DOOR)){
                        tempPermissions.remove(Permissions.DOOR);
                    }                }
            }
        });

        lightsCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (lightsCheckBox.isSelected()) {
                    if()
                    tempPermissions.add(Permissions.LIGHT);
                } else {
                    if(testUser.getPermissions().contains(Permissions.LIGHT)){
                        tempPermissions.remove(Permissions.LIGHT);
                    }                }
            }
        });

        temperatureCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (temperatureCheckBox.isSelected()) {
                    tempPermissions.add(Permissions.TEMP);
                    System.out.println(tempPermissions);
                } else {
                    if(testUser.getPermissions().contains(Permissions.TEMP)){
                        tempPermissions.remove(Permissions.TEMP);
                        System.out.println(tempPermissions);
                    }
                }
            }
        });

        testUser.setPermissions(tempPermissions);

        // Add checkboxes to the permissionsPanel
        permissionsPanel.add(windowsCheckBox);
        permissionsPanel.add(doorsCheckBox);
        permissionsPanel.add(lightsCheckBox);
        permissionsPanel.add(temperatureCheckBox);

        return permissionsPanel;
    }

}
