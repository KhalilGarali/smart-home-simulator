package main.java.logic.layout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import main.java.model.fixtures.HVAC;
import main.java.model.fixtures.Light;
import main.java.model.openings.Door;
import main.java.model.openings.Window;
import main.java.model.rooms.Basement;
import main.java.model.rooms.Bathroom;
import main.java.model.rooms.BedRoom;
import main.java.model.rooms.Garage;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.LivingRoom;
import main.java.model.rooms.Porch;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.BathroomsZone;
import main.java.model.rooms.zones.BedroomsZone;
import main.java.model.rooms.zones.CommonZone;
import main.java.model.rooms.zones.GatewayZone;
import main.java.model.rooms.zones.Zone;

// *******************
// create an array for the house layout
// *******************

public class Layout {
    private List<Room> rooms = new ArrayList<>();
    private List<Zone> zones = new ArrayList<>(); // This will be populated with the zones (0 or max of 1 per zone type). JB!

    public Layout(String filePath) {
        readLayout(filePath);
    }

    private void readLayout(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line, roomName;
            Room currentRoom = null;
            Light currentLight = null;
            Window currentWindow = null;
            Door currentDoor = null;
            String tempName = null;
            Zone currentZone = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Room:")) {
                    if (currentRoom != null && currentZone != null) {
                        rooms.add(currentRoom);
                    }
                    if(line.substring(6).trim().equalsIgnoreCase("kitchen")){
                        currentRoom = new Kitchen("Kitchen");
                        currentZone = CommonZone.getInstance();
                    } else if (line.substring(6).trim().equalsIgnoreCase("bedroom")){
                        currentRoom = new BedRoom("bedroom");
                        currentZone = BedroomsZone.getInstance();
                    } else if (line.substring(6).trim().equalsIgnoreCase("garage")){
                        currentRoom = new Garage("garage");
                        currentZone = GatewayZone.getInstance();
                    } else if (line.substring(6).trim().equalsIgnoreCase("bathroom")){
                        currentRoom = new Bathroom("bathroom");
                        currentZone = BathroomsZone.getInstance();
                    } else if (line.substring(6).trim().equalsIgnoreCase("livingroom")){
                        currentRoom = new LivingRoom("living room");
                        currentZone = CommonZone.getInstance();
                    } else if (line.substring(6).trim().equalsIgnoreCase("basement")){
                        currentRoom = new Basement("basement");
                        currentZone = CommonZone.getInstance();
                    } else if (line.substring(6).trim().equalsIgnoreCase("porch")){
                        currentRoom = new Porch("porch");
                        currentZone = GatewayZone.getInstance();
                    }
                    
                //should implement other types of rooms once made
                } else if (currentRoom != null) {
                    if (line.startsWith("Name:")) {
                        roomName = line.substring(6);
                        currentRoom.setName(roomName);
                    }else if (line.startsWith("Light:")) {
                        currentLight = new Light();
                        if (line.substring(6).trim().equalsIgnoreCase("on")){
                            currentLight.setLightOn();
                        } else if (line.substring(6).trim().equalsIgnoreCase("off")){
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

    public List<Zone> getZones() {
        return zones;
    }
}

