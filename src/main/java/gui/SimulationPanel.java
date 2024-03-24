package main.java.gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.plaf.metal.MetalToggleButtonUI;

import main.java.logic.dashboard.DateTime;
import main.java.logic.dashboard.TimeSpeed;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.awt.event.ActionEvent;

import main.java.model.fixtures.Temperature;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.util.Vector;

import main.java.logic.layout.House;
import main.java.logic.users.*;
import main.java.logic.modules.SHS;
import main.java.model.rooms.*;

public class SimulationPanel extends JPanel {
    private JToggleButton simulationToggle;
    private JLabel locationLabel, timeSpeedLabel, userLabel, outsideTempLabel, dateLabel, timeLabel;
    private JSlider timeSpeedSlider;
    private JLabel userIcon;
    private JButton editButton;
    DateTime currentDateTime = new DateTime();
    private TimeSpeed timeSpeed;
    private House house = House.getInstance();
    private ArrayList<Room> rooms = house.getRooms();
    private JButton editLocationButton;
    private String selection;
    private JButton uploadTempButton;

    // used as a dummy value
    private SHS shs = SHS.getInstance();
    // User activeUser = shs.activeUser;
    // activeUser = new User("Active user");

    public SimulationPanel() {
        // dummy variable
        shs.activeUser = new Stranger("Youssef");
        Room dummyRoom = rooms.get(5);
        shs.activeUser.moveToRoom(dummyRoom);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Simulation"));

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
        editButton = new JButton();
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editButton.setMaximumSize(new Dimension(40, 40)); // Similar to the toggle, ensures the button size
        editButton.setIcon(new ImageIcon("src/main/resources/editIcon.png"));
        // Add an action listener for the edit button
        // editButton.addActionListener(e -> {
        // // Open a dialog or another frame to edit simulation parameters
        // editSimulationParameters();
        // });

        // User and Location
        userLabel = new JLabel(shs.activeUser.toString());
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationLabel = new JLabel("Location: " + shs.activeUser.getRoom().getName());
        editLocationButton = new JButton("Move");
        editLocationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editLocationButton.setMaximumSize(new Dimension(100, 40));
        editLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // list all rooms to the user, and update based on user selection
                handleMoveUser();
            }
        });
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userIcon = new JLabel(new ImageIcon("src/main/resources/profilePicture.png"));
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
        timeSpeedSlider = new JSlider();
        timeSpeed = new TimeSpeed();
        timeSpeedSlider.addChangeListener(e -> {
            int speed = timeSpeedSlider.getValue();
            timeSpeed.setSpeed(speed*10);
        });

        // upload csv file containing weather data
        uploadTempButton = new JButton("Upload Outside Temperature");
        uploadTempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadTempButton.setMaximumSize(new Dimension(250, 40));
        uploadTempButton.addActionListener(e -> openFileChooser());


        timeSpeedLabel = new JLabel("Time Speed");
        timeSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeSpeedSlider.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Organizing The Panel
        addComponents();

        // Initialize date and time labels
        updateDateTimeLabels(); // Update labels with current date and time

        // Set up timer to update date and time labels every second
        javax.swing.Timer timer = new javax.swing.Timer(1000, new UpdateDateTimeListener());
        timer.start();

        // outsideTemperature = new Temperature(11);
        // Temperature.setTemperature(11);

        if (DateTime.getMonth() >= 0 && DateTime.getMonth() < 3) {
            Temperature.setTemperature(0);
        } else if(DateTime.getMonth() >= 3 && DateTime.getMonth() < 5) {
            Temperature.setTemperature(15);
        } else if(DateTime.getMonth() >= 5 && DateTime.getMonth() < 9) {
            Temperature.setTemperature(30);
        } else if(DateTime.getMonth() >= 9 && DateTime.getMonth() < 12) {
            Temperature.setTemperature(20);
        }

        // Set up a timer to update the outside temperature label every second
        Timer temperatureTimer = new Timer();
        temperatureTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateOutsideTempLabel();
            }
        }, 0, 1000); // Update every second
    }

    private void addComponents() {
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(simulationToggle);
        add(Box.createVerticalStrut(30));
        add(editButton);
        add(Box.createVerticalStrut(20));
        add(userIcon);
        add(Box.createVerticalStrut(20));
        add(userLabel);
        add(Box.createVerticalStrut(10));
        add(locationLabel);
        add(Box.createVerticalStrut(10));
        add(outsideTempLabel);
        add(Box.createVerticalStrut(10));
        add(dateLabel);
        add(Box.createVerticalStrut(10));
        add(timeLabel);
        add(Box.createVerticalStrut(30));
        add(timeSpeedLabel);
        add(Box.createVerticalStrut(10));
        add(timeSpeedSlider);
        add(Box.createVerticalStrut(30));
        add(editLocationButton);
        add(Box.createVerticalStrut(30));
        add(uploadTempButton);
    }

    private void openFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select Temperature CSV File");
    fileChooser.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
    fileChooser.addChoosableFileFilter(filter);

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        readTemperatureFile(selectedFile);
        }
    }

    private void readTemperatureFile(File file) {
    try {
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        // Here you would process these lines and update your simulation's temperature settings
        // For simplicity, this example won't go into the details of parsing CSV data
        
        JOptionPane.showMessageDialog(this, "Temperature data uploaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Failed to read the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JLabel getUserLabel()
    {
        return userLabel;
    }

    public JLabel getLocationLabel(){
        return locationLabel;
    }
    // ActionListener class to update date and time labels
    private class UpdateDateTimeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the current time before incrementing
            LocalTime beforeTime = DateTime.getTime();

            // currentDateTime.incrementSecond();
            int increment = timeSpeed.calculateIncrement();
            DateTime.incrementTime(0, 0, increment);

            // Get the current time after incrementing
            LocalTime afterTime = DateTime.getTime();

            // Check if an hour has passed
            if (afterTime.getHour() != beforeTime.getHour()) {
                // If an hour has passed, adjust the temperature display
                adjustTemperatureDisplay();
            }

            updateDateTimeLabels();
        }
    }

    // Method to update date and time labels
    private void updateDateTimeLabels() {
        String currentDate = getCurrentDateAsString();
        String currentTime = getCurrentTimeAsString();

        // Update labels
        dateLabel.setText("Date: " + currentDate);
        timeLabel.setText("Time: " + currentTime);
    }

    // Method to get current date as string
    private String getCurrentDateAsString() {
        LocalDate dateOnly = DateTime.getDate();
        return dateOnly.format(DateTimeFormatter.ofPattern("E MMM dd yyyy"));
    }

    // Method to get current time as string
    private String getCurrentTimeAsString() {
        LocalTime timeOnly = DateTime.getTime();
        return timeOnly.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    // Method to update outside temperature label
    private void updateOutsideTempLabel() {
        int temperature = Temperature.getTemperature();
        outsideTempLabel.setText("Outside Temp: " + temperature + "°C");
    }

    // Method to adjust temperature display based on simulation time
    private void adjustTemperatureDisplay() {
        // Get the current temperature
        int currentTemperature = Temperature.getTemperature();

        // Generate a random boolean value to decide whether to increase or decrease temperature
        boolean increaseTemp = new Random().nextBoolean();

        // Increment or decrement temperature by 1
        int newTemperature = increaseTemp ? currentTemperature + 1 : currentTemperature - 1;

        // Update the temperature display
        Temperature.setTemperature(newTemperature); // Update temperature in Temperature class
        outsideTempLabel.setText("Outside Temp: " + newTemperature + "°C");
    }

    private void handleMoveUser() {
        JDialog moveDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Change Location", true);
        moveDialog.setLayout(new BorderLayout());
    
        Vector<String> locations = new Vector<>();
        int selectedIndex = -1; // Default value for no selection
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            locations.add(room.getName());
            // Check if the current room matches the active user's room
            if (room.getName().equals(shs.activeUser.getRoom().getName())) {
                selectedIndex = i; // Set the index as the selected index
            }
        }
    
        JComboBox<String> locationDropdown = new JComboBox<>(locations);
    
        // Set the selected index to match the active user's room
        if (selectedIndex != -1) {
            locationDropdown.setSelectedIndex(selectedIndex);
        }
    
        // Add ActionListener to capture user selection
        locationDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedRoomName = (String) comboBox.getSelectedItem();
                // Store the selected room name in the variable selection
                selection = selectedRoomName;
    
                // Iterate over the rooms ArrayList and compare the selected room name
                for (Room room : rooms) {
                    if (room.getName().equals(selectedRoomName)) {
                        // Do something with the room object if needed
                        // System.out.println("Selected Room: " + room.getName());
                        shs.activeUser.moveToRoom(room);
                        locationLabel.setText("Location: " + selectedRoomName);
                    }
                }
            }
        });
    
        moveDialog.add(locationDropdown, BorderLayout.CENTER);
    
        // Set dialog size and visibility
        moveDialog.setSize(300, 150);
        moveDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        moveDialog.setVisible(true);
    }
    
    
}