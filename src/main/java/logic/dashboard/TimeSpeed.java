package main.java.logic.dashboard;

public class TimeSpeed {
    private int speed; // Speed of time increment in seconds

    public TimeSpeed() {
        this.speed = 1; // Default speed
    }

    // method to set speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // return speed
    public int getSpeed() {
        return speed;
    }

    public int calculateIncrement() {
        // Calculate time increment based on speed
        return speed;
    }
}
