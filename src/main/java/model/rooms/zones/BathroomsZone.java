package main.java.model.rooms.zones;

public class BathroomsZone extends Zone{
    
    private static BathroomsZone instance = null;
    
    private BathroomsZone(){
        super(24);
    }

    public static BathroomsZone getInstance(){
        if(instance == null){
            instance = new BathroomsZone();
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
        return "BathroomsZone {" +
                "zoneTemperature=" + getZoneTemperature() +
                '}';
    }
}
