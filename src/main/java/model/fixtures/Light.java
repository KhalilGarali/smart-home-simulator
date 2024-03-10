package main.java.model.fixtures;

public class Light {
    private LightState light = LightState.OFF;
    private LightState Autolight = LightState.OFF;

    public LightState getAutolight() {
        return Autolight;
    }

    public void setAutolight(LightState autolight) {
        Autolight = autolight;
    }

    public LightState getLight() {
        return light;
    }

    public void setLight(LightState light) {
        this.light = light;
    }

}
