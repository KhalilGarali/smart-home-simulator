package main.java.logic.commands.change;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class changeTemperature implements Command {
    protected Room room;

    public changeTemperature(Room room){
        this.room = room;
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
        return true;
    }

    @Override
    public String toString(){
        return "change temperature";
    }
}
