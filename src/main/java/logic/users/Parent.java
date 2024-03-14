package main.java.logic.users;

import main.java.model.rooms.Room;

public class Parent extends FamilyMember{

    public Parent(String name){

        super(name);
        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.TEMP);
    }

    public Parent(String name, Room room)
    {
        super(name, room);
        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.TEMP);
    }

    @Override
    public String toString() {
        return "Parent " + this.name;
    }
    
}
