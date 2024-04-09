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
import main.java.logic.modules.SHC;
import main.java.logic.modules.SHP;
import main.java.logic.modules.SHS;
import main.java.model.rooms.Room;

public class SHPPanel extends JPanel {
    private JToggleButton simulationToggle;
    private JLabel titleLabel;
    House house = House.getInstance();
    private ArrayList<JCheckBox> roomCheckBoxes;
    private JLabel timerLabel;
    private JTextField timerField;
    
    private SHC shc = SHC.getIntance();
    private SHP shp = SHP.getInstance(shc);
    

    public SHPPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        roomCheckBoxes =  new ArrayList<>();

        JPanel awayMode = createAwayModePanel();
        JPanel setTimerForPolice = createSetTimerForPolicePanel();

        add(awayMode, gbc);
        add(setTimerForPolice, gbc);
    }

    private JPanel createAwayModePanel(){
        JPanel awayMode = new JPanel();
        JLabel pleaseMsg = new JLabel("Please set Detector Monitors in rooms BEFORE turning on the AWAY MODE");
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
                shp.setIsAway(true);
                simulationToggle.setBackground(Color.GREEN);
            } else {
                simulationToggle.setText("OFF");
                shp.setIsAway(false);
                simulationToggle.setBackground(Color.RED);
            }
        });
        for (Room room : house.getRooms()) {
            JCheckBox roomCheckBox = new JCheckBox(room.getName());
            roomCheckBox.addItemListener(e -> {
                if (roomCheckBox.isSelected()) {
                    shp.addMotionDetector(room);
                    System.out.println("Room: " + room.getName() + " has motion detector: " + room.getMotionDetector());
                } else {
                    shp.removeMotionDetector(room);
                    System.out.println("Room: " + room.getName() + " has motion detector: " + room.getMotionDetector());
                }

            });
            awayMode.add(roomCheckBox);
            roomCheckBoxes.add(roomCheckBox);
        }

        //awayMode.add(titleLabel);
        awayMode.add(simulationToggle);
        awayMode.add(pleaseMsg);
        return awayMode;
    }


//    private JPanel createMotionDetectorsPlacementPanel() {
//        JPanel motionDetectorsPlacementPanel = new JPanel();
//        motionDetectorsPlacementPanel.setLayout(new BoxLayout(motionDetectorsPlacementPanel, BoxLayout.Y_AXIS));
//        motionDetectorsPlacementPanel.setBorder(BorderFactory.createTitledBorder("Motion Detectors Locations"));
//
//        JPanel motionDetectorsPanel = new JPanel();
//        int rows = (int) Math.ceil(house.getRooms().size() / 4.0); // Calculate the number of rows needed for two columns
//        motionDetectorsPanel.setLayout(new GridLayout(rows, 4, 15, 15)); // Set the layout with px horizontal and vertical gaps
//
//
//
//
//
//        motionDetectorsPlacementPanel.add(motionDetectorsPanel);
//
//
//
//        return motionDetectorsPlacementPanel;
//    }

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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int time = Integer.parseInt(timerField.getText());
                //shp.setTimerForPolice(time);
            }
        });
        submitPanel.add(submitButton);

        setTimerPanel.add(timerPanel);
        setTimerPanel.add(submitPanel);

        return setTimerPanel;
    }
}
