package main.java.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.java.logic.observerPattern.*;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import java.util.Iterator;

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
import main.java.logic.layout.House;
import main.java.logic.modules.SHS;
import main.java.logic.users.*;
import main.java.model.rooms.Room;
import main.java.logic.users.*;
import main.java.model.rooms.Room;

public class ModulePanel extends JPanel implements Observer{
    
    private JTabbedPane tabbedPane;
    House house = House.getInstance();
    private SHS shs = SHS.getInstance();
    
    List<User> listOfUsers = shs.getHouseUser();
    private JButton editButton;

    JTextField usernameField;
    JCheckBox windowsCheckBox, doorsCheckBox, lightsCheckBox, temperatureCheckBox;

    private static OutputPanel outpanel = OutputPanel.getInstance();

    public ModulePanel(JLabel usernameDisplay, JLabel locationDisplay) {
        shs.addObserver(this);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Modules"));

        // Create SHC Here
        SHCPanel SHCPanel = new SHCPanel();
        // Create SHS Panel
        JPanel shsPanel = createShsPanel(usernameDisplay, locationDisplay);

        // Module Tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("SHS", new JScrollPane(shsPanel));
        tabbedPane.addTab("SHC", new JScrollPane(SHCPanel));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));

        add(tabbedPane, BorderLayout.CENTER);
    }

    // private void resizeButton(JToggleButton button) {
    //     button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
    // }

    private JPanel createShsPanel(JLabel usernameDisplay, JLabel locationDisplay){
        JPanel shsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        JPanel newPanel = createNewUserPanel();
        shsPanel.add(newPanel, gbc);
        JPanel deletePanel = createDeletePanel();
        shsPanel.add(deletePanel, gbc);
        JPanel editPanel = createEditPanel(usernameDisplay, locationDisplay);
        shsPanel.add(editPanel, gbc);

        return shsPanel;
    }

    private JPanel createEditPanel(JLabel usernameDisplay, JLabel locationDisplay){
        JPanel editPanel = new JPanel(new GridBagLayout());
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit User Permissions"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);//Add paddings

        JLabel usernameLabel = new JLabel ("Username: ");
        usernameField = new JTextField(20);
          usernameField.setText(shs.activeUser.getName());


        //Permission checkboxes
        windowsCheckBox = new JCheckBox("Open/Close Windows");
            windowsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.WINDOW) ? true : false);
        doorsCheckBox = new JCheckBox("Open/Close Doors");
            doorsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.DOOR) ? true : false);
        lightsCheckBox = new JCheckBox("Turn on/off the lights");
            lightsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.LIGHT) ? true : false);
        temperatureCheckBox = new JCheckBox("Change House Temperature");
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
                ArrayList<String> text = new ArrayList<>();
                text.add("Target: User Permission and Username");
                text.add("Event type: Change");
                text.add("Event Description: Change User Permission and Username");
                String newUsername = usernameField.getText();
                usernameDisplay.setText(shs.activeUser.getClass().getSimpleName() + " " + newUsername);
                text.add("Current Username: "  + newUsername);
                text.add("New Permission: ");
                //Check if WINDOW permission is selected
                if (windowsCheckBox.isSelected()) {
                    if(!shs.activeUser.getPermissions().contains(Permissions.WINDOW)){
                        tempPermissions.add(Permissions.WINDOW);
                        text.add(Permissions.WINDOW.name());
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
                        text.add(Permissions.LIGHT.name());
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
                        text.add(Permissions.DOOR.name());
                    }
                } else {
                    if(shs.activeUser.getPermissions().contains(Permissions.DOOR)){
                        tempPermissions.remove(Permissions.DOOR);
                    }                }

                //Check if TEMPERATURE permission is selected
                if (temperatureCheckBox.isSelected()) {
                    if(!shs.activeUser.getPermissions().contains(Permissions.TEMP)) {
                        tempPermissions.add(Permissions.TEMP);
                        text.add(Permissions.TEMP.name());
                    }
                } else {
                    if(shs.activeUser.getPermissions().contains(Permissions.TEMP)){
                        tempPermissions.remove(Permissions.TEMP);
                    }
                }

                shs.activeUser.setName(newUsername);
                shs.activeUser.setPermissions(tempPermissions);
                outpanel.appendText(text);
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

    private JPanel createDeletePanel(){
        JPanel deletePanel = new JPanel(new GridBagLayout());
        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete User Profile"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);//Add paddings
        
        JButton deleteButton = new JButton ("Delete");
        JButton updateButton = new JButton ("Update");
        JLabel profileLabel = new JLabel ("Choose user profile to delete");

        JMenuBar profileBar = new JMenuBar();
        JMenu profileMenu = new JMenu("Profile");
        profileMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (User user: listOfUsers)
        {
            JMenuItem profileItem = new JMenuItem(user.getClass().getSimpleName() + " " + user.getName());
            profileItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    profileMenu.setText(((JMenuItem) e.getSource()).getText());
                }
            });
            profileMenu.add(profileItem);
        }
        profileBar.add(profileMenu);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        deletePanel.add(profileLabel, gbc);

        gbc.gridx = 1;
        deletePanel.add(profileBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        deletePanel.add(deleteButton, gbc);

        //This button updates the list of users
        gbc.gridx = 1;
        deletePanel.add(updateButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                Iterator<User> iterator = listOfUsers.iterator();
                while (iterator.hasNext()){
                    User user = iterator.next();
                    if (profileMenu.getText().equals(user.getClass().getSimpleName() + " " + user.getName())){
                        ArrayList<String> text = new ArrayList<>();
                        text.add("Target: User");
                        text.add("Event type: Delete");
                        text.add("Event Description: Delete User from User List");
                        text.add("Deleted user: " + user.getClass().getSimpleName() + " " + user.getName() + " from " + user.getClass().getSimpleName());
                        outpanel.appendText(text);
                        user.exitRoom();
                        iterator.remove();
                    }
                }
                for (User user: listOfUsers){
                    System.out.println(user.toString());
                }
                profileMenu.removeAll();
                profileMenu.setText("Profile");
                for (User user: listOfUsers)
                {
                    JMenuItem profileItem = new JMenuItem(user.getClass().getSimpleName() + " " + user.getName());
                    profileItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            profileMenu.setText(((JMenuItem) e.getSource()).getText());
                        }
                    });
                    profileMenu.add(profileItem);
                }
                profileBar.revalidate();
                profileBar.repaint();
                
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                profileMenu.removeAll();
                profileMenu.setText("Profile");
                ArrayList<String> text = new ArrayList<>();
                text.add("Target: SHS GUI");
                text.add("Event type: Update");
                text.add("Event Description: Update List of Useres");
                text.add("List of users: ");
                for (User user: listOfUsers)
                {
                    JMenuItem profileItem = new JMenuItem(user.getClass().getSimpleName() + " " + user.getName());
                    text.add(user.getClass().getSimpleName() + " " + user.getName() + " in " + user.getRoom().getClass().getSimpleName());
                    profileItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            profileMenu.setText(((JMenuItem) e.getSource()).getText());
                        }
                    });
                    profileMenu.add(profileItem);
                }
                outpanel.appendText(text);
                profileBar.revalidate();
                profileBar.repaint();
            }
        });

        return deletePanel;
    }

    private void resizePanel(JTextField textfield){
        textfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, textfield.getPreferredSize().height));
    }

    private JPanel createNewUserPanel()
    {
        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.setBorder(BorderFactory.createTitledBorder("Create New User Profile"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);//Add paddings

        JLabel usernameLabel = new JLabel ("Username: ");
        JLabel locationLabel = new JLabel ("Location: ");
        JLabel usertypeLabel = new JLabel ("User Type: ");
        
        JTextField usernameField = new JTextField (20);

        JMenuBar locationBar = new JMenuBar();
        JMenu locationMenu = new JMenu("Location");
        locationMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (Room room: house.getRooms())
        {
            JMenuItem locationItem = new JMenuItem(room.getName());
            locationItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    locationMenu.setText(((JMenuItem) e.getSource()).getText());
                }
            });
            locationMenu.add(locationItem);
        }
        locationBar.add(locationMenu);

        JMenuBar usertypeBar = new JMenuBar();
        JMenu usertypeMenu = new JMenu("Usertype");
        usertypeMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        List<String> usertypes = Arrays.asList("Admin", "Child", "FamilyMember", "Guest", "Parent", "Stranger");
        for (String types: usertypes)
        {
            JMenuItem usertypeItem = new JMenuItem(types);
            usertypeItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    usertypeMenu.setText(((JMenuItem) e.getSource()).getText());
                }
            });
            usertypeMenu.add(usertypeItem);
        }
        usertypeBar.add(usertypeMenu);
        
        JButton submitButton = new JButton ("Create");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        newPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        newPanel.add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        newPanel.add(locationLabel, gbc);
        
        gbc.gridx = 1;
        newPanel.add(locationBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        newPanel.add(usertypeLabel, gbc);
        
        gbc.gridx = 1;
        newPanel.add(usertypeBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        newPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                String newUsername = usernameField.getText().trim();
                String newUserType = usertypeMenu.getText().trim();
                Room newRoom = shs.getRoomByName(locationMenu.getText());
                User newUser;
                switch (newUserType){
                    case "Admin":
                        newUser = new Admin(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "Child": 
                        newUser = new Child(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "FamilyMember":
                        newUser = new FamilyMember(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "Guest":
                        newUser = new Guest(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "Stranger":
                        newUser = new Stranger(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "Parent":
                        newUser = new Parent(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Create user failed. Please try again.");
                }
                ArrayList<String> text = new ArrayList<>();
                text.add("Target: List of User");
                text.add("Event type: Add");
                text.add("Event Description: Add New User Profile");
                text.add("Added New User: " + newUserType + " " + newUsername + " in " + newRoom.getClass().getSimpleName());
                outpanel.appendText(text);
            }
        });

        return newPanel;
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
                ArrayList<String> text = new ArrayList<>();
                text.add("Target: Date and Time");
                text.add("Event type: Change");
                text.add("Event Description: Change Date and Time");
                text.add("New Date and Time: ");
                outpanel.appendText(text);
                try {
                    // Parse the entered values to integers
                    int year = Integer.parseInt(dateEditField1.getText());
                    int month = Integer.parseInt(dateEditField2.getText());
                    int day = Integer.parseInt(dateEditField3.getText());
                    text.add("Year: " + Integer.toString(year));
                    text.add("Month: " + Integer.toString(month));
                    text.add("Day: " + Integer.toString(day));

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
                        text.add("Hour: " + Integer.toString(hour));
                        text.add("Minute: " + Integer.toString(minute));
                        text.add("Second: " + Integer.toString(second));
    
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

    @Override
    public void update(Observable o) {
        SwingUtilities.invokeLater(() -> {
            User activeUser = SHS.getInstance().getActiveUser();
            usernameField.setText(activeUser.toString());
            windowsCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.WINDOW));
            doorsCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.DOOR));
            lightsCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.LIGHT));
            temperatureCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.TEMP));
        });
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
