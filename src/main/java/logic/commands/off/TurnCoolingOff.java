package main.java.logic.commands.off;

import java.util.ArrayList;

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
        outpanel.appendText(toConsole());
        return true;
    }

    @Override
    public String toString(){
        return "turn a cooling off";
    }
    
    @Override
    public ArrayList<String> toConsole(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Target: " + room.getClass().getSimpleName() + " Cooling");
        text.add("Event Type: Turn Off");
        text.add("Event Description: Turn Cooling Off");
        // text.add(room.getHvac().coolingToString());
        return text;
    }
}
