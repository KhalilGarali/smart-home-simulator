package main.java.logic.modules;

import main.java.logic.observerPattern.Observable;
import main.java.model.rooms.Room;

public class SHH extends Module {
    @Override
    public void update(Observable o){
        if (o instanceof Room) {
            Room room = (Room) o;
            System.out.println("updated the SHH and the window is now: " + room.getWindow1().isOpen());
        } else {
        }    
    }
}
