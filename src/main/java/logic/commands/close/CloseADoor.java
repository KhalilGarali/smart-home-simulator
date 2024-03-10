package main.java.logic.commands.close;

import main.java.logic.commands.Command;
import main.java.model.rooms.Room;

public class CloseADoor implements Command{
    protected Room room;

    public CloseADoor(Room room){
        this.room = room;
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
}
