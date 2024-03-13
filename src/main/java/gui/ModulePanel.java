package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        shsPanel.add(editPanel, gbc);
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
}
