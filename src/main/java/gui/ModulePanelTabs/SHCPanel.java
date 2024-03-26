package main.java.gui.ModulePanelTabs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import main.java.gui.HouseLayoutPanel;
import main.java.gui.SimulationPanel;
import main.java.logic.commands.Command;
import main.java.logic.commands.CommandFactory;
import main.java.logic.commands.close.CloseADoor;
import main.java.logic.commands.close.CloseAWindow;
import main.java.logic.commands.off.TurnLightOff;
import main.java.logic.commands.on.TurnLightOn;
import main.java.logic.commands.open.OpenADoor;
import main.java.logic.commands.open.OpenAWindow;
import main.java.logic.layout.House;
import main.java.logic.modules.SHC;
import main.java.logic.modules.SHS;
import main.java.logic.users.Parent;
import main.java.logic.users.User;
import main.java.model.rooms.Room;

public class SHCPanel extends JPanel {
    JToggleButton windowsButton, doorsButton, lightsButton;
    HouseLayoutPanel houseLayout;
    House house = House.getInstance();
    private String selectedToggle; // This will store the command of the last toggled button
    private ArrayList<JCheckBox> roomCheckBoxes;
    SHC shc = SHC.getIntance();

    private SHS shs = SHS.getInstance();
    // User activeUser = shs.activeUser;

    Command aCommand;
    

    public SHCPanel() {
        roomCheckBoxes =  new ArrayList<>();
        houseLayout = new HouseLayoutPanel();
        setLayout(new GridBagLayout());
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
        add(itemsPanel, gbc);
        add(openClosePanel, gbc);
    }

    private JPanel createItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Items"));

        // Create and add buttons
        windowsButton = new JToggleButton("Windows");
        lightsButton = new JToggleButton("Lights");
        doorsButton = new JToggleButton("Doors");

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
            // Update based on the new toggle state
            // updateBasedOnToggleState();
            updateCheckBoxesBasedOnButtonToggled();
        };
    
        // Add the same listener to all toggle buttons
        windowsButton.addActionListener(toggleListener);
        lightsButton.addActionListener(toggleListener);
        doorsButton.addActionListener(toggleListener);

        return itemsPanel;
    }

    private void updateCheckBoxesBasedOnButtonToggled() {
        for (JCheckBox checkBox : roomCheckBoxes) {
            Room room = findRoomByName(checkBox.getText());
            if (room != null) {
                if ("Windows".equals(selectedToggle)) {
                    checkBox.setSelected(room.getWindow(1).isOpen());
                } else if ("Lights".equals(selectedToggle)) {
                    checkBox.setSelected(room.getLight().getLight());
                } else if ("Doors".equals(selectedToggle)) {
                    checkBox.setSelected(room.getDoor1().isOpen());
                } else {
                    checkBox.setSelected(false);
                }
            }
        }
    }

    private Room findRoomByName(String roomName) {
        for (Room room : house.getRooms()) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        return null; // Room not found
    }

    private JPanel createOpenClosePanel() {
        JPanel openClosePanel = new JPanel();
        openClosePanel.setLayout(new BoxLayout(openClosePanel, BoxLayout.Y_AXIS));
        openClosePanel.setBorder(BorderFactory.createTitledBorder("Open/Close"));  
        for (Room room : house.getRooms()) {
            JCheckBox roomCheckBox = new JCheckBox(room.getName());
            roomCheckBox.addItemListener(e -> {
                if ("Windows".equals(selectedToggle)) {
                    boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
                    if (isSelected) {
                        aCommand = shs.cf.createCommand("OpenAWindow", room, 1);
                        shc.userAction(shs.activeUser, aCommand, room);
                    } else {
                        aCommand = shs.cf.createCommand("CloseAWindow", room, 1);
                        shc.userAction(shs.activeUser, aCommand, room); // Assuming Room has a method to close the window
                    }
                    houseLayout.updateRoomIcon(); // Update the icon in HouseLayoutPanel
                }
                if ("Lights".equals(selectedToggle)) {
                    boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
                    
                    if (isSelected) {
                        aCommand = shs.cf.createCommand("TurnLightOn", room, 1);
                        shc.userAction(shs.activeUser, aCommand, room); // Assuming Room has a method to open the window
                    } else {
                        aCommand = shs.cf.createCommand("TurnLightOff", room, 1);
                        shc.userAction(shs.activeUser, aCommand, room); // Assuming Room has a method to close the window
                    }
                    houseLayout.updateRoomIcon(); // Update the icon in HouseLayoutPanel
                }
                if ("Doors".equals(selectedToggle)) {
                    boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
                    if (isSelected) {
                        aCommand = shs.cf.createCommand("OpenADoor", room, 1);
                        shc.userAction(shs.activeUser, aCommand, room);
                    } else {
                        aCommand = shs.cf.createCommand("CloseADoor", room, 1);
                        shc.userAction(shs.activeUser, aCommand, room);
                    }
                    houseLayout.updateRoomIcon(); // Update the icon in HouseLayoutPanel
                }
            });
            openClosePanel.add(roomCheckBox);
            roomCheckBoxes.add(roomCheckBox);
        }

        return openClosePanel;
    }

    private void resizeButton(JToggleButton button) {
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
    }
    
}
