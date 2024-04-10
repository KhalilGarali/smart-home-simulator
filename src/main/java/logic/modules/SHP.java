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
import main.java.model.fixtures.HVAC;
import main.java.model.fixtures.Temperature;
import main.java.model.rooms.Room;

import javax.swing.*;

// Main class for the Smart Home Security module (SHP), 
// implements Component and Observable for the observer pattern.
// FIXME must also implement the observer pattern to observe the rooms
public class SHP extends Module implements Component, Observable{

    // System's date and time handler
    private DateTime dateTime = DateTime.getInstance();

    // References to other modules
    private SHC shc;
    private static SHP shp;
    private SHS shs;

    // Home layout and status
    private House house = House.getInstance();
    private boolean isAway = false;
    private ArrayList<HVAC> hvacs = House.getInstance().getHVACs();
    private int policeTimer = 0;
    private int startTime = 0;

    private ArrayList<Observer> observers = new ArrayList<>();

    // Private constructor for singleton pattern
    private SHP(SHC ashc){
        this.shc = ashc;
        for (HVAC hvac : hvacs) {
            hvac.addObserver(this);
        }
        for(Room room: house.getRooms()){
            room.addObserver(this);
        }
        dateTime.addObserver(this);
    }

    // Singleton method to get instance of SHP
    public static synchronized SHP getInstance(SHC ashc){
        if(shp == null){
            shp = new SHP(ashc);
        }
        return shp;
    }

    // Perform actions via the SHC module
    public void doAction(Command command){
        shc.moduleAction(command);
    }


    @Override
    public void update(Observable o){
        if(o instanceof Room) {
             startTime = dateTime.getHour() * 3600 + dateTime.getMinute() * 60 + dateTime.getSecond();
            System.out.println("str " + startTime + " : "+ ((Room) o).getName());
        }

        if(o instanceof DateTime){
            System.out.println("DateTime" + dateTime.getHour() + " " + dateTime.getMinute() + " " + dateTime.getSecond());
            dateTime = (DateTime) o;

            if(startTime != 0 && ((dateTime.getHour() * 3600 + dateTime.getMinute() * 60 + dateTime.getSecond()) - startTime >= policeTimer) && policeTimer != 0) {
                JOptionPane.showMessageDialog(null, "*Police is on their way*");
                startTime = 0;
            }
        }
        if(o instanceof HVAC){
            HVAC hvac = (HVAC) o;
            if(hvac.onFire || hvac.tooHot){
                this.isAway = false;
                System.out.println("HVAC is on fire or too hot");
            }
        }

    }

    // Set the timer after which police notification is sent
    public void setPoliceTimer(int time){
        this.policeTimer = time;
        System.out.println("Police Timer: " + policeTimer);
    }

    // Notify that house is empty
    public void houseIsEmpty() {
        shs.notify(this, "houseIsEmpty");
    }

    // Notify that house is not empty
    public void houseIsNotEmpty() {
        shs.notify(this, "houseIsNotEmpty");
    }

    // Set reference to Smart Home Simulation module
    public void setSHS(SHS shs){
        this.shs = shs;
    }

    // Set away status and trigger notifications and motion detectors accordingly
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
        } else {
            shs.notify(this, "HouseIsNotEmpty");
        }

    }

    // Return away status
    public boolean getIsAway() {
        return this.isAway;
    }

    // Add an observer to the list
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    // Remove an observer from the list
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // Notify all observers about a change
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
