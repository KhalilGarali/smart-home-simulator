package main.java.model.rooms.zones;

public class GatewayZone extends Zone{
    private static GatewayZone instance = null;

    private GatewayZone(){
        super(19);
    }

    public static GatewayZone getInstance(){
        if(instance == null){
            instance = new GatewayZone();
        }
        return instance;
    }

    public void setZoneTemperature(int zoneTemperature) {
        super.setZoneTemperature(zoneTemperature);
    }

    public int getZoneTemperature() {
        return super.getZoneTemperature();
    }

    @Override
    public String toString() {
        return "GatewayZone {" +
                "zoneTemperature=" + getZoneTemperature() +
                '}';
    }
}
