package main.java.logic.layout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import main.java.model.fixtures.Light;
import main.java.model.openings.Door;
import main.java.model.openings.Window;
import main.java.model.rooms.Bathroom;
import main.java.model.rooms.BedRoom;
import main.java.model.rooms.Garage;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;

// *******************
// create a 2D array for the house layout
// *******************

public class Layout {
    private List<Room> rooms = new ArrayList<>();

    public Layout(String filePath) {
        readLayout(filePath);
    }

    private void readLayout(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Room currentRoom = null;
            Light currentLight = null;
            Window currentWindow = null;
            Door currentDoor = null;
            String tempName = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Room:")) {
                    if (currentRoom != null) {
                        rooms.add(currentRoom);
                    }
                    if(line.substring(6).trim().equalsIgnoreCase("kitchen")){
                        currentRoom = new Kitchen("kitchen");
                    } else if (line.substring(6).trim().equalsIgnoreCase("bedroom")){
                        currentRoom = new BedRoom("bedroom");
                    } else if (line.substring(6).trim().equalsIgnoreCase("garage")){
                        currentRoom = new Garage("garage");
                    } else if (line.substring(6).trim().equalsIgnoreCase("bathroom")){
                        currentRoom = new Bathroom("bathroom");
                    }
                    //should implement other types of rooms once made
                } else if (currentRoom != null) {
                    if (line.startsWith("Light:")) {
                        currentLight = new Light();
                        if (line.substring(7).trim().equalsIgnoreCase("on")){
                            currentLight.setLightOn();
                        } else if (line.substring(7).trim().equalsIgnoreCase("off")){
                            currentLight.setLightOff();
                        }
                        currentRoom.setLight(currentLight);
                    } else if (line.startsWith("Door:")) {
                        tempName = line.substring(6).trim();
                        currentDoor = new Door(tempName);
                        if (line.substring(6).trim().equalsIgnoreCase("open")){
                            currentDoor.open();
                        } else if (line.substring(6).trim().equalsIgnoreCase("closed")){
                            currentDoor.close();
                        }
                        currentRoom.setDoor(currentDoor);
                    } else if (line.startsWith("Window:")) {
                        tempName = line.substring(8).trim();
                        currentWindow = new Window(tempName);
                        if (line.substring(8).trim().equalsIgnoreCase("open")){
                            currentWindow.open();
                        } else if (line.substring(8).trim().equalsIgnoreCase("closed")){
                            currentWindow.close();
                        }
                        currentRoom.setWindow(currentWindow);
                    }
                }
            }

            if (currentRoom != null) {
                rooms.add(currentRoom); // Add the last room
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    // Additional methods as needed
}

