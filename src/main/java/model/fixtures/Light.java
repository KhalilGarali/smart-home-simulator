package main.java.model.fixtures;

public class Light {
    private  boolean lightON = false ;
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

    public String lightToSTring(){
        if (lightON){
            return "Light: ON";
        }
        else{
            return "Light: OFF";
        }
    }

    public String autoLightToString(){

        if (AutoLightON){
            return "Auto Light: ON";
        }
        else {
            return "Auto Light: OFF";
        }
    }

}
