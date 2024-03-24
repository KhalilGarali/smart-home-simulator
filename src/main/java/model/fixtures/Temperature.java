package main.java.model.fixtures;

import java.util.Timer;
import java.util.TimerTask;

public class Temperature {

    private static int temperature; // Temperature variable

    public static int getTemperature() {
        return temperature;
    }

    public static void setTemperature(int temperature) {
        Temperature.temperature = temperature;
    }
}
