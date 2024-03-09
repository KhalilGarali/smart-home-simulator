package main.java.logic.commands.open;

import main.java.logic.commands.Command;
import main.java.model.rooms.Room;

public class OpenAWindow implements Command{
    protected Room room;
    protected int windowNumber;

    public OpenAWindow(Room room, int num){
        this.room = room;
        windowNumber = num;
    }

    //how can i make the excecution not depend on which window to open?
    @Override
    public Boolean execute(){
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println(room);
        System.out.print("command done: ");
        room.openWindow(windowNumber);
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        return true;
    }
}
