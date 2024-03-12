package main.java.logic.singletonPattern;

import java.util.ArrayList;

import main.java.model.rooms.Room;

public class RoomManager {

  private static RoomManager instance;

  private ArrayList<Room> rooms;

  private RoomManager() {
      rooms = new ArrayList<>();
  }

  public static RoomManager getInstance() {
      if (instance == null) {
          instance = new RoomManager();
      }
      return instance;
  }

  public ArrayList<Room> getRooms() {
      return rooms;
  }

  public void addRoom(Room room) {
      rooms.add(room);
  }
}
