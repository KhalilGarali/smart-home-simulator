package main.java.logic.modules;

import main.java.logic.MediatorPattern.Component;
import main.java.logic.MediatorPattern.Mediator;
import main.java.logic.commands.Command;
import main.java.logic.commands.off.TurnCoolingOff;
import main.java.logic.commands.off.TurnHeatingOff;
import main.java.logic.commands.on.TurnCoolingOn;
import main.java.logic.commands.on.TurnHeatingOn;
import main.java.logic.observerPattern.Observable;
import main.java.logic.users.User;
import main.java.model.rooms.Room;

public class SHH extends Module implements Component {
    private SHC shc;
    private static SHH shh;
    public SHS shs; 

    public SHH(SHC shc){
        this.shc = shc;
    }

    public static synchronized SHH getInstance(SHC shc){
        if(shh == null){
            shh = new SHH(shc);
        }
        return shh;
    }
    public void doAction(Command command, Room room){
        shc.moduleAction(command, room);
    }

    // public void regulateTemperature(Room room){
    //     //directly change temperature
    //     if(room.getCurrentTemperature() < room.getDesiredTemp()){
    //         room.turnHeatingOn();
    //     }
    //     if(room.getCurrentTemperature() > room.getDesiredTemp()){
    //         room.turnCoolingOn();
    //     }
    // }

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
    public void setSHS(SHS shs){
        this.shs = shs;
    }
}
