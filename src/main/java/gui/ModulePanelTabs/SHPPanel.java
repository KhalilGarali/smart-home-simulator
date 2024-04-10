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

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import main.java.logic.layout.House;
import main.java.logic.modules.SHC;
import main.java.logic.modules.SHP;
import main.java.logic.modules.SHS;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.rooms.Room;

import main.java.logic.users.User;
import main.java.logic.users.Parent;

public class SHPPanel extends JPanel implements Observer {
    private JToggleButton simulationToggle;
    private JLabel titleLabel;
    House house = House.getInstance();
    private ArrayList<JCheckBox> roomCheckBoxes;
    private JLabel timerLabel;
    private JTextField timerField;
    
    private SHC shc = SHC.getIntance();
    private SHP shp = SHP.getInstance(shc);
    private SHS shs = SHS.getInstance();

    private User activeUser;
    

    public SHPPanel() {
        shs.addObserver(this);
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
         if(shs.activeUser instanceof Parent) {
             if (simulationToggle.isSelected()) {
                 simulationToggle.setText("ON");
                 shp.setIsAway(true);
                 simulationToggle.setBackground(Color.GREEN);
             } else {
                 simulationToggle.setText("OFF");
                 shp.setIsAway(false);
                 simulationToggle.setBackground(Color.RED);
             }
         } else {
             JOptionPane.showMessageDialog(null, "Only parents can turn on the AWAY MODE");
             simulationToggle.setSelected(false);
         }
        });
        for (Room room : house.getRooms()) {
            JCheckBox roomCheckBox = new JCheckBox(room.getName());
            roomCheckBox.addItemListener(e -> {
             if(shs.activeUser instanceof Parent) {
                 if (roomCheckBox.isSelected()) {
                     shp.addMotionDetector(room);
                     System.out.println("Room: " + room.getName() + " has motion detector: " + room.getMotionDetector());
                 } else {
                     shp.removeMotionDetector(room);
                     System.out.println("Room: " + room.getName() + " has motion detector: " + room.getMotionDetector());
                 }
             } else {
                 JOptionPane.showMessageDialog(null, "Only parents can set the motion detectors");
                 roomCheckBox.setSelected(false);
             }

            });
            awayMode.add(roomCheckBox);
            roomCheckBoxes.add(roomCheckBox);
        }


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
        timerLabel = new JLabel("Set Timer in SECONDS:");
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
                if(shs.activeUser instanceof Parent)
                    shp.setPoliceTimer(Integer.parseInt(timerField.getText()));
                else
                    JOptionPane.showMessageDialog(null, "Only parents can set this TIMER");

            }
        });
        submitPanel.add(submitButton);

        setTimerPanel.add(timerPanel);
        setTimerPanel.add(submitPanel);

        return setTimerPanel;
    }

    @Override
    public void update(Observable o) {

        SwingUtilities.invokeLater(() -> {
            User activeUser = SHS.getInstance().getActiveUser();
        });
    }
}
