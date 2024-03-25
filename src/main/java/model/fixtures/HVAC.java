package main.java.model.fixtures;

public class HVAC {

    private boolean heating = false;
    private boolean cooling = false;

    public boolean isHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public boolean isCooling() {
        return cooling;
    }

    public void setCooling(boolean cooling) {
        this.cooling = cooling;
    }

    public String heatingToString(){
        if (heating){
            return "Heating: ON";
        } else {
            return "Heating: OFF";
        }
    }

    public String coolingToString(){
        if (cooling){
            return "Cooling: ON";
        } else {
            return "Cooling: OFF";
        }
    }
}
