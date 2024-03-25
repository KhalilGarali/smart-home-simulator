package main.java.gui.ModulePanelTabs;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import main.java.logic.layout.House;
import main.java.model.rooms.Room;

import java.awt.*;
import java.awt.event.ActionListener;

public class SHHPanel extends JPanel {
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

    public SHHPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Zones Panel
        JPanel zonesPanel = createZonesPanel();

        // Rooms Panel
        JPanel roomsPanel = createRoomsPanel();
 
        // Add the items and open/close panels to the SHC panel
        add(zonesPanel, gbc);
        add(roomsPanel, gbc);

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
            if (selectedToggle != null) {
                String temperature = temperatureField.getText();
                // Process the temperature setting for the selected zone
                // For example:
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
                // room.setDesiredTemperature(newTemperature);
                System.out.println("Setting temperature for " + room.getName() + " to " + newTemperature + "°C (OVERRIDDEN)");
                dialog.dispose(); // Close the dialog after setting the temperature
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

}
