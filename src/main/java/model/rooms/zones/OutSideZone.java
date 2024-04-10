package main.java.model.rooms.zones;

public class OutSideZone extends Zone{
    private static OutSideZone instance = null;

    private OutSideZone(){
        super(20);
    }

    public static OutSideZone getInstance(){
        if(instance == null){
            instance = new OutSideZone();
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
