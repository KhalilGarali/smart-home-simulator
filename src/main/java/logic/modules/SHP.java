package main.java.logic.modules;

import java.awt.List;
import java.util.ArrayList;

import main.java.logic.MediatorPattern.Component;
import main.java.logic.commands.Command;
import main.java.logic.layout.House;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.fixtures.HVAC;
import main.java.model.fixtures.Temperature;
import main.java.model.rooms.Room;

// FIXME must also implement the observer pattern to observe the rooms
public class SHP extends Module implements Component, Observable{

    private SHC shc;
    private static SHP shp;
    private SHS shs;
    private House house = House.getInstance();
    private boolean isAway = false;
    private ArrayList<HVAC> hvacs = House.getInstance().getHVACs();
    private ArrayList<Observer> observers = new ArrayList<>();

    private SHP(SHC ashc){
        this.shc = ashc;
        for (HVAC hvac : hvacs) {
            hvac.addObserver(this);
        }
    }

    public static synchronized SHP getInstance(SHC ashc){
        if(shp == null){
            shp = new SHP(ashc);
        }
        return shp;
    }
    public void doAction(Command command){
        shc.moduleAction(command);
    }

    public void houseIsEmpty() {
        shs.notify(this, "houseIsEmpty");
    }

    public void houseIsNotEmpty() {
        shs.notify(this, "houseIsNotEmpty");
    }

    public void setSHS(SHS shs){
        this.shs = shs;
    }

    public void setIsAway(boolean isAway) {
        this.isAway = isAway;
        notifyObservers();

        if (this.isAway == true) {
            shs.notify(this, "HouseIsEmpty");
        }
    }

    public boolean getIsAway() {
        return this.isAway;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }
    @Override
    public void update(Observable o){
        System.err.println("SHP is updated");

        // monitor the temperature of the rooms
        if (o instanceof HVAC) {
            HVAC hvac = (HVAC) o;
        }
    }

}
