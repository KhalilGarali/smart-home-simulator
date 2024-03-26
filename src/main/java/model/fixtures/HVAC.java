package main.java.model.fixtures;

import main.java.logic.dashboard.DateTime;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.Zone;

public class HVAC implements Observer{

    private Room room;
    private Boolean hasPower = true;
    private Boolean heatingOn = false;
    private Boolean coolingOn = false;
    private int currentRoomTemp = 25;
    private int desiredRoomTemp;
    private int seconds = 0;

    private DateTime dateTime = DateTime.getInstance();

    public HVAC(Room room) {
        this.room = room;
        this.desiredRoomTemp = room.getZone().getZoneTemperature();
        dateTime.addObserver(this);
    }
    public Room getRoom() {
        return room;
    }  

    public Boolean getHasPower() {
        return hasPower;
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

    // TODO this is what's to be added in the udpate function for HVAC
    public void reachDesiredTemp() {
        if(!this.hasPower){
            this.hasPower = true;
        }
        this.controlTemperature();
    }

    public void controlTemperature() {
        //TODO check the 0.25 for maintaining the temperature
        if(this.currentRoomTemp < this.desiredRoomTemp){
            this.raiseTemp();
        } else if(this.currentRoomTemp > this.desiredRoomTemp){
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
        this.currentRoomTemp += 0.1;

    }

    public void lowerTemp(){
        this.heatingOn = false;
        this.coolingOn = true;
        // arithmetically decrease the temperature
        this.currentRoomTemp -= 0.1;

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
            this.seconds = dateTime.getTotalMinutesIncremented();
            System.out.println("hour: " + this.seconds);
        }
    }

    @Override
    public String toString() {
        return "HVAC [room=" + this.getRoom().getName() + "]";
    }
}
