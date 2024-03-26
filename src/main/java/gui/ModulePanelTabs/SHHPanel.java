package main.java.gui.ModulePanelTabs;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import main.java.logic.commands.Command;
import main.java.logic.layout.House;
import main.java.logic.modules.SHC;
import main.java.logic.modules.SHH;
import main.java.logic.modules.SHS;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.logic.users.Parent;
import main.java.logic.users.Permissions;
import main.java.logic.users.Stranger;
import main.java.logic.users.User;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;

import java.awt.*;
import java.awt.event.ActionListener;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import static main.java.logic.modules.SHS.shs;

public class SHHPanel extends JPanel implements Observer {
    House house = House.getInstance();
    private JToggleButton zoneAButton, zoneBButton, room;
    private String selectedToggle; // This will store the command of the last toggled button
    private JLabel temperatureLabel;
    private JTextField temperatureField;
    private JButton submitButton;
    JPanel buttonPanel;
    private JCheckBox shhToggle;
    private JComboBox<String> zoneSelector;
    private JComboBox<String> roomSelector;
    JLabel userNameLabel;
    JLabel userLocationLabel;
    JCheckBox windowsCheckBox, doorsCheckBox, lightsCheckBox, temperatureCheckBox;

    Command aCommand;

    SHC shc = SHC.getIntance();
    private SHH shh = SHH.getInstance(shc);

