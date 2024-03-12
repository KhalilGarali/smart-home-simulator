package main.java.gui.ModulePanelTabs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import main.java.gui.HouseLayoutPanel;
import main.java.model.rooms.Room;

public class SHCPanel extends JPanel {
    HouseLayoutPanel houseLayout;

    public SHCPanel() {
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
        for (Room room : houseLayout.getRooms()) {
            openClosePanel.add(new JCheckBox(room.getName()));
        }

        return openClosePanel;
    }

    private void resizeButton(JToggleButton button) {
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
    }
    
}
