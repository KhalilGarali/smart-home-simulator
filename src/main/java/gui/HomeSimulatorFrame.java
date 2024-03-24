package main.java.gui;

import javax.swing.*;
import main.java.model.rooms.Kitchen;
import java.awt.*;


public class HomeSimulatorFrame extends JFrame {

    public HomeSimulatorFrame() {
        setTitle("Smart Home Simulator");
        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Simulation Panel
        SimulationPanel simulationPanel = new SimulationPanel();
        add(simulationPanel, BorderLayout.WEST);

        // House Layout Panel 
        HouseLayoutPanel houseLayoutPanel = new HouseLayoutPanel();
        JScrollPane scrolableHouseLayoutPanel = new JScrollPane(houseLayoutPanel);
        scrolableHouseLayoutPanel.setBorder(null);
        scrolableHouseLayoutPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrolableHouseLayoutPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrolableHouseLayoutPanel, BorderLayout.EAST);

        // module Panel
        ModulePanel ModulePanel = new ModulePanel(simulationPanel.getUserLabel(), simulationPanel.getLocationLabel());
        add(ModulePanel, BorderLayout.CENTER);

        // Output Console
        OutputPanel outputPanel = OutputPanel.getInstance();
        add(outputPanel, BorderLayout.SOUTH);
        
    }
}
