package main.java.logic.commands.close;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class CloseAWindow extends Command{
    protected Room room;
    protected int windowNumber;

    public CloseAWindow(Room room, int num){
        this.room = room;
        this.windowNumber = num;
        this.REQUIRED_PERMISSIONS = Permissions.WINDOW;
    }

    @Override
    public Permissions requirePermissions(){
        return REQUIRED_PERMISSIONS;
    }

    @Override
    public Boolean execute(){
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println(room);
        System.out.print("command done: ");
        room.closeWindow(1);
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        return true;
    }
    @Override
    public String toString(){
        return "open a door";
    }
}
