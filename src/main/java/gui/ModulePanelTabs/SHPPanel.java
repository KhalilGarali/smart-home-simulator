package main.java.gui.ModulePanelTabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import main.java.logic.layout.House;
import main.java.model.rooms.Room;

public class SHPPanel extends JPanel {
    private JToggleButton simulationToggle;
    private JLabel titleLabel;
    House house = House.getInstance();
    private ArrayList<JCheckBox> roomCheckBoxes;

    public SHPPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        roomCheckBoxes =  new ArrayList<>();

        JPanel awayMode = createAwayModePanel();
        JPanel motionDetectorsPlacement = createMotionDetectorsPlacementPanel();
        JPanel setTimerForPolice = createSetTimerForPolicePanel();

        add(awayMode, gbc);
        add(motionDetectorsPlacement, gbc);
        add(setTimerForPolice, gbc);
    }

    private JPanel createAwayModePanel(){
        JPanel awayMode = new JPanel();
        awayMode.setLayout(new BoxLayout(awayMode, BoxLayout.Y_AXIS));
        awayMode.setBorder(BorderFactory.createTitledBorder("Away Mode")); 

        // Title Label
        titleLabel = new JLabel("Away mode");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Simulation Toggle
        simulationToggle = new JToggleButton("OFF");
        simulationToggle.setPreferredSize(new Dimension(100, 40));
        simulationToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        simulationToggle.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.GREEN;
            }
        });
        simulationToggle.setBackground(Color.RED);
        simulationToggle.addItemListener(e -> {
            if (simulationToggle.isSelected()) {
                simulationToggle.setText("ON");
                simulationToggle.setBackground(Color.GREEN);
            } else {
                simulationToggle.setText("OFF");
                simulationToggle.setBackground(Color.RED);
            }
        });

        //awayMode.add(titleLabel);
        awayMode.add(simulationToggle);
        return awayMode;
    }


    private JPanel createMotionDetectorsPlacementPanel() {
        JPanel openClosePanel = new JPanel();
        openClosePanel.setLayout(new BoxLayout(openClosePanel, BoxLayout.Y_AXIS));
        openClosePanel.setBorder(BorderFactory.createTitledBorder("Motion Detectors Locations"));  
        for (Room room : house.getRooms()) {
            JCheckBox roomCheckBox = new JCheckBox(room.getName());
            roomCheckBox.addItemListener(e -> {
                
            });
            openClosePanel.add(roomCheckBox);
            roomCheckBoxes.add(roomCheckBox);
        }

        return openClosePanel;
    }

    private JPanel createSetTimerForPolicePanel() {
        JPanel setTimerPanel = new JPanel();
        setTimerPanel.setLayout(new BoxLayout(setTimerPanel, BoxLayout.Y_AXIS));
        setTimerPanel.setBorder(BorderFactory.createTitledBorder("Set Timer For Police"));  
        

        return setTimerPanel;
    }
}
