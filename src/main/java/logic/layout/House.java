package main.java.logic.layout;

import java.util.ArrayList;

import main.java.logic.modules.SHS;
import main.java.model.fixtures.HVAC;
import main.java.model.rooms.Outside;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.Zone;

public class House {

    // attributes
    private static House house;
    private Layout houseLayout;
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Zone> zones = new ArrayList<>();
    ArrayList<HVAC> hvacs = new ArrayList<>();

    // private constructor for the Singleton pattern
    private House(){}

    // method to load house layout from a file
    public void loadLayoutFromFile(String filePath) {
        rooms.clear(); // Clear the current rooms list
        houseLayout = new Layout(filePath);
        ArrayList<Room> fileRooms = (ArrayList<Room>) houseLayout.getRooms();
        ArrayList<Zone> fileZones = (ArrayList<Zone>) houseLayout.getZones();
        for (Room room : fileRooms) {
            addRoom(room);
        }
        for (Zone zone : fileZones) {
            addZone(zone);
        }
        for (HVAC hvac : houseLayout.getHVACs()) {
            addHVAC(hvac);
        }
    }

    // method to add room
    private void addRoom(Room room){
        rooms.add(room);
    }

    // method to add zone
    private void addZone(Zone zone){
        zones.add(zone);
    }

    private void addHVAC(HVAC hvac){
        hvacs.add(hvac);
    }

    // getInstance method to return the House instance
    public static synchronized House getInstance(){
        if(house == null){
            house = new House();
        }
        return house;
    }

    // return rooms
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }

    // return HVACs
    public ArrayList<HVAC> getHVACs(){
        return this.hvacs;
    }

    // return outside
    public Room getOutside() {
        for (Room room : rooms) {
            if (room instanceof Outside) {
                return room;
            }
        }
        return null;
    }
}
