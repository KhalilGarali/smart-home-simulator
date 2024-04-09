package main.java.gui.ModulePanelTabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.Box;

import main.java.logic.layout.House;
import main.java.model.rooms.Room;

public class SHPPanel extends JPanel {
    private JToggleButton simulationToggle;
    private JLabel titleLabel;
    House house = House.getInstance();
    private ArrayList<JCheckBox> roomCheckBoxes;
    private JLabel timerLabel;
    private JTextField timerField;
    

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
        JPanel motionDetectorsPlacementPanel = new JPanel();
        motionDetectorsPlacementPanel.setLayout(new BoxLayout(motionDetectorsPlacementPanel, BoxLayout.Y_AXIS));
        motionDetectorsPlacementPanel.setBorder(BorderFactory.createTitledBorder("Motion Detectors Locations"));
        
        JPanel motionDetectorsPanel = new JPanel();
        int rows = (int) Math.ceil(house.getRooms().size() / 4.0); // Calculate the number of rows needed for two columns
        motionDetectorsPanel.setLayout(new GridLayout(rows, 4, 15, 15)); // Set the layout with px horizontal and vertical gaps

        for (Room room : house.getRooms()) {
            JCheckBox roomCheckBox = new JCheckBox(room.getName());
            roomCheckBox.addItemListener(e -> {
                
            });
            motionDetectorsPanel.add(roomCheckBox);
            roomCheckBoxes.add(roomCheckBox);
        }

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle submit button click
            }
        });

        submitPanel.add(submitButton);
        motionDetectorsPlacementPanel.add(motionDetectorsPanel);
        motionDetectorsPlacementPanel.add(submitPanel);



        return motionDetectorsPlacementPanel;
    }

    private JPanel createSetTimerForPolicePanel() {
        JPanel setTimerPanel = new JPanel();
        setTimerPanel.setLayout(new BoxLayout(setTimerPanel, BoxLayout.Y_AXIS));
        setTimerPanel.setBorder(BorderFactory.createTitledBorder("Timer For Police"));  
        
        // Panel for temperature setting
        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timerLabel = new JLabel("Set Timer in Minutes:");
        timerField = new JTextField(5);
        timerPanel.add(timerLabel);
        timerPanel.add(timerField);


        // Panel for the submit button
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitPanel.add(submitButton);

        setTimerPanel.add(timerPanel);
        setTimerPanel.add(submitPanel);

        return setTimerPanel;
    }
}
