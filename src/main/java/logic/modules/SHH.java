package main.java.logic.modules;

import main.java.logic.observerPattern.Observable;
import main.java.model.rooms.Room;

public class SHH extends Module {

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
