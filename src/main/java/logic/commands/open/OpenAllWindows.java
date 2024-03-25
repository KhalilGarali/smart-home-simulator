package main.java.logic.commands.open;

import java.util.ArrayList;

import main.java.logic.commands.Command;
import main.java.logic.users.Permissions;
import main.java.model.rooms.Room;

public class OpenAllWindows extends Command {
    protected Room room;

    public OpenAllWindows(Room room){
        this.room = room;
        this.REQUIRED_PERMISSIONS = Permissions.WINDOW;
    }

    @Override
    public Permissions requirePermissions(){
        return REQUIRED_PERMISSIONS;
    }

    @Override
    public Boolean execute(){
        // if(room.getHasDoors()){
        // room.setDoorOpen(false);
        // } else {
        // potentially send a GUI error
        // System.out.println("No doors to open in " + room);
        // }
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println(room);
        System.out.print("command done: ");
        room.openAllWindows();
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        outpanel.appendText(toConsole());
        return true;
    }

    @Override
    public String toString(){
        return "open all windows";
    }

    @Override
    public ArrayList<String> toConsole(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Target: " + room.getClass().getSimpleName() + " all windows");
        text.add("Event Type: Open");
        text.add("Event Description: Open All Windows");
        text.add("window1 " + room.getWindow(1).toString());
        text.add("window2 " + room.getWindow(2).toString());
        return text;
    }
}
