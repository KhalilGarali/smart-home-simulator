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

    // attributes
    private SHC shc;
    private SHS shs;
    private static SHH shh;
    private SHP shp;
    private ArrayList<HVAC> hvacs = House.getInstance().getHVACs();
    private Temperature tempOutside = Temperature.getInstance();
    private CommandFactory cf;
    private Boolean isAway = false;

    // private constructor for the Singleton pattern
    private SHH(SHC shc){
        this.shc = shc;
        this.shp = SHP.getInstance(shc);
        this.shp.addObserver(this);
        cf = new CommandFactory(shc);
        for (HVAC hvac : hvacs) {
            hvac.addObserver(this);
        }
        if (tempOutside != null) {
            tempOutside.addObserver(this);
        }
    }

    // getInstance method return SHH instance
    public static SHH getInstance(SHC shc){
        if(shh == null){
            shh = new SHH(shc);
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
            monitorTemp();
        }  
        // monitor the temperature outside 
        if (o instanceof Temperature) {
            Temperature temp = (Temperature) o;
            tempOutside = temp;
            System.out.println("updated the SHH and the Temperature is now: " + tempOutside.getTemperature() + " outside");
            monitorTemp();
        }

        if (o instanceof SHP) {
            this.isAway = ((SHP)o).getIsAway();
            // System.out.println("this.isAway in SHH: " + this.isAway);
        }
    }

    // method to monitor temperature
    public void monitorTemp(){
        if (!isAway) {
            for (HVAC hvac : hvacs) {
                if (tempOutside.getTemperature() <= hvac.getCurrentRoomTemp()  && !hvac.getRoom().getWindow(1).isOpen()) {
                    System.out.println("the temp check is reached");
                    shc.moduleAction(cf.createCommand("openawindow", hvac.getRoom(),0));
                } else if (tempOutside.getTemperature() > hvac.getCurrentRoomTemp() && hvac.getRoom().getWindow(1).isOpen()) {
                    System.out.println("the temp check is reached");
                    shc.moduleAction(cf.createCommand("closeawindow", hvac.getRoom(),0));
                }
            }
        }
        
    }

    // to be called by the mediator class
    public void updateHouseStatus(Boolean isAway){
        this.isAway = isAway;
    }

    // method to set SHS
    public void setSHS(SHS shs){
        this.shs = shs;
    }

    // overriding the toString method
    @Override
    public String toString() {
        return "SHH";
    }
}
