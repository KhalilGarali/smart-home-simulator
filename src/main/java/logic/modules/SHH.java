package main.java.logic.modules;

import main.java.logic.commands.Command;
import main.java.logic.observerPattern.Observable;
import main.java.logic.users.User;
import main.java.model.rooms.Room;

public class SHH extends Module {
    private SHC shc;
    private static SHH shh;
    private SHH(SHC shc){
        this.shc = shc;
    }
    public static SHH getInstance(SHC shc){
        if(shh == null){
            shh = new SHH(shc);
        }
        return shh;
    }
    public void doAction(Command command, Room room){
        shc.moduleAction(command, room);
    }

    public void increaseTemperature(int temperature){
        //directly change temperature
    }

    // temporary implementation, very minial just to show update received automatically
    // upon notification from the room
    @Override
    public void update(Observable o){
        if (o instanceof Room) {
            Room room = (Room) o;
            System.out.println("updated the SHH and the window is now: " + room.getWindow(1).isOpen());
        } else {
        }    
    }
}
