package main.java.logic.commands.change;

import java.util.ArrayList;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class ChangeTemperature extends Command {
    protected Room room;
    protected int temperature;
    protected int previousTemp;
    public ChangeTemperature(Room room, int temperature){
        previousTemp = 0;
        this.room = room;
        this.temperature = temperature;
    }

    @Override
    public Permissions requirePermissions(){
        return REQUIRED_PERMISSIONS;
    }

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
        previousTemp = room.getCurrentTemperature();
        room.setTemperature(temperature);
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        outpanel.appendText(toConsole());
        return true;
    }

    @Override
    public String toString(){
        return "change temperature";
    }
           
    @Override
    public ArrayList<String> toConsole(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Target: " + room.getClass().getSimpleName() + " Temperature");
        text.add("Event Type: Change");
        text.add("Event Description: Change Temperature");
        text.add("Previous temperature: " + previousTemp);
        text.add("Current temperature: " + room.getCurrentTemperature());
        return text;
    }
}
