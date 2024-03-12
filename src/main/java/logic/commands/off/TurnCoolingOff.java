package main.java.logic.commands.off;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class TurnCoolingOff extends Command {
    protected Room room;

    public TurnCoolingOff(Room room){
        this.room = room;
        this.REQUIRED_PERMISSIONS = Permissions.TEMP;
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
        room.turnCoolingOff();
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        return true;
    }

    @Override
    public String toString(){
        return "turn a cooling off";
    }
}
