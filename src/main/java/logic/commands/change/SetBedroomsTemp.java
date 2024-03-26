package main.java.logic.commands.change;

import java.util.ArrayList;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;
import main.java.model.rooms.BedRoom;


public class SetBedroomsTemp extends Command{

    public BedRoom room;
    private int desiredTemp;
 

    public SetBedroomsTemp(BedRoom room){
        this.room = room;
        this.REQUIRED_PERMISSIONS = Permissions.TEMP;
    }

    @Override
    public Permissions requirePermissions(){
        return REQUIRED_PERMISSIONS;
    }

    public Boolean execute(){
        // room.setDesiredTemperature(desiredTemp);
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
        text.add("Current temperature: " + room.getCurrentTemperature());
        return text;
    }
    
}
