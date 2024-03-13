// package main.java.logic.dashboard;

// import java.util.Date;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Random;
// import java.time.Duration;

// import main.java.logic.users.*;
// import main.java.model.openings.*;
// import main.java.model.rooms.*;
// import main.java.logic.dashboard.DateTime;
// import main.java.logic.layout.*;

// public class Dashboard {

//   private static DateTime simulationDateTime = new DateTime();

//   private double timeSpeed;

//   private static int externalTemperature = 5;
//   private static Random random = new Random();
//   private static LocalDateTime lastTemperatureUpdateTime; // Store the last time the temperature was modified

//   private Room currentRoomLoggedUser = null;
//   private Room currentRoomNonLoggedUser = null;

//   private ArrayList<Room> rooms;

//   // adjust temperature every hour and make sure the temperature change makes sesne
  
//   public Dashboard(ArrayList<Room> rooms) {
//     this.rooms = rooms;
//   }
//   // **************************************
//   // **************************************
//   // Use Case: Modify the date and time
//   public void setDateTime(int year, int month, int day, int hour, int minute) {
//     simulationDateTime.setDateTime(year, month, day, hour, minute);
//     System.out.println("Date and time set: " + simulationDateTime.getDateTime());
//   }

//   public static DateTime getDateTime() {
//     return simulationDateTime;
//   }
//   // **************************************
//   // **************************************
//   // Use Case: The simulated time speed can be increased or decreased
//   public void adjustTimeSpeed(double speed) {
//     this.timeSpeed = speed;
//     System.out.println("Time speed adjusted to: " + speed);

//     // no need to modify the time if the speed is 1.0 (normal speed)
//     if (speed != 1.0) {
//         // calculate the time difference based on the speed
//         long timeDifferenceSeconds = (long) (speed - 1.0) * 1000; // convert speed difference to milliseconds

//         // apply the time difference to the current date and time
//         for (int i = 0; i < timeDifferenceSeconds; i++) {
//             simulationDateTime.tick();
//         }
//     }
//   }

//   public double getTimeSpeed() {
//     return this.timeSpeed;
//   }
//   // **************************************
//   // **************************************
//   // Use Case: Move the logged user to another room
//   public void moveLoggedUser(Room destination) {
//     for(int i = 0; i < rooms.size(); i++) {
//       if(rooms.get(i).equals(destination)) {
//         setCurrentRoomLoggedUser(destination);
//         System.out.println("User moved to: " + destination.getName());
//         return;
//       }
//     }
//     System.out.println("room not found.");
//   }

//   public Room getCurrentRoomLoggedUser() {
//     return this.currentRoomLoggedUser;
//   }

//   private void setCurrentRoomLoggedUser(Room destination) {
//     this.currentRoomLoggedUser = destination;
//   }
//   // **************************************
//   // **************************************
//   // Use Case: Place people in specific rooms, or outside the home
//   public void moveNonLoggedUser(Room destination) {
//     for(int i = 0; i < rooms.size(); i++) {
//       if(rooms.get(i).equals(destination)) {
//         setCurrentRoomNonLoggedUser(destination);
//         System.out.println("User moved to: " + destination.getName());
//         return;
//       }
//     }
//     System.out.println("room not found.");
//   }

//   public Room getCurrentRoomNonLoggedUser() {
//     return this.currentRoomNonLoggedUser;
//   }

//   private void setCurrentRoomNonLoggedUser(Room destination) {
//     this.currentRoomNonLoggedUser = destination;
//   }
//   // **************************************
//   // **************************************
//   // Use Case: Modify the temperature outside the home
//   // method to calculate external temperature
//   public static int modifyTemperatureOutsideHome() {
//         LocalDateTime currentTime = simulationDateTime.getDateTime();

//         // Check if an hour has passed since the last temperature update
//         if (lastTemperatureUpdateTime == null || Duration.between(lastTemperatureUpdateTime, currentTime).toHours() >= 1) {
//             // Randomly increment or decrement the temperature by an integer value between -3 and 3
//             int temperatureChange = random.nextInt(7) - 3;
//             externalTemperature += temperatureChange;

//             // Ensure temperature stays within reasonable bounds (Optional)
//             if (externalTemperature < -20) {
//                 externalTemperature = -20;
//             } else if (externalTemperature > 30) {
//                 externalTemperature = 30;
//             }

//             // Print the updated temperature
//             // System.out.println("Current external temperature: " + externalTemperature);

//             // Update the last temperature update time
//             lastTemperatureUpdateTime = currentTime;
//         }
//         return externalTemperature;
//     }

//   public static void getExternalTemperature() {
//     System.out.println("Current external temperature: " + modifyTemperatureOutsideHome());
//   }
//   // **************************************
//   // **************************************
//   // Use Case: Block window movements by putting an arbitrary object
//   public void blockWindow(Window window) {
//     window.blockWindow();
//     System.out.println("Window blocked.");
//   }

//   public void unblockWindow(Window window) {
//     window.unblockWindow();
//     System.out.println("Window unblocked.");
//   }
// }
