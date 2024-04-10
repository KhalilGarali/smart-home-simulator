package main.java.model.fixtures;

import main.java.logic.dashboard.DateTime;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.Zone;

import java.lang.Math;

import java.util.ArrayList;
import java.util.Random;

public class HVAC implements Observer, Observable{

    private Room room;
    private Boolean hasPower = true;
    private Boolean heatingOn = false;
    private Boolean coolingOn = false;
    private double currentRoomTemp = 25;
    private double desiredRoomTemp;
    private double seconds = 0;
    private ArrayList<Observer> observers= new ArrayList<>();
    private DateTime dateTime = DateTime.getInstance();
    private int tempOutside = Temperature.getTemperature();
    public boolean onFire = false;
    public boolean tooHot = false;
    public double increasedTemp = 0;

    public HVAC(Room room) {
        this.room = room;
        this.currentRoomTemp = room.getZone().getZoneTemperature();
        this.desiredRoomTemp = room.getZone().getZoneTemperature();
        dateTime.addObserver(this);
    }
    public Room getRoom() {
        return room;
    }  

    public Boolean getHasPower() {
        return hasPower;
    }

    public double getCurrentRoomTemp() {
        return currentRoomTemp;
    }
    public double getDesiredRoomTemp() {
        return desiredRoomTemp;
    }
    public double setDesiredRoomTemp(double desiredRoomTemp) {
        return this.desiredRoomTemp = desiredRoomTemp;

    }
    public void setHasPower(Boolean power) {
        this.hasPower = power;
    }

    public Boolean getHeatingOn() {
        return heatingOn;
    }

    public void setHeatingOn(Boolean heating) {
        this.heatingOn = heating;
    }

    public Boolean getCoolingOn() {
        return coolingOn;
    }

    public void setCoolingOn(Boolean cooling) {
        this.coolingOn = cooling;
    }

    public void reachDesiredTemp() {
        if(!this.hasPower){
            this.hasPower = true;
        }
        this.controlTemperature();
        notifyObservers();
    }

    public void controlTemperature() {
        if(this.currentRoomTemp < this.desiredRoomTemp && (Math.abs(this.currentRoomTemp - this.desiredRoomTemp)) > 0.09){
            this.raiseTemp();
        } else if(this.currentRoomTemp > this.desiredRoomTemp && (Math.abs(this.currentRoomTemp - this.desiredRoomTemp)) > 0.09){
            this.lowerTemp();
        } else {
            this.heatingOn = false;
            this.coolingOn = false;
        }
    }

    public void raiseTemp(){
        this.coolingOn = false;
        this.heatingOn = true;
        // arithmetically increase the temperature
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        if (true) {
            this.increasedTemp += 0.25;
            this.currentRoomTemp += 0.25;

            if (increasedTemp >= 15){
                this.onFire = true;
                notifyObservers();
                this.onFire = false;
                increasedTemp = 0;  
            }
            
        } else {
            this.currentRoomTemp += 0.1;
        }
        if (currentRoomTemp >= 135){
            this.tooHot = true;
            notifyObservers();
            this.tooHot = false;
        }

        System.out.println("Current in "+this.room.getName() + " is "+this.currentRoomTemp);
        System.err.println("Desired in "+this.room.getName() + " is "+this.desiredRoomTemp);
    }

    public void lowerTemp(){
        this.heatingOn = false;
        this.coolingOn = true;
        // arithmetically decrease the temperature
        this.currentRoomTemp -= 0.1;
        System.out.println("Current in "+this.room.getName() + " is "+ this.currentRoomTemp);
        System.err.println("Desired in "+this.room.getName() + " is "+this.desiredRoomTemp);
    }
    @Override
    public void update(Observable o) {
        if (o instanceof Zone){
            Zone zone = (Zone) o;
            System.out.println(room.getClass());
            System.out.println("before observer change " + this.desiredRoomTemp);
            this.desiredRoomTemp = zone.getZoneTemperature();
            System.out.println("after observer change " + this.desiredRoomTemp);
        }
        if(o instanceof DateTime){
            DateTime dateTime = (DateTime) o;
            this.seconds = dateTime.getTotalSecondsElapsed();
            reachDesiredTemp();
        }
    }

    @Override
    public String toString() {
        return "HVAC [room=" + this.getRoom().getName() + "]";
    }

    @Override
    public void notifyObservers() {
        // this notifies the HouseLayoutPanel everytime temperature changes.
        for(Observer observer: observers){
            observer.update(this);
        }
    }
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
}
