package main.java.model.fixtures;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;

public class Temperature implements Observable{

    private static int temperature; // Temperature variable
    private ArrayList<Observer> observers = new ArrayList<Observer>(); // List of observers
    public static Temperature instance = new Temperature();
    
    public static int getTemperature() {
        return temperature;
    }
    public static Temperature getInstance() {
        return instance;
    }

    public static void setTemperature(int temperature) {
        Temperature.temperature = temperature;
        instance.notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add((Observer) o);
    }
    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
