package main.java.logic.dashboard;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;
import java.time.Duration;

import main.java.logic.users.*;
import main.java.model.openings.*;
import main.java.model.rooms.*;
import main.java.logic.dashboard.DateTime;

public class Dashboard {

  private DateTime simulationDateTime = new DateTime();

  private double timeSpeed;

  private int externalTemperature = 5;
  private Random random = new Random();
  private LocalDateTime lastTemperatureUpdateTime; // Store the last time the temperature was modified

  private User loggedUser = new Admin("Logged User");
  private User otherUser = new Stranger("Stranger");

  private Room currentRoomLoggedUser = layout[0][0];
  private int xLoggedUser = 0;
  private int yLoggedUser = 0;

  private Room currentRoomNonLoggedUser = layout[3][2];
  private int xNonLoggedUser = 3;
  private int yNonLoggedUser = 2;

  // temp house layout to build the use case logic
  public static Room[][] layout = {
    {new Kitchen("kitchen"), new Garage("garage"), new Bathroom("bathroom1")},
    {new Bedroom("bedroom1"), new Bedroom("bedroom2"), new Bathroom("bathroom2")},
    {new Bedroom("bedroom3"), new Bedroom("bedroom4"), new Bathroom("bathroom3")},
    {new Bedroom("bedroom5"), new Bathroom("bathroom4"), new Bedroom("bedroom6")}
};

  // adjust temperature every hour and make sure the temperature change makes sesne
  
  public Dashboard(User loggedUser, User otherUser) {
    this.loggedUser = loggedUser;
    this.otherUser = otherUser;
  }
  // **************************************
  // **************************************
  // Use Case: Modify the date and time
  public void setDateTime(int year, int month, int day, int hour, int minute) {
    simulationDateTime.setDateTime(year, month, day, hour, minute);
    System.out.println("Date and time set: " + simulationDateTime.getDateTime());
  }

  public DateTime getDateTime() {
    return this.simulationDateTime;
  }
  // **************************************
  // **************************************
  // Use Case: The simulated time speed can be increased or decreased
  public void adjustTimeSpeed(double speed) {
    this.timeSpeed = speed;
    System.out.println("Time speed adjusted to: " + speed);

    // no need to modify the time if the speed is 1.0 (normal speed)
    if (speed != 1.0) {
        // calculate the time difference based on the speed
        long timeDifferenceSeconds = (long) (speed - 1.0) * 1000; // convert speed difference to milliseconds

        // apply the time difference to the current date and time
        for (int i = 0; i < timeDifferenceSeconds; i++) {
            simulationDateTime.tick();
        }
    }
  }

  public double getTimeSpeed() {
    return this.timeSpeed;
  }
  // **************************************
  // **************************************
  // Use Case: Move the logged user to another room
  public void moveLoggedUser(Room destination) {
    // find the destination location in the layout
    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        if (layout[i][j].equals(destination)) {
            // update the logged user's current location to the destination
            setCurrentRoomLoggedUser(destination);
            setXLoggedUser(i);
            setYLoggedUser(j);
            System.out.println("User moved to: " + destination.getName());
            return;
        }
      }
    }
  }

  public Room getCurrentRoomLoggedUser() {
    return this.currentRoomLoggedUser;
  }

  private void setCurrentRoomLoggedUser(Room destination) {
    this.currentRoomLoggedUser = destination;
  }

  private void setXLoggedUser(int x) {this.xLoggedUser = x;}

  private void setYLoggedUser(int y) {this.yLoggedUser = y;}
  // **************************************
  // **************************************
  // Use Case: Place people in specific rooms, or outside the home
  public void moveNonLoggedUser(Room destination) {
    // find the destination location in the layout
    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        if (layout[i][j].equals(destination)) {
            // update the logged user's current location to the destination
            setCurrentRoomNonLoggedUser(destination);
            setXNonLoggedUser(i);
            setYNonLoggedUser(j);
            System.out.println("User moved to: " + destination.getName());
            return;
        }
      }
    }
  }

  public Room getCurrentRoomNonLoggedUser() {
    return this.currentRoomNonLoggedUser;
  }

  private void setCurrentRoomNonLoggedUser(Room destination) {
    this.currentRoomNonLoggedUser = destination;
  }

  private void setXNonLoggedUser(int x) {this.xNonLoggedUser = x;}

  private void setYNonLoggedUser(int y) {this.yNonLoggedUser = y;}
  // **************************************
  // **************************************
  // Use Case: Modify the temperature outside the home
  // method to calculate external temperature
  private int modifyTemperatureOutsideHome() {
        LocalDateTime currentTime = simulationDateTime.getDateTime();

        // Check if an hour has passed since the last temperature update
        if (lastTemperatureUpdateTime == null || Duration.between(lastTemperatureUpdateTime, currentTime).toHours() >= 1) {
            // Randomly increment or decrement the temperature by an integer value between -3 and 3
            int temperatureChange = random.nextInt(7) - 3;
            externalTemperature += temperatureChange;

            // Ensure temperature stays within reasonable bounds (Optional)
            if (externalTemperature < -20) {
                externalTemperature = -20;
            } else if (externalTemperature > 30) {
                externalTemperature = 30;
            }

            // Print the updated temperature
            // System.out.println("Current external temperature: " + externalTemperature);

            // Update the last temperature update time
            lastTemperatureUpdateTime = currentTime;
        }
        return externalTemperature;
    }

  public void getExternalTemperature() {
    System.out.println("Current external temperature: " + modifyTemperatureOutsideHome());
  }
  // **************************************
  // **************************************
  // Use Case: Block window movements by putting an arbitrary object
  public void blockWindow(Window window) {
    window.blockWindow();
    System.out.println("Window blocked.");
  }

  public void unblockWindow(Window window) {
    window.unblockWindow();
    System.out.println("Window unblocked.");
  }
}
