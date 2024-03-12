package main.java.model.fixtures;

import java.util.Timer;
import java.util.TimerTask;

public class Temperature {

    private int temperature = 10; // Initial temperature

    public Temperature() {
        // Schedule a task to update temperature every 5 minutes
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Update temperature
                temperature += 1; // Example: increase temperature by 1 every 5 minutes
                // Notify listeners about the change in temperature (if required)
            }
        }, 0, 5 * 60 * 1000); // Update every 5 minutes
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
