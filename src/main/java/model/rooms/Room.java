
package main.java.model.rooms;

import java.util.ArrayList;
import java.util.List;

import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.lighting.Light;
import main.java.model.openings.*;

/**
 * Abstract class representing a Room that can observe and be observed,
 * following the Observer pattern. It allows for the manipulation of room openings
 * such as windows and doors.
 */

public abstract class Room implements Observable{
    String name;
    //making the assumption that the room can have max 2 openings of the same kind
    public Window window1;
    public Window window2;
    private Door door1;
    private Door door2;
    private Light light1;

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
    public void notifyObserver(){
        for (Observer module: listeningModules){
            module.update(this);
        }
    }
    // END OF THE OBSERVER PATTERN IMPLEMENTATION

    // Setters for the Openings in the Room
    public void setWindow(Window window) {
        if(window1 == null){
            this.window1 = window;   
        } else if(window1 != null && window2 == null){
            this.window2 = window;
        } else {
            System.out.println("can't add more than 2 windows to a room!");
        }
    }
    public void setDoor(Door door) {
        if(door1 == null){
            this.door1 = door;   
        } else if(door1 != null && door2 == null){
            this.door2 = door;
        } else {
            System.out.println("can't add more than 2 doors to a room!");
        }    
    }


    public Window getWindow(int windowNumber){
        if (windowNumber==1){
            return window1;
        }
        return window2;
    }

    public void setLight(Light l){
        this.light1 = l;
    }

    // open and close for All's - will be useful with SHH and SHP
    public void openAllOpenings() {
        System.out.println("Open everything");
        window1.open();
        window2.open();
        door1.open();
        door2.open();
    }
    public void openAllWindows(){
        System.out.println("Open all windows");
        window1.open();
        window2.open();
    }
    public void openAllDoors(){
        System.out.println("Open all doors");
        door1.open();
        door2.open();
    }
    public void closeAllOpenings() {
        System.out.println("Close everything");
        window1.close();
        window2.close();
        door1.close();
        door2.close();
    }
    public void closeAllWindows(){
        System.out.println("Close all windows");
        window1.close();
        window2.close();
    }
    public void closeAllDoors(){
        System.out.println("Close all doors");
        door1.close();
        door2.close();
    }
    
    // single Opening closers and openers
    public void openWindow(int num){
        if (num==1){
            System.out.println("Open window1");
            window1.open();
            notifyObserver();
        }
        if (num==2){
            System.out.println("Open window2");
            window2.open();
            notifyObserver();
        } 
    }
    public void openDoor(int num){
        if (num==1){
            System.out.println("Open door1");
            door1.open();
            notifyObserver();
        }
        if (num==2){
            System.out.println("Open door2");
            door2.open();
            notifyObserver();
        } 
    }
    public void closeWindow(int num){
        if (num==1){
            System.out.println("Close window1");
            window1.close();
            notifyObserver();
        }
        if (num==2){
            System.out.println("Close window2");
            window2.close();
            notifyObserver();
        } 
    }
    public void closeDoor(int num){
        if (num==1){
            System.out.println("Close door1");
            door1.close();
            notifyObserver();
        }
        if (num==2){
            System.out.println("Close door2");
            door2.close();
            notifyObserver();
        } 
    }
    
    //not showing window2 and door2 for now!
    @Override
    public String toString() {
        return " has window1=" + window1 + ", door1=" + door1 + ", light=" + light1;
    }   

    public String getName() {
        return this.name;
    }
    
}
