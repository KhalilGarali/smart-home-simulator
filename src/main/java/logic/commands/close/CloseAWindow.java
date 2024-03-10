package main.java.logic.commands.close;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class CloseAWindow implements Command{
    protected Room room;

    public CloseAWindow(Room room){
        this.room = room;
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
