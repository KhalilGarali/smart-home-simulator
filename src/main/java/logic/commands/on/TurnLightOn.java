package main.java.logic.commands.on;

import java.util.ArrayList;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class TurnLightOn extends Command {
    protected Room room;

    public TurnLightOn(Room room){
        this.room = room;
        this.REQUIRED_PERMISSIONS = Permissions.LIGHT;
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
        room.turnLightOn();
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        outpanel.appendText(toConsole());
        return true;
    }

    @Override
    public String toString(){
        return "turn a light on";
    }
   
    @Override
    public ArrayList<String> toConsole(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Target: " + room.getClass().getSimpleName() + " light");
        text.add("Event Type: Turn On");
        text.add("Event Description: Turn Light On");
        text.add(room.getLight().lightToSTring());
        return text;
    }

}
