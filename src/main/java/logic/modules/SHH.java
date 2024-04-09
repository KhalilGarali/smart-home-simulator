package main.java.logic.modules;

import java.util.ArrayList;

import java.util.ArrayList;
import main.java.logic.MediatorPattern.Component;
import main.java.logic.MediatorPattern.Mediator;
import main.java.logic.commands.Command;
import main.java.logic.commands.CommandFactory;
import main.java.logic.commands.off.TurnCoolingOff;
import main.java.logic.commands.off.TurnHeatingOff;
import main.java.logic.commands.on.TurnCoolingOn;
import main.java.logic.commands.on.TurnHeatingOn;
import main.java.logic.layout.House;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.logic.users.User;
import main.java.model.fixtures.HVAC;
import main.java.model.fixtures.Temperature;
import main.java.model.rooms.Room;

public class SHH extends Module implements Component {
    private SHC shc;
    private static SHH shh;
    private SHH(SHC shc){
        this.shc = shc;
    }
    public static SHH getInstance(SHC shc){
        if(shh == null){
            shh = new SHH();
        }
        return shh;
    }
    public void doAction(Command command, Room room){
        shc.moduleAction(command);
    }

    @Override
    public void update(Observable o){
        if (o instanceof Room) {
            Room room = (Room) o;
        } 
        // monitor the temperature of the rooms
        if (o instanceof HVAC) {
            HVAC hvac = (HVAC) o;
            System.out.println("updated the SHH and the HVAC is now: " + hvac.getHeatingOn() + " in room: " + hvac.getRoom().getName() + "from " + hvac.getCurrentRoomTemp() + " to " + hvac.getDesiredRoomTemp());
            monitorTemp();
        }  
        // monitor the temperature outside 
        if (o instanceof Temperature) {
            Temperature temp = (Temperature) o;
            tempOutside = temp;
            System.out.println("updated the SHH and the Temperature is now: " + tempOutside.getTemperature() + " outside");
            monitorTemp();
        }
    }

    public void monitorTemp(){
        if (!isAway) {
            for (HVAC hvac : hvacs) {
                if (tempOutside.getTemperature() <= hvac.getCurrentRoomTemp()  && !hvac.getRoom().getWindow(1).isOpen() ) {
                    shc.moduleAction(cf.createCommand("openawindow", hvac.getRoom(),0));
                } else if (tempOutside.getTemperature() > hvac.getCurrentRoomTemp() && hvac.getRoom().getWindow(1).isOpen()) {
                    shc.moduleAction(cf.createCommand("closeawindow", hvac.getRoom(),0));
                }
            }
        }
        
    }

    // to be called by the mediator class
    public void updateHouseStatus(Boolean isAway){
        this.isAway = isAway;
    }
    public void setSHS(SHS shs){
        this.shs = shs;
    }
    public void setSHS(SHS shs){
        this.shs = shs;
    }
}
