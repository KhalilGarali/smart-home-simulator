package main.java.logic.commands.open;

import java.util.ArrayList;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class OpenADoor extends Command{
    protected Room room;
    protected int doorNumber;

    public OpenADoor(Room room, int doorNumber){
        this.room = room;
        this.doorNumber = doorNumber;
        this.REQUIRED_PERMISSIONS = Permissions.DOOR;
    }

    @Override
    public Permissions requirePermissions(){
        return REQUIRED_PERMISSIONS;
    }

    @Override
    public Boolean execute(){
        // if(room.getHasDoors()){
            // room.setDoorOpen(true);
        // } else {
            // potentially send a GUI error
            // System.out.println("No doors to open in " + room);
        // }
        // System.out.println("\n--------------------------------------------------------------------");
        // System.out.println(room);
        // System.out.print("command done: ");
        room.openDoor(doorNumber);
        // System.out.println(room);
        // System.out.println("----------------------------------------------------------------------");
        outpanel.appendText(toConsole());
        return true;
    }

    @Override
    public String toString(){
        return "close a window";
    }
    
    @Override
    public ArrayList<String> toConsole(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Target: " + room.getClass().getSimpleName() + " door" + doorNumber);
        text.add("Event Type: Open");
        text.add("Event Description: Open Door");
        text.add("door" + doorNumber + " " + room.getDoor(doorNumber).toString());
        return text;
    }
}
