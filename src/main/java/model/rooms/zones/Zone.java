package main.java.model.rooms.zones;

import java.util.ArrayList;
import java.util.List;

import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.fixtures.HVAC;
import main.java.model.rooms.Room;

public abstract class Zone implements Observable{
    // Zone temperature
    private int zoneTemperature;
    private List<Room> rooms = new ArrayList<>();
    private List<Observer> hvacs = new ArrayList<>();

    // Non-Default Constructor
    public Zone(int zoneTemperature) {
        this.zoneTemperature = zoneTemperature;
    }

    // Getters and Setters
    public int getZoneTemperature() {
        return zoneTemperature;
    }

    public void setZoneTemperature(int zoneTemperature) {
        this.zoneTemperature = zoneTemperature;
        notifyObservers();
    }

    public void addRoomToZone(Room room) {
        //TODO go back to this function when implementing the SHH observer
        // notifyObservers();
        rooms.add(room);
    }

    // toString method
    @Override
    public String toString() {
        return "Zone {" +
                "zoneTemperature=" + zoneTemperature +
                '}';
    }

    // Observer Pattern Methods
    @Override
    public void addObserver(Observer hvac) {
        hvacs.add((HVAC)hvac);
    }

    @Override
    public void removeObserver(Observer hvac) {
        hvacs.remove(hvac);
    }

    @Override
    public void notifyObservers() {
        for (Observer hvac : hvacs) {
            hvac.update(this);
        }
    }
}
