package main.java.model.fixtures;

public class Light {
    private  boolean lightON = true ;
    private boolean AutoLightON = true;

    public boolean getAutolight() {
        return AutoLightON;
    }

    public void setAutolightOff() {
        this.AutoLightON = false;
    }
    public void setAutolightOn() {
        this.AutoLightON = true;
    }
    public boolean getLight() {
        return lightON;
    }
    public void setLightOff() {
        this.lightON = false;
    }
    public void setLightOn() {
        this.lightON = true;
    }

}
