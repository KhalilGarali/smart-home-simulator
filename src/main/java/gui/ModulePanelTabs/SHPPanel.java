package main.java.gui.ModulePanelTabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    House house = House.getInstance();
    private ArrayList<JCheckBox> roomCheckBoxes;
    private JLabel timerLabel;
    private JTextField timerField;
    
    private SHC shc = SHC.getIntance();
    private SHP shp = SHP.getInstance(shc);
    private SHS shs = SHS.getInstance();   


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
        JLabel warningMessage = new JLabel("Please set Detector Monitors in rooms BEFORE turning on the AWAY MODE");
        awayMode.setLayout(new BoxLayout(awayMode, BoxLayout.Y_AXIS));
        awayMode.setBorder(BorderFactory.createTitledBorder("Away Mode")); 

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

        // Simulation Toggle
        simulationToggle = new JToggleButton("OFF");
        simulationToggle.setPreferredSize(new Dimension(100, 40));
        simulationToggle.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.GREEN;
            }
        });
        simulationToggle.setBackground(Color.RED);
        simulationToggle.addItemListener(e -> {
         if(shs.activeUser instanceof Parent) {
            // Code to add changes to log
            ArrayList<String> text = new ArrayList<>();
            text.add("Target: Away From Home Status");
            text.add("Event type: Change");
            text.add("Event Description: Change away from home status");
            if (simulationToggle.isSelected()) {
                simulationToggle.setText("ON");
                shp.setIsAway(true);
                text.add("Change away from home status to: ON");
                simulationToggle.setBackground(Color.GREEN);
            } else {
                simulationToggle.setText("OFF");
                shp.setIsAway(false);
                text.add("Change away from home status to: OFF");
                simulationToggle.setBackground(Color.RED);
            }
            outpanel.appendText(text);
         } else {
             JOptionPane.showMessageDialog(null, "Only parents can turn on the AWAY MODE");
             simulationToggle.setSelected(false);
         }
        });

        
      
        awayMode.add(Box.createVerticalStrut(20)); // Add 10 pixels of vertical spacing
        awayMode.add(simulationToggle);
        awayMode.add(Box.createVerticalStrut(10)); // Add 10 pixels of vertical spacing
        awayMode.add(warningMessage);
        
        return awayMode;
    }


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
                if(shs.activeUser instanceof Parent){
                    shp.setPoliceTimer(Integer.parseInt(timerField.getText()));
                    // Adding to code to write changes to log
                    ArrayList<String> text = new ArrayList<>();
                    text.add("Target: Police Timer");
                    text.add("Event type: Set");
                    text.add("Event Description: Set Police Timer");
                    text.add("Set current Police Timer field to " + timerField.getText());
                    outpanel.appendText(text);
                }
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
