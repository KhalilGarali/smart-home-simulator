package main.java.gui;

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
// import java.time.DateTimeException;

public class SimulationPanel extends JPanel {
    private JToggleButton simulationToggle;
    private JLabel locationLabel, timeSpeedLabel, userLabel, outsideTempLabel, dateLabel, timeLabel;
    private JSlider timeSpeedSlider;
    private JLabel userIcon;
    private JButton editButton;
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

        // outsideTemperature = new Temperature(11);
        Temperature.setTemperature(11);
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
}