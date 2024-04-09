package main.java.model.rooms.zones;

public class CommonZone extends Zone{
    
    private static CommonZone instance = null;

    private CommonZone(){
        super(30);
    }

    public static CommonZone getInstance(){
        if(instance == null){
            instance = new CommonZone();
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
        return "CommonZone {" +
                "zoneTemperature=" + getZoneTemperature() +
                '}';
    }
}
