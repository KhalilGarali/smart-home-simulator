package main.java.model.lighting;

public class Light {
    private boolean ON = false;

    public void turnON() {
        ON = true;
    }

    public void turnOFF() {
        ON = false;
    }

    public boolean isON() {
        return ON;
    }

    public String toString() {
        return "Light [ON=" + ON + "]";
    } 
}
