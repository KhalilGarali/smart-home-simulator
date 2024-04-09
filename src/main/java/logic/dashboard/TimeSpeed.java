package main.java.logic.dashboard;

public class TimeSpeed {
    private int speed; // Speed of time increment in seconds
    private static TimeSpeed instance;
    private TimeSpeed() {
        this.speed = 1; // Default speed
    }

    public static TimeSpeed getInstance() {
        if(instance == null)
            new TimeSpeed();
        return instance;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int calculateIncrement() {
        // Calculate time increment based on speed
        return speed;
    }
}
