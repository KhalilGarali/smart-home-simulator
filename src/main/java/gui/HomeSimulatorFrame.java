package main.java.gui;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import java.awt.*;

public class HomeSimulatorFrame extends JFrame {

    private JToggleButton simulationToggle;
    private JLabel userIcon;
    private JLabel locationLabel, timeSpeedLabel, simulationLabel;
    private JLabel outsideTempLabel;
    private JLabel timeLabel, dateLabel;
    private JSlider timeSpeedSlider;
    private JTabbedPane tabbedPane;
    private JTextArea outputConsole;
    private JPanel houseViewPanel;

    public HomeSimulatorFrame() {
        setTitle("Smart Home Simulator");
        setSize(1600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Simulation Panel
        JPanel simulationPanel = createSimulationPanel();
        add(simulationPanel, BorderLayout.WEST);

        // module Panel
        JPanel ModulePanel = createModulePanel();
        add(ModulePanel, BorderLayout.CENTER);


        // House Layout Panel 
        JPanel HousePanel = createHousePanel();
        add(HousePanel, BorderLayout.EAST);


        // Output Console
        outputConsole = new JTextArea();
        outputConsole.setBorder(BorderFactory.createTitledBorder("Output Console"));
        add(new JScrollPane(outputConsole), BorderLayout.SOUTH);
    }

    private JPanel createSimulationPanel() {
        JPanel simulationPanel = new JPanel();
        simulationPanel.setLayout(new BoxLayout(simulationPanel, BoxLayout.Y_AXIS));
        simulationPanel.setBorder(BorderFactory.createTitledBorder("Simulation"));

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
            if (simulationToggle.isSelected()) {
                simulationToggle.setText("ON");
                simulationToggle.setBackground(Color.GREEN);
            } else {
                simulationToggle.setText("OFF");
                simulationToggle.setBackground(Color.RED);
            }
        });

        // Simulation Label
        simulationLabel = new JLabel("Simulation", SwingConstants.CENTER);

        simulationPanel.add(simulationLabel);
        simulationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        simulationPanel.add(simulationToggle);

        // User and Location
        locationLabel = new JLabel("Parent - Kitchen");
        userIcon = new JLabel(new ImageIcon("src\\main\\resources\\pdp.jpg"));
        simulationPanel.add(userIcon);
        simulationPanel.add(locationLabel);

        // Outside Temperature
        outsideTempLabel = new JLabel("Outside Temp: 15°C");
        simulationPanel.add(outsideTempLabel);

        // Date and Time
        dateLabel = new JLabel("Wed Oct 21 2020 19:30:55");
        timeLabel = new JLabel("19:30:55");
        simulationPanel.add(dateLabel);
        simulationPanel.add(timeLabel);

        // Time Speed Slider
        timeSpeedLabel = new JLabel("Time Speed");
        timeSpeedSlider = new JSlider();
        simulationPanel.add(timeSpeedLabel);
        simulationPanel.add(timeSpeedSlider);

        return simulationPanel;
    }

    private JPanel createModulePanel(){
        JPanel modulePanel = new JPanel();
        modulePanel.setLayout(new BoxLayout(modulePanel, BoxLayout.Y_AXIS));
        modulePanel.setBorder(BorderFactory.createTitledBorder("Modules"));

        // Tabbed Pane for Items
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("SHC", new JLabel("SHC Content"));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));
        // ... Add other tabs
        modulePanel.add(tabbedPane);
        
        return modulePanel;
    }

    private JPanel createHousePanel(){
        JPanel housePanel = new JPanel();
        housePanel.setLayout(new BoxLayout(housePanel, BoxLayout.Y_AXIS));
        housePanel.setBorder(BorderFactory.createTitledBorder("House Layout"));   
        houseViewPanel = new JPanel();
        houseViewPanel.setBorder(BorderFactory.createTitledBorder("House View"));
        
         // House View Panel with a rectangle drawn
         houseViewPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(10, 10, getWidth() - 20, getHeight() - 20);  // Drawing a rectangle
            }
        };
        houseViewPanel.setBorder(BorderFactory.createTitledBorder("House View"));
        
        housePanel.add(houseViewPanel);  // Adding to housePanel with BorderLayout (no need for EAST here)

        return housePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeSimulatorFrame frame = new HomeSimulatorFrame();
            frame.setVisible(true);
        });
    }
}
