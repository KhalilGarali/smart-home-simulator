package main.java.model.fixtures;

import java.util.Timer;
import java.util.TimerTask;

public class Temperature {

    private int temperature; // Temperature variable

    public Temperature(int initialTemperature) {
        this.temperature = initialTemperature; // Initialize temperature
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
