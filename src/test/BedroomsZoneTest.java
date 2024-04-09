package test;

import main.java.model.rooms.zones.BedroomsZone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BedroomsZoneTest {

    @Test
    public void testGetInstance() {
        BedroomsZone zone1 = BedroomsZone.getInstance();
        BedroomsZone zone2 = BedroomsZone.getInstance();
        assertEquals(zone1, zone2); // Ensure the same instance is returned
    }

    @Test
    public void testZoneTemperature() {
        BedroomsZone zone = BedroomsZone.getInstance();
        assertEquals(20, zone.getZoneTemperature()); // Initial temperature should be 20

        zone.setZoneTemperature(25);
        assertEquals(25, zone.getZoneTemperature()); // Temperature should be set correctly
    }

    @Test
    public void testToString() {
        BedroomsZone zone = BedroomsZone.getInstance();
        assertEquals("BedroomsZone {zoneTemperature=25}", zone.toString()); // Ensure correct string representation
    }
}