    public SHHPanel() {
        shs.addObserver(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        //Active User Panel
        JPanel userPanel = createUserPanel();

        // Zones Panel
        JPanel zonesPanel = createZonesPanel();

        // Rooms Panel
        JPanel roomsPanel = createRoomsPanel();
 
        // Add the items and open/close panels to the SHC panel
        add(userPanel, gbc);
        add(zonesPanel, gbc);
        add(roomsPanel, gbc);

    }

    private JPanel createUserPanel() {

        //User activeUser = new Parent("John Doe", new Kitchen("kitchen Room"));

     //   activeUser.setPermissions(List.of(Permissions.LIGHT, Permissions.WINDOW, Permissions.TEMP));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBorder(BorderFactory.createTitledBorder("Profile Permissions"));

        //TODO:Update this to automatically display new shs.activeUser (I have to reactive user in simulation panel to see changes from Edit User)

         userNameLabel = new JLabel("Active User:   " + shs.activeUser.toString());

        // Create userLocationLabel with bold font
         userLocationLabel = new JLabel("\nLocation:       " + shs.activeUser.getRoom().getName());

        JLabel permissionsLabel = new JLabel("\nPermissions (Read-Only):\n");

        //Permission checkboxes
        windowsCheckBox = new JCheckBox("Open/Close Windows");
            windowsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.WINDOW));
        doorsCheckBox = new JCheckBox("Open/Close Doors");
            doorsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.DOOR));
        lightsCheckBox = new JCheckBox("Turn on/off the lights");
            lightsCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.LIGHT));
        temperatureCheckBox = new JCheckBox("Change House Temperature");
            temperatureCheckBox.setSelected(shs.activeUser.getPermissions().contains(Permissions.TEMP));

        windowsCheckBox.getModel().setEnabled(false);
        doorsCheckBox.getModel().setEnabled(false);
        lightsCheckBox.getModel().setEnabled(false);
        temperatureCheckBox.getModel().setEnabled(false);

        userPanel.add(userNameLabel);
        userPanel.add(userLocationLabel);
        userPanel.add(windowsCheckBox);
        userPanel.add(doorsCheckBox);
        userPanel.add(lightsCheckBox);
        userPanel.add(temperatureCheckBox);



        return userPanel;
    }

    private JPanel createZonesPanel() {
        JPanel zonesPanel = new JPanel();
        zonesPanel.setLayout(new BoxLayout(zonesPanel, BoxLayout.Y_AXIS));
        zonesPanel.setBorder(BorderFactory.createTitledBorder("Zones Temperature"));

        // Panel for Zone buttons
        JPanel zoneButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        zoneAButton = new JToggleButton("Zone A");
        zoneBButton = new JToggleButton("Zone B");

        ButtonGroup zonesGroup = new ButtonGroup();
        zonesGroup.add(zoneAButton);
        zonesGroup.add(zoneBButton);

        zoneButtonsPanel.add(zoneAButton);
        zoneButtonsPanel.add(zoneBButton);


        // Panel for temperature setting
        JPanel temperaturePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        temperatureLabel = new JLabel("Set Temperature:");
        temperatureField = new JTextField(5); // Adjust the size if needed
        temperaturePanel.add(temperatureLabel);
        temperaturePanel.add(temperatureField);


        // Panel for the submit button
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Submit");
        submitPanel.add(submitButton);

        zonesPanel.add(zoneButtonsPanel);
        zonesPanel.add(temperaturePanel);
        zonesPanel.add(submitPanel);

        ActionListener toggleListener = e -> {
            JToggleButton button = (JToggleButton) e.getSource();
            if (button.isSelected()) {
                if (selectedToggle != null && selectedToggle.equals(button.getActionCommand())) {
                    // The button was already selected, so untoggle it
                    button.setSelected(false);
                    selectedToggle = null;
                } else {
                    // A different button was selected
                    // resetCheckedBoxes();
                    selectedToggle = button.getActionCommand();
                }
            } else {
                // If the user deselects the button, clear the selection.
                selectedToggle = null;
            }
        };
    
        // Add the same listener to all toggle buttons
        zoneAButton.addActionListener(toggleListener);
        zoneBButton.addActionListener(toggleListener);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {

            if(!(shs.activeUser instanceof Parent)){
                JOptionPane.showMessageDialog(zonesPanel, "You do not have permission to change ZONE Temperatures.");
            }

            else if (selectedToggle != null) {
                String temperature = temperatureField.getText();
                Room kitchen = new Kitchen("kitchen Room");
                //TODO:Actually change temperature for all rooms in TOGGLED ZONE (gotta figure out how to store rooms in Zones)
//                aCommand = shs.cf.createCommand("ChangeTemperature", kitchen, 1);
//                shc.userAction(shs.activeUser, aCommand, kitchen);

                System.out.println("Setting temperature for " + selectedToggle + " to " + temperature + "°C");
            } else {
                JOptionPane.showMessageDialog(zonesPanel, "Please select a zone first.");
            }
        });

        return zonesPanel;
    }

    private JPanel createRoomsPanel() {
        JPanel roomsPanel = new JPanel();
        roomsPanel.setBorder(BorderFactory.createTitledBorder("Rooms Temperature"));
        int rows = (int) Math.ceil(house.getRooms().size() / 2.0); // Calculate the number of rows needed for two columns
        roomsPanel.setLayout(new GridLayout(rows, 2, 15, 15)); // Set the layout with px horizontal and vertical gaps
    
        for (Room room : house.getRooms()) {
            JToggleButton roomButton = new JToggleButton(room.getName());
            roomButton.addActionListener(e -> showRoomDialog(room));
            roomsPanel.add(roomButton);
        }
        return roomsPanel;
    }

    private void showRoomDialog(Room room) {
        // The current temperature and zone would be retrieved from the 'room' object
        String currentTemp = String.valueOf(room.getCurrentTemperature());
        String zone = "A"; // Assuming Room has a method getZoneName() or similar
    
        // Create and set up the dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(room.getName() + " Settings");
        dialog.setModal(true);
    
        // Components inside the dialog
        JLabel currentTempLabel = new JLabel("Current Temperature: " + currentTemp + "°C");
        JLabel zoneLabel = new JLabel("Zone: " + zone);
        JTextField setTempField = new JTextField(5); // Input field for set temperature
        JButton setTempButton = new JButton("Set Temperature");
    
        // Action listener for set temperature button
        setTempButton.addActionListener(e -> {
            // Get the temperature from the text field and set it for the room
            int newTemperature;
            try {
                newTemperature = Integer.parseInt(setTempField.getText());
                // Here you would call a method on 'room' to set the new temperature:

                if (shs.activeUser.getPermissions().contains(Permissions.TEMP)) {
                    if ((!(shs.activeUser instanceof Parent) && !(shs.activeUser.getRoom().equals(room)))
                            || shs.activeUser instanceof Stranger) {
                        JOptionPane.showMessageDialog(dialog, "You do not have permission to change " + room.getName() + " temperature.");
                    } else {
                        room.setDesiredTemperature(newTemperature);
                        System.out.println("Setting temperature for " + room.getName() + " to " + newTemperature + "°C (OVERRIDDEN)");
                        dialog.dispose(); // Close the dialog after setting the temperature
                        JOptionPane.showMessageDialog(dialog, room.getName() + " is being set to " + newTemperature + "°C");
                        System.out.println(room.getDesiredTemp());
                    }
                }
                else JOptionPane.showMessageDialog(dialog, "You do not have permission to change any room temperatures.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid integer for the temperature.");
            }
        });
    
        // Layout for the dialog
        JPanel panel = new JPanel(new GridLayout(0, 1, 15, 15));
        panel.add(currentTempLabel);
        panel.add(zoneLabel);
        panel.add(setTempField);
        panel.add(setTempButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
    
        dialog.getContentPane().add(panel); // Add panel to dialog
    
        // Set the size of the dialog
        dialog.setPreferredSize(new Dimension(300, 200)); // You can adjust the size here as needed
        dialog.pack();
        // Set the location of the dialog to be centered on screen
        dialog.setLocationRelativeTo(null);
        // Show the dialog
        dialog.setVisible(true);
    }

    @Override
    public void update(Observable o) {
        SwingUtilities.invokeLater(() -> {
            User activeUser = SHS.getInstance().getActiveUser();
            System.out.println(shs.activeUser.toString() + activeUser.getPermissions());
            userNameLabel.setText("User:       "+activeUser.getName() + " is a " + activeUser.getClass().getSimpleName());
            userLocationLabel.setText("Location:   "+activeUser.getRoom().getName());
            windowsCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.WINDOW));
            doorsCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.DOOR));
            lightsCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.LIGHT));
            temperatureCheckBox.setSelected(activeUser.getPermissions().contains(Permissions.TEMP));

        });
    }
}
