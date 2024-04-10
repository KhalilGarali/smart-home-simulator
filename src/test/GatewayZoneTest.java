package test;

import main.java.model.rooms.zones.GatewayZone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GatewayZoneTest {

    @Test
    public void testGetInstance() {
        GatewayZone zone1 = GatewayZone.getInstance();
        GatewayZone zone2 = GatewayZone.getInstance();
        assertEquals(zone1, zone2); // Ensure the same instance is returned
    }

    @Test
    public void testZoneTemperature() {
        GatewayZone zone = GatewayZone.getInstance();
        assertEquals(0, zone.getZoneTemperature()); // Initial temperature should be 19

        zone.setZoneTemperature(20);
        assertEquals(20, zone.getZoneTemperature()); // Temperature should be set correctly
    }

    @Test
    public void testToString() {
        GatewayZone zone = GatewayZone.getInstance();
        assertEquals("GatewayZone {zoneTemperature=20}", zone.toString()); // Ensure correct string representation
    }
}

