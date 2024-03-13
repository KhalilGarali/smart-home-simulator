package main.java.logic.commands.close;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class CloseAllDoors extends Command {
    protected Room room;

    public CloseAllDoors(Room room){
        this.room = room;
        this.REQUIRED_PERMISSIONS = Permissions.DOOR;
    }
    public Room getRoom() {
        return room;
    }

    @Override
    public Permissions requirePermissions(){
        return REQUIRED_PERMISSIONS;
    }

    @Override
    public Boolean execute(){
        // if(room.getHasDoors()){
        // room.setDoorOpen(false);
        // } else {
        // potentially send a GUI error
        // System.out.println("No doors to open in " + room);
        // }
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println(room);
        System.out.print("command done: ");
        room.closeAllDoors();
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        return true;
    }

    @Override
    public String toString(){
        return "close all doors";
    }
}
