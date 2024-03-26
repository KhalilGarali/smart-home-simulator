package main.java.logic.layout;

import java.util.ArrayList;

import main.java.logic.modules.SHS;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.Zone;

public class House {
    private static House house;
    private Layout houseLayout;
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Zone> zones = new ArrayList<>();

    private House(){
        houseLayout = new Layout("src/main/java/logic/layout/houseLayoutFile.txt");
        ArrayList<Room> fileRooms = (ArrayList<Room>) houseLayout.getRooms();
        ArrayList<Zone> fileZones = (ArrayList<Zone>) houseLayout.getZones();
        for (Room room : fileRooms) {
            addRoom(room);
        }
        for (Zone zone : fileZones) {
            addZone(zone);
        }
    }

    private void addRoom(Room room){
        rooms.add(room);
    }

    private void addZone(Zone zone){
        zones.add(zone);
    }

    public static House getInstance(){
        if(house == null){
            house = new House();
        }
        return house;
    }

    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
}
