package commands.open;

import commands.Command;
import rooms.Room;

public class OpenWindow implements Command{
    protected Room room;

    public OpenWindow(Room room){
        this.room = room;
    }

    @Override
    public void execute(){
        System.out.println("window in " + room +" was open: " + room.window1.isOpen());
        System.out.print("command done: ");
        room.openWindow1();
        System.out.println("window was open: " + room.window1.isOpen());
    }
}
