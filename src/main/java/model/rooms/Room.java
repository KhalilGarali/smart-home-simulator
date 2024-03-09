package main.java.model.rooms;

import java.util.ArrayList;
import java.util.List;

import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.openings.*;


public abstract class Room implements Observable{

    public List<Observer> listeningModules = new ArrayList<>();

    @Override
    public void addObserver(Observer m){
        listeningModules.add(m);
    }
    @Override
    public void removeObserver(Observer m){
        listeningModules.remove(m);
    }
    @Override
    public void notifyObserver(){
        for (Observer module: listeningModules){
            module.update(this);
        }
    }

    // we can use it if we decide to have more openings in a room
    // private List<Opening> openings = new ArrayList<>();

    //making the assumption that the room can have max 2 openings of the same kind
    public Window window1;
    public Window window2;
    private Door door1;
    private Door door2;

    //default constructor
    public Room(){
    } 

    // opening setters
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
            System.out.println("can't add more than 2 windows to a room!");
        }    
    }
    public Window getWindow1(){
        return this.window1;
    }

    //All openers and closers - will be useful with SHH and SHP
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
    
    // single opening closers and openers
    public void openWindow(int num){
        if (num==1){
            System.out.println("Open window1");
            window1.open();
            notifyObserver();
        }
        if (num==2){
            System.out.println("Open window2");
            window2.open();
        } 
    }
    public void openDoor1(){
        System.out.println("Open door1");
        door1.open();
    }
    public void openDoor2(){
        System.out.println("Open door2");
        door2.open();
    }
    public void closeWindow1(){
        System.out.println("Close window1");
        window1.close();
    }
    public void closeWindow2(){
        System.out.println("Close window2");
        window2.close();
    }
    public void closeDoor1(){
        System.out.println("Close door1");
        door1.close();
    }
    public void closeDoor2(){
        System.out.println("Close door2");
        door2.close();
    }
    
    @Override
    public String toString() {
        return " [window1=" + window1 + ", window2=" + window2 + ", door1=" + door1 + ", door2=" + door2 + "]";
    }   
    
}
