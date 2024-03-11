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
        add(houseLayoutPanel, BorderLayout.EAST);
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));
        houseLayoutPanel.addRoom(new Kitchen("Kitchen"));

        // module Panel
        ModulePanel ModulePanel = new ModulePanel();
        add(ModulePanel, BorderLayout.CENTER);

        // Output Console
        OutputPanel outputPanel = new OutputPanel();
        add(outputPanel, BorderLayout.SOUTH);
        
    }
}
