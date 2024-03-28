package test;

import main.java.model.rooms.zones.CommonZone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonZoneTest {

    @Test
    public void testGetInstance() {
        CommonZone zone1 = CommonZone.getInstance();
        CommonZone zone2 = CommonZone.getInstance();
        assertEquals(zone1, zone2); // Ensure the same instance is returned
    }

    @Test
    public void testZoneTemperature() {
        CommonZone zone = CommonZone.getInstance();
        assertEquals(25, zone.getZoneTemperature()); // Initial temperature should be 25

        zone.setZoneTemperature(30);
        assertEquals(30, zone.getZoneTemperature()); // Temperature should be set correctly
    }

    @Test
    public void testToString() {
        CommonZone zone = CommonZone.getInstance();
        assertEquals("CommonZone {zoneTemperature=30}", zone.toString()); // Ensure correct string representation
    }

    // Add more tests as needed...
}

