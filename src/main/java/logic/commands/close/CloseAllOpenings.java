package main.java.logic.commands.close;

import java.util.ArrayList;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class CloseAllOpenings extends Command {
    protected Room room;

    public CloseAllOpenings(Room room){
        this.room = room;
        //problem with permissions here
        this.REQUIRED_PERMISSIONS = Permissions.DOOR;
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
        room.closeAllOpenings();
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        outpanel.appendText(toConsole());
        return true;
    }

    @Override
    public String toString(){
        return "close all openings";
    }
        
    @Override
    public ArrayList<String> toConsole(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Target: " + room.getClass().getSimpleName() + " all windows and doors");
        text.add("Event Type: Close");
        text.add("Event Description: Close All Windwos and Doors");
        text.add("window1 " + room.getWindow(1).toString());
        text.add("window2 " + room.getWindow(2).toString());
        text.add("door1 " + room.getDoor(1).toString());
        text.add("door2 " + room.getDoor(2).toString());
        return text;
    }
}
