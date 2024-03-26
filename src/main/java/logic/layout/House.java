package main.java.logic.layout;

import java.util.ArrayList;

import main.java.logic.modules.SHS;
import main.java.model.rooms.Room;

public class House {
    private static House house;
    private Layout houseLayout;
    ArrayList<Room> rooms = new ArrayList<>();

    private House(){
        // houseLayout = new Layout(filepath);
        // ArrayList<Room> fileRooms = (ArrayList<Room>) houseLayout.getRooms();
        // for (Room room : fileRooms) {
        //     addRoom(room);
        // }
    }

    private void addRoom(Room room){
        rooms.add(room);
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

    public void loadLayoutFromFile(String filePath) {
        rooms.clear(); // Clear the current rooms list
        houseLayout = new Layout(filePath);
        ArrayList<Room> fileRooms = (ArrayList<Room>) houseLayout.getRooms();
        for (Room room : fileRooms) {
            addRoom(room);
        }

        // Additional actions after loading the rooms (like updating observers)
    }
}
