package main.java.logic.users;

import main.java.model.rooms.Room;

public class Guest extends User{
    public Guest(String name){
        super(name);
        this.name = name;

        permissions.add(Permissions.LIGHT);
    }
    public Guest(String name, Room room){
        super(name, room);
        permissions.add(Permissions.LIGHT);
    }
    @Override
    public String toString() {
        return "Guest " + this.name;
    }
}
