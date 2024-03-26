package main.java.model.rooms.zones;

public class BedroomsZone extends Zone{
    
    private static BedroomsZone instance = null;

    private BedroomsZone(){
        super(20);
    }

    public static BedroomsZone getInstance(){
        if(instance == null){
            instance = new BedroomsZone();
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
        return "BedroomsZone {" +
                "zoneTemperature=" + getZoneTemperature() +
                '}';
    }
}
