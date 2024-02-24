package commands.close;

import commands.Command;
import rooms.Room;

public class CloseWindow implements Command{
    protected Room room;

    public CloseWindow(Room room){
        this.room = room;
    }

    @Override
    public void execute(){
        // if(room.getHasWindows()){
            // room.setWindowOpen(false);
        // } else {
            // potentially send a GUI error
            // System.out.println("No doors to open in " + room);
        // }
    }
}
