/**
 * parent class for all user classes
 * contains the name and the list of permissions for every class
 */

package main.java.logic.users;
import main.java.model.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    List<Permissions> permissions;
    String name;
    Room room = null;
    public User(String name){
        permissions = new ArrayList<>();
        this.name = name;
    }

    public Boolean hasPermission(Permissions permission){
        return permissions.contains(permission);
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
    public void enterRoom(Room room){
        this.room = room;
        room.addUserToRoom(this);
        room.turnLightOn();
    }
    public void exitRoom(Room room){
        room.removeUserFromRoom(this);
        room.turnLightOff();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }
}
