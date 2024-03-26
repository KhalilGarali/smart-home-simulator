
package main.java.model.rooms;

import java.util.ArrayList;
import java.util.List;

import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
// import main.java.model.lighting.Light;
import main.java.logic.users.User;
import main.java.model.fixtures.HVAC;
import main.java.model.fixtures.Light;
import main.java.model.fixtures.Temperature;
import main.java.model.openings.*;
import main.java.model.rooms.zones.Zone;

/**
 * Abstract class representing a Room that can observe and be observed,
 * following the Observer pattern. It allows for the manipulation of room openings
 * such as windows and doors.
 */

public abstract class Room implements Observable, Observer{
    // String name;
    //making the assumption that the room can have max 2 openings of the same kind
    public Window window1;
    private Door door1;
    private Light light;
    private int currentTemperature = 25;
    private int lightSensor;
    private HVAC hvac;
    protected String name;
    public Zone zone;

    protected List<User> usersInThisRoomList = new ArrayList<>();

    //default constructor
    public Room(String name){
        this.name = name;
    } 

    // START OF THE OBSERVER PATTERN IMPLEMENTATION
    // List of Observer objects that are monitoring changes in this Room
    public List<Observer> listeningModules = new ArrayList<>();

    /**
     * Adds an observer to the list of observers listening for changes to the room.
     * @param m The observer to add.
     */
    @Override
    public void addObserver(Observer m){
        listeningModules.add(m);
    }
    /**
     * Removes an observer from the list of observers.
     * @param m The observer to remove.
     */
    @Override
    public void removeObserver(Observer m){
        listeningModules.remove(m);
    }
    /**
     * Notifies all observers about a change in the room's state.
     */
    @Override
    public void notifyObservers(){
        for (Observer module: listeningModules){
            module.update(this);
        }
    }
    // END OF THE OBSERVER PATTERN IMPLEMENTATION

    public void setName(String name){
        this.name = name;
    }
    // Setters for the Openings in the Room
    public void setWindow(Window window) {
        this.window1 = window;   
    }
    public void setDoor(Door door) {
            this.door1 = door;   
    }

    public void setCurrentTemperature(int temperature){
        currentTemperature = temperature;
    }

    public HVAC getHvac(){
        return this.hvac;
    }

    public void installHVAC(HVAC hvac){
        this.hvac = hvac;
        this.zone.addObserver(hvac);
    }

    public Window getWindow(int windowNumber){
        if (windowNumber==1){
            return window1;
        }
        return window1;
    }

    public double getTemperature(){
        return this.hvac.getCurrentRoomTemp();
    }
    
    public Door getDoor(int doorNumber){
        if (doorNumber==1){
            return door1;
        }
        return door1;
    }

    public void setLight(Light light){
        this.light = light;
    }
    public Door getDoor1() {
        return door1;
    }
    
    public Light getLight() {
        return light;
    }
    public int getCurrentTemperature() {
        return currentTemperature;
    }
    public double getDesiredTemp() {
        return this.hvac.getDesiredRoomTemp();
    }
    public int getLightSensor() {
        return lightSensor;
    }
    public String getName() {
        return name;
    }

    // open and close for All's - will be useful with SHH and SHP
    public void openAllOpenings() {
        System.out.println("Open everything");
        window1.open();
        door1.open();
    }
    
    public void closeAllOpenings() {
        System.out.println("Close everything");
        window1.close();
        door1.close();
    }

    // single Opening closers and openers
    public void openWindow(int num){
            System.out.println("Open window1");
            window1.open();
            notifyObservers();
    }
    public void openDoor(int num){
            System.out.println("Open door1");
            door1.open();
            notifyObservers();
    }
    public void closeWindow(int num){
            System.out.println("Close window1");
            window1.close();
            notifyObservers();
        
    }
    public void closeDoor(int num){
            System.out.println("Close door1");
            door1.close();
            notifyObservers();
        
    }
    public void turnLightOn(){
        if(!light.getLight() && light.getAutolight()){
            System.out.println("turning light on in : " + this.getName());
            light.setLightOn();
            notifyObservers();
        }
    }
    public void turnLightOff(){
        if(!usersInThisRoomList.isEmpty() && light.getAutolight()){
            System.out.println("cannot turn light off, someone is still in: " + this.getName());
        }
        else{
            System.out.println("turning light off");
            light.setLightOff();
            notifyObservers();
        }
    }
    public void turnAutoLightOn(){
        System.out.println("turning auto light on");
        light.setAutolightOn();
        notifyObservers();
    }
    public void turnAutoLightOff(){
        System.out.println("turning light on");
        light.setAutolightOff();
        notifyObservers();
    }
    public void setTemperature(int temperature){
        System.out.println("setting temperature to: "+temperature);
        setCurrentTemperature(temperature);
        notifyObservers();
    }
    //TODO worry about this when the user set the temperature
    // public void setDesiredTemperature(int temperature){
    //     System.out.println("setting desired temperature to: " + temperature);
    //     desiredTemp = temperature;
    //     notifyObservers();
    // }
    
    //TODO worry about this when the user set the temperature
    // public void turnHeatingOn() {
    //     turnCoolingOff();
    //     System.out.println("Turning On Heating : ");
    //     // hvac.setHeating(true);
    //     setTemperature(this.desiredTemp);
    //     System.out.println("temperature is now: " + getCurrentTemperature() );
    //     //set the temp object to something higher over time
    //     notifyObservers();
    // }
    
    //TODO worry about this when the user set the temperature
    // public void turnHeatingOff(){
    //     System.out.println("Turning Off Heating : ");
    //     hvac.setHeating(false);
    //     notifyObservers();
    // }

    //TODO worry about this when the user set the temperature
    // public void turnCoolingOn(){
    //     turnCoolingOff();
    //     System.out.println("Turning On Cooling : ");
    //     // hvac.setCooling(true);
    //     setTemperature(this.desiredTemp);
    //     System.out.println("temperature is now: " + getCurrentTemperature() );
    //     notifyObservers();
    // }

    public void turnCoolingOff(){
        System.out.println("Turning Off Cooling : ");
        // hvac.setCooling(false);
        notifyObservers();
    }

    public void addUserToRoom(User user){
        usersInThisRoomList.add(user);
    }

    public void removeUserFromRoom(User user){
        try {
            usersInThisRoomList.remove(user);
        }catch(Exception ignored){
            System.out.println("user isn't in: " + this.getName());
        }
    }

    public List<User> getUserFromRoom(){
        return usersInThisRoomList;
    }

    public void update(Observable o){
        this.zone = (Zone)o;
    }

    public void setZone(Zone zone){
        this.zone = zone;
    }

    public Zone getZone(){
        return zone;
    }
    
    //not showing window2 and door2 for now!
    @Override
    public String toString() {
        return " has window= " + window1 +
                ", door= " + door1 +
                ", light= " + light.getLight() +
                ", current temp= " + getCurrentTemperature() +
                ", desired temp= " + getDesiredTemp() +
                ", autoLight= " + light.getAutolight() +
                ", zone= " + zone +
                ", hvac= " + hvac;
    }   
    
}
