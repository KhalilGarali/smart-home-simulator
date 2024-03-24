package main.java.logic.users;

import main.java.model.rooms.Room;

public class Admin extends User{
    public Admin(String name){
        super(name);

        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.TEMP);
    }
    public Admin(String name, Room room){
        super(name, room);

        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.TEMP);
    }
}
