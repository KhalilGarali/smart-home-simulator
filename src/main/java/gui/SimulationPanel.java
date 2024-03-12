package main.java.gui;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.plaf.metal.MetalToggleButtonUI;

import main.java.logic.dashboard.DateTime;
import main.java.logic.dashboard.TimeSpeed;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.java.model.fixtures.Temperature;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class SimulationPanel extends JPanel {
    private JToggleButton simulationToggle;
    private JLabel locationLabel, timeSpeedLabel, userLabel, outsideTempLabel, dateLabel, timeLabel;
    private JSlider timeSpeedSlider;
    private JLabel userIcon;
    private JButton editButton;
    private Temperature outsideTemperature;
    DateTime currentDateTime = new DateTime();
    private TimeSpeed timeSpeed;

    public SimulationPanel() {
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
        // Set the icon of the button
        editButton.setIcon(new ImageIcon("src\\main\\resources\\editIcon.png"));
        // Add an action listener for the edit button
        // editButton.addActionListener(e -> {
        // // Open a dialog or another frame to edit simulation parameters
        // editSimulationParameters();
        // });
        editButton.addActionListener(e -> {
            editSimulationParameters(); // Call method to open edit dialog
        });

        // User and Location
        userLabel = new JLabel("User: Parent");
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
        timeSpeedSlider = new JSlider();
        timeSpeed = new TimeSpeed();
        timeSpeedSlider.addChangeListener(e -> {
            int speed = timeSpeedSlider.getValue();
            timeSpeed.setSpeed(speed);
        });

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

        outsideTemperature = new Temperature(11);
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
    }

    // ActionListener class to update date and time labels
    private class UpdateDateTimeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the current time before incrementing
            LocalTime beforeTime = currentDateTime.getTime();

            // currentDateTime.incrementSecond();
            int increment = timeSpeed.calculateIncrement();
            currentDateTime.incrementTime(0, 0, increment);

            // Get the current time after incrementing
            LocalTime afterTime = currentDateTime.getTime();

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
        LocalDate dateOnly = currentDateTime.getDate();
        return dateOnly.format(DateTimeFormatter.ofPattern("E MMM dd yyyy"));
    }

    // Method to get current time as string
    private String getCurrentTimeAsString() {
        LocalTime timeOnly = currentDateTime.getTime();
        return timeOnly.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    // Method to update outside temperature label
    private void updateOutsideTempLabel() {
        int temperature = outsideTemperature.getTemperature();
        outsideTempLabel.setText("Outside Temp: " + temperature + "°C");
    }

    // Method to adjust temperature display based on simulation time
    private void adjustTemperatureDisplay() {
        // Get the current temperature
        int currentTemperature = outsideTemperature.getTemperature();

        // Generate a random boolean value to decide whether to increase or decrease temperature
        boolean increaseTemp = new Random().nextBoolean();

        // Increment or decrement temperature by 1
        int newTemperature = increaseTemp ? currentTemperature + 1 : currentTemperature - 1;

        // Update the temperature display
        outsideTemperature.setTemperature(newTemperature); // Update temperature in Temperature class
        outsideTempLabel.setText("Outside Temp: " + newTemperature + "°C");
    }

    // Method to handle editing simulation parameters
    private void editSimulationParameters() {
        // Create a dialog window
        JDialog editDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Parameters", true);
        editDialog.setLayout(new BorderLayout());
    
        // Panel to hold components
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(6, 2, 5, 5));
    
        // Date Labels and TextFields
        JLabel dateEditLabel1 = new JLabel("Year:");
        JTextField dateEditField1 = new JTextField();
        JLabel dateEditLabel2 = new JLabel("Month:");
        JTextField dateEditField2 = new JTextField();
        JLabel dateEditLabel3 = new JLabel("Day:");
        JTextField dateEditField3 = new JTextField();
    
        // Time Label and TextField
        JLabel timeEditLabel1 = new JLabel("Hour:");
        JTextField timeEditField1 = new JTextField();
        JLabel timeEditLabel2 = new JLabel("Minute:");
        JTextField timeEditField2 = new JTextField();
        JLabel timeEditLabel3 = new JLabel("Second:");
        JTextField timeEditField3 = new JTextField();
    
        // Add components to edit panel
        editPanel.add(dateEditLabel1);
        editPanel.add(dateEditField1);
        editPanel.add(dateEditLabel2);
        editPanel.add(dateEditField2);
        editPanel.add(dateEditLabel3);
        editPanel.add(dateEditField3);
        editPanel.add(timeEditLabel1);
        editPanel.add(timeEditField1);
        editPanel.add(timeEditLabel2);
        editPanel.add(timeEditField2);
        editPanel.add(timeEditLabel3);
        editPanel.add(timeEditField3);
    
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
    
        // Add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the entered values to integers
                    int year = Integer.parseInt(dateEditField1.getText());
                    int month = Integer.parseInt(dateEditField2.getText());
                    int day = Integer.parseInt(dateEditField3.getText());
    
                    // Check if the date is valid
                    if (isValidDate(year, month, day)) {
                        int hour = Integer.parseInt(timeEditField1.getText());
                        int minute = Integer.parseInt(timeEditField2.getText());
                        int second = Integer.parseInt(timeEditField3.getText());
    
                        // Update date and time labels with user input
                        currentDateTime.setDate(year, month, day);
                        currentDateTime.setTime(hour, minute, second);
                        dateLabel.setText("Date: " + currentDateTime.getDate());
                        timeLabel.setText("Time: " + currentDateTime.getTime());
    
                        // Close the dialog
                        editDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(editDialog, "Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Display an error message if parsing fails
                    JOptionPane.showMessageDialog(editDialog, "Please enter valid integers for date fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without saving changes
                editDialog.dispose();
            }
        });
    
        // Add buttons to button panel
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
    
        // Add panels to dialog
        editDialog.add(editPanel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);
    
        // Set dialog properties
        editDialog.setSize(300, 200);
        editDialog.setLocationRelativeTo(null); // Center the dialog
        editDialog.setVisible(true); // Display the dialog
    }

    // Method to validate if the given year, month, and day form a valid date
    private boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}