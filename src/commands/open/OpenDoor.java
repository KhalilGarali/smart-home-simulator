package commands.open;

import commands.Command;
import rooms.Room;

public class OpenDoor implements Command{
    protected Room room;

    public OpenDoor(Room room){
        this.room = room;
    }

    @Override
    public void execute(){
        // if(room.getHasDoors()){
            // room.setDoorOpen(true);
        // } else {
            // potentially send a GUI error
            // System.out.println("No doors to open in " + room);
        // }
    }
}
