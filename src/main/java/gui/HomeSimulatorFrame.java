package main.java.gui;

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import java.awt.*;

public class HomeSimulatorFrame extends JFrame {

    private JToggleButton simulationToggle;
    private JLabel locationLabel, timeSpeedLabel, userLabel, userIcon, outsideTempLabel, timeLabel, dateLabel;
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

        // House Layout Panel 
        JPanel HousePanel = createHousePanel();
        add(HousePanel, BorderLayout.EAST);

        // module Panel
        JPanel ModulePanel = createModulePanel();
        add(ModulePanel, BorderLayout.CENTER);

        // Output Console
        JPanel outputPanel = createOutputPanel();
        add(outputPanel, BorderLayout.SOUTH);
        
    }

    private JPanel createSimulationPanel() {
        JPanel simulationPanel = new JPanel();
        simulationPanel.setLayout(new BoxLayout(simulationPanel, BoxLayout.Y_AXIS));
        simulationPanel.setBorder(BorderFactory.createTitledBorder("Simulation"));

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

        // Edit Parameters Button
        JButton editButton = new JButton();
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editButton.setMaximumSize(new Dimension(40, 40)); // Similar to the toggle, ensures the button size
        // Set the icon of the button
        editButton.setIcon(new ImageIcon("src\\main\\resources\\editIcon.png"));
        // Add an action listener for the edit button
        // editButton.addActionListener(e -> {
        // // Open a dialog or another frame to edit simulation parameters
        // editSimulationParameters();
        // });

        // User and Location
        userLabel =  new JLabel("User: Parent");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationLabel = new JLabel("Location: Kitchen");
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userIcon = new JLabel(new ImageIcon("src\\main\\resources\\profilePicture.png"));
        userIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Outside Temperature
        outsideTempLabel = new JLabel("Outside Temp: 15°C");
        outsideTempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Date and Time
        dateLabel = new JLabel("Date: Wed Oct 21 2023");
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabel = new JLabel("Time: 19:30:55");
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Time Speed Slider
        timeSpeedLabel = new JLabel("Time Speed");
        timeSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeSpeedSlider = new JSlider();
        timeSpeedSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Organizing The Panel
        simulationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        simulationPanel.add(simulationToggle);
        simulationPanel.add(Box.createVerticalStrut(30)); 
        simulationPanel.add(editButton);
        simulationPanel.add(Box.createVerticalStrut(20));
        simulationPanel.add(userIcon);
        simulationPanel.add(Box.createVerticalStrut(20));
        simulationPanel.add(userLabel);
        simulationPanel.add(Box.createVerticalStrut(10));
        simulationPanel.add(locationLabel);
        simulationPanel.add(Box.createVerticalStrut(10));
        simulationPanel.add(outsideTempLabel);
        simulationPanel.add(Box.createVerticalStrut(10));
        simulationPanel.add(dateLabel);
        simulationPanel.add(Box.createVerticalStrut(10));
        simulationPanel.add(timeLabel);
        simulationPanel.add(Box.createVerticalStrut(30));
        simulationPanel.add(timeSpeedLabel);
        simulationPanel.add(Box.createVerticalStrut(10));
        simulationPanel.add(timeSpeedSlider);

        return simulationPanel;
    }

    private JPanel createModulePanel(){
        JPanel modulePanel = new JPanel(new BorderLayout());
        // modulePanel.setLayout(new BoxLayout(modulePanel, BoxLayout.Y_AXIS));
        modulePanel.setBorder(BorderFactory.createTitledBorder("Modules"));

        // Tabbed Pane for Items
        tabbedPane = new JTabbedPane();

         // SHC Panel
        JPanel shcPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Items Panel
        JPanel itemsPanel = new JPanel();
        // itemsPanel.setPreferredSize(new Dimension(300, 100));
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Items"));

        JToggleButton windowsButton = new JToggleButton("Windows");
        JToggleButton lightsButton = new JToggleButton("Lights");
        JToggleButton doorsButton = new JToggleButton("Doors");

        ButtonGroup itemsGroup = new ButtonGroup();
        itemsGroup.add(windowsButton);
        itemsGroup.add(lightsButton);
        itemsGroup.add(doorsButton);

        // Set the buttons to fill the horizontal space
        windowsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, windowsButton.getMinimumSize().height));
        lightsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, lightsButton.getMinimumSize().height));
        doorsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, doorsButton.getMinimumSize().height));

        itemsPanel.add(windowsButton);
        itemsPanel.add(lightsButton);
        itemsPanel.add(doorsButton);

        // Open/Close Panel
        JPanel openClosePanel = new JPanel();
        // openClosePanel.setPreferredSize(new Dimension(300, 300));
        openClosePanel.setLayout(new BoxLayout(openClosePanel, BoxLayout.Y_AXIS));
        openClosePanel.setBorder(BorderFactory.createTitledBorder("Open/Close"));
        openClosePanel.add(new JCheckBox("Garage"));
        openClosePanel.add(new JCheckBox("Living Room"));
        openClosePanel.add(new JCheckBox("Backyard"));


        // Add the items and open/close panels to the SHC panel
        shcPanel.add(itemsPanel, gbc);
        shcPanel.add(openClosePanel, gbc);

        tabbedPane.addTab("SHC", new JScrollPane(shcPanel));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));
        // ... Add other tabs
        modulePanel.add(tabbedPane, BorderLayout.CENTER);
        
        return modulePanel;
    }

    private JPanel createHousePanel(){
        JPanel housePanel = new JPanel(new BorderLayout());
        housePanel.setBorder(BorderFactory.createTitledBorder("House Layout"));
    
        // House View Panel with an image
        houseViewPanel = new JPanel() {
        private Image houseImage = new ImageIcon("src\\main\\resources\\pdp.jpg").getImage();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the image on the panel
            g.drawImage(houseImage, 0, 0, this.getWidth(), this.getHeight(), this); // Scales image to panel size
        }
        };
        houseViewPanel.setPreferredSize(new Dimension(600, houseViewPanel.getPreferredSize().height));
        housePanel.add(houseViewPanel); 

        return housePanel;
    }

    private JPanel createOutputPanel(){
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.setBorder(BorderFactory.createTitledBorder("Console Output"));
        outputConsole = new JTextArea();
        outputConsole.setLineWrap(true);
        outputConsole.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputConsole);
        scrollPane.setPreferredSize(new Dimension(outputPanel.getWidth(), 100)); // Set the preferred height to 200 pixels
        outputPanel.add(scrollPane);

        return outputPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeSimulatorFrame frame = new HomeSimulatorFrame();
            frame.setVisible(true);
        });
    }
}
