package test;

import main.java.model.rooms.zones.BathroomsZone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BathroomsZoneTest {

    @Test
    public void testGetInstance() {
        BathroomsZone zone1 = BathroomsZone.getInstance();
        BathroomsZone zone2 = BathroomsZone.getInstance();
        assertEquals(zone1, zone2); // Ensure the same instance is returned
    }

    @Test
    public void testZoneTemperature() {
        BathroomsZone zone = BathroomsZone.getInstance();
        assertEquals(24, zone.getZoneTemperature()); // Initial temperature should be 24

        zone.setZoneTemperature(28);
        assertEquals(28, zone.getZoneTemperature()); // Temperature should be set correctly
    }

    @Test
    public void testToString() {
        BathroomsZone zone = BathroomsZone.getInstance();
        assertEquals("BathroomsZone {zoneTemperature=28}", zone.toString()); // Ensure correct string representation
    }
}

