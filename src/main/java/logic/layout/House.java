package main.java.logic.layout;

import java.util.ArrayList;

import main.java.logic.modules.SHS;
import main.java.model.fixtures.HVAC;
import main.java.model.rooms.Outside;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.Zone;

public class House {
    private static House house;
    private Layout houseLayout;
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Zone> zones = new ArrayList<>();
    ArrayList<HVAC> hvacs = new ArrayList<>();

    private House(){   
    }

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

    private void addRoom(Room room){
        rooms.add(room);
    }

    private void addZone(Zone zone){
        zones.add(zone);
    }

    private void addHVAC(HVAC hvac){
        hvacs.add(hvac);
    }

    public static synchronized House getInstance(){
        if(house == null){
            house = new House();
        }
        return house;
    }

    public ArrayList<Room> getRooms(){
        return this.rooms;
    }

    public ArrayList<HVAC> getHVACs(){
        return this.hvacs;
    }

    public Room getOutside() {
        for (Room room : rooms) {
            if (room instanceof Outside) {
                return room;
            }
        }
        return null;
    }
}
