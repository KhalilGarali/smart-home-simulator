package main.java.gui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import static main.java.logic.dashboard.DateTime.getInstance;
import static main.java.logic.users.UserPersistence.saveUsers;

public class SimulationPanel extends JPanel {
    private JToggleButton simulationToggle;
    private JLabel locationLabel, timeSpeedLabel, userLabel, outsideTempLabel, dateLabel, timeLabel;
    private JSlider timeSpeedSlider;
    private JLabel userIcon;
    private JButton editButton;
    DateTime currentDateTime = getInstance();
    private TimeSpeed timeSpeed;
    private House house = House.getInstance();
    private ArrayList<Room> rooms = house.getRooms();
    private JButton editLocationButton;
    private String selection;
    private JButton uploadTempButton;
    private JButton saveUsersButton;

    // used as a dummy value
    private SHS shs = SHS.getInstance();
    private ArrayList<User> users = shs.getHouseUsers();

    // Add link to outputPanel to print information
    private static OutputPanel outpanel = OutputPanel.getInstance();
    private DateTime dateTime = DateTime.getInstance();


    public SimulationPanel() {
        // dummy variable
        users.add(new Stranger("Example"));
        Room dummyRoom = rooms.get(2);
        users.get(0).moveToRoom(dummyRoom);
        shs.activeUser = users.get(0);
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
        editButton.addActionListener(e -> {
            // Open a dialog or another frame to edit simulation parameters
            handleProfileChange();
        });

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
        timeSpeed = TimeSpeed.getInstance();
        timeSpeedSlider.addChangeListener(e -> {
            int speed = timeSpeedSlider.getValue();
            timeSpeed.setSpeed(speed*2);
            dateTime.setMultiplier(dateTime.getMultiplier()*10);
            // This writes to file too often
            // TODO: Limit the amount of times this writes to file
            ArrayList<String> text = new ArrayList<>();
            text.add("Target: Speed Slider");
            text.add("Event type: Change");
            text.add("Event Description: Adjust Speed Slider");
            text.add("Current time speed: " + timeSpeed.getSpeed());
            outpanel.appendText(text);
            
        });

        // upload csv file containing weather data
        uploadTempButton = new JButton("Upload Outside Temperature");
        uploadTempButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadTempButton.setMaximumSize(new Dimension(250, 40));
        uploadTempButton.addActionListener(e -> openFileChooser());

        saveUsersButton = new JButton("Save Users to File");
        saveUsersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveUsersButton.setMaximumSize(new Dimension(250, 40));
        saveUsersButton.addActionListener(e -> {
            openDirectoryChooser();
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

    private void openDirectoryChooser() {
        LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current local date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // Replace colons with dashes in the time part
        String dateTimeWithDashes = formattedDateTime.replace(":", "-");


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chose where to save Users");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showDialog(this, "Choose");

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            System.out.println("Save users to: " + selectedFolder.getAbsolutePath()+"/users-"+dateTimeWithDashes+".txt");
             UserPersistence.saveUsers(selectedFolder.getAbsolutePath()+"/users-"+dateTimeWithDashes+".txt", users);
        }
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
        add(Box.createVerticalStrut(20));
        add(editLocationButton);
        add(Box.createVerticalStrut(30));
        add(uploadTempButton);
        add(Box.createVerticalStrut(10));
        add(saveUsersButton);
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
            ArrayList<String> text = new ArrayList<>();
            text.add("Target: Temperature");
            text.add("Event type: Upload");
            text.add("Event Description: Upload Outside Temperature through Temperature CSV File");
            text.add("Current temperature file: " + selectedFile.getName());
            outpanel.appendText(text);
        }
    }

    private void readTemperatureFile(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current local date and time
            LocalDateTime closestDateTime = null;
            double closestTemp = Double.NaN;

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { // Ensure at least two columns are present
                    String dateTimeStr = parts[1].trim(); // Index 1 for "datetime"
                    String tempStr = parts[2].trim(); // Index 2 for "temp"

                    // Check if the column names match "datetime" and "temp"
                    if (parts[1].equalsIgnoreCase("datetime") && parts[2].equalsIgnoreCase("temp")) {
                        continue; // Skip the header row
                    }

                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
                    double temp = Double.parseDouble(tempStr);

                    // Check if this datetime is closer to the current local datetime
                    if (closestDateTime == null || Math.abs(Duration.between(dateTime, currentDateTime).toMillis()) < Math.abs(Duration.between(closestDateTime, currentDateTime).toMillis())) {
                        closestDateTime = dateTime;
                        closestTemp = temp;
                    }
                }
            }

            if (closestDateTime != null) {
                // Update DateTime.java with the selected datetime value
                DateTime.setDateTime(closestDateTime);
                // Update Temperature.java with the corresponding temp value
                Temperature.setTemperature((int) closestTemp);

                // Update the date and time labels on the GUI
                updateDateTimeLabels();
                updateOutsideTempLabel();

                JOptionPane.showMessageDialog(this, "Temperature data updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No temperature data found in the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

            currentDateTime.incrementTime(0, 0, increment);

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
                        ArrayList<String> text = new ArrayList<>();
                        text.add("Target: Active User");
                        text.add("Event type: Change");
                        text.add("Event Description: Change Active User Location");
                        text.add("Active User Current Location: " + selectedRoomName);
                        outpanel.appendText(text);
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

    private void handleProfileChange(){
        JDialog changeUserDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Change Active User", true);
        changeUserDialog.setLayout(new BorderLayout());
    
        Vector<String> tempUsers = new Vector<>();
        int selectedIndex = -1; // Default value for no selection
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            tempUsers.add(user.getName());
            // Check if the current room matches the active user's room
            if (user.getName().equals(shs.activeUser.getName())) {
                selectedIndex = i; // Set the index as the selected index
            }
        }
    
        JComboBox<String> availableUsersDropdown = new JComboBox<>(tempUsers);
    
        // Set the selected index to match the active user's room
        if (selectedIndex != -1) {
            availableUsersDropdown.setSelectedIndex(selectedIndex);
        }
    
        // Add ActionListener to capture user selection
        availableUsersDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedUserName = (String) comboBox.getSelectedItem();
                // Store the selected room name in the variable selection
                selection = selectedUserName;
    
                // Iterate over the rooms ArrayList and compare the selected room name
                for (User user : users) {
                    if (user.getName().equals(selectedUserName)) {
                        shs.setActiveUser(user);
                        shs.notifyObservers();
                        userLabel.setText(user.getClass().getSimpleName() + " " + user.getName());
                        locationLabel.setText("Location: " + user.getRoom().getClass().getSimpleName());
                        ArrayList<String> text = new ArrayList<>();
                        text.add("Target: Active User");
                        text.add("Event type: Change");
                        text.add("Event Description: Change Active User");
                        text.add("Current active user: " + shs.activeUser.getClass().getSimpleName() + " " + shs.activeUser.getName() + " in " + shs.activeUser.getRoom().getClass().getSimpleName());
                        outpanel.appendText(text);
                    }
                }
            }
        });
    
        changeUserDialog.add(availableUsersDropdown, BorderLayout.CENTER);
    
        // Set dialog size and visibility
        changeUserDialog.setSize(300, 150);
        changeUserDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        changeUserDialog.setVisible(true);

    }

}