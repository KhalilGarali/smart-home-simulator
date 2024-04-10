package main.java.logic.modules;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import main.java.logic.MediatorPattern.Component;
import main.java.logic.commands.Command;
import main.java.logic.dashboard.DateTime;
import main.java.logic.layout.House;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.rooms.Room;

// FIXME must also implement the observer pattern to observe the rooms
public class SHP extends Module implements Component, Observable{

    private DateTime dateTime = DateTime.getInstance();

    private SHC shc;
    private static SHP shp;
    private SHS shs;
    private House house = House.getInstance();
    private boolean isAway = false;
    private int policeTimer = 0;


    private ArrayList<Observer> observers = new ArrayList<>();

    private SHP(SHC ashc){
        this.shc = ashc;
        for(Room room: house.getRooms()){
            room.addObserver(this);
        }
        dateTime.addObserver(this);
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

    @Override
    public void update(Observable o){
        if(o instanceof Room){
            int startTime = dateTime.getHour() * 3600 + dateTime.getMinute() * 60 + dateTime.getSeconds();
            Room room = (Room) o;

            System.out.println("POLICE CALLED");
        }
        if(o instanceof DateTime){
            dateTime = (DateTime) o;
        }
    }

    public void setPoliceTimer(int time){
        this.policeTimer = time;
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

        if(!this.isAway){
            for(Room room: house.getRooms()){
                room.setActiveMotionDetector(false);
            }
        }
        if (this.isAway) {
            for(Room room: house.getRooms()){
                if(room.getMotionDetector())
                    room.setActiveMotionDetector(true);
            }
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

    public void addMotionDetector(Room room) {
        room.setMotionDetector(true);
    }
    public void removeMotionDetector(Room room) {
        room.setMotionDetector(false);
    }
}
