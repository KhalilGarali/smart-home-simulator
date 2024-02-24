package commands.close;

import commands.Command;
import rooms.Room;

public class CloseDoor implements Command{
    protected Room room;

    public CloseDoor(Room room){
        this.room = room;
    }

    @Override
    public void execute(){
        // if(room.getHasDoors()){
            // room.setDoorOpen(false);
        // } else {
            // potentially send a GUI error
            // System.out.println("No doors to open in " + room);
        // }
    }
}
