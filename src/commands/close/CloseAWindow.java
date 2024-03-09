package commands.close;

import commands.Command;
import rooms.Room;

public class CloseAWindow implements Command{
    protected Room room;

    public CloseAWindow(Room room){
        this.room = room;
    }

    @Override
    public Boolean execute(){
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println(room);
        System.out.print("command done: ");
        room.closeWindow1();
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        return true;
    }
}
