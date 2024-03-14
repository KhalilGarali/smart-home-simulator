package main.java.logic.users;

import main.java.model.rooms.Room;

public class Child extends FamilyMember{
    public Child(String name){
        super(name);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
    }
    public Child(String name, Room room)
    {
        super(name, room);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
    }
    @Override
    public String toString() {
        return "Child " + this.name;
    }
}
