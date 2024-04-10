package test;

import main.java.model.fixtures.HVAC;
import main.java.model.rooms.Bathroom;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.BathroomsZone;
import main.java.model.rooms.zones.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HVACTest {

    private HVAC hvac;
    private Room room;

    @BeforeEach
    public void setUp() {
        // Initialize a room and HVAC for each test
        Zone bathroomsZone = BathroomsZone.getInstance(); // Room's zone with initial temperature 25
        room = new Bathroom("Test Room"); // Create a room with the zone
        hvac = new HVAC(room);
    }

    @Test
    public void testGetRoom() {
        assertEquals(room, hvac.getRoom());
    }

    @Test
    public void testInitialTemperature() {
        assertEquals(10, hvac.getCurrentRoomTemp());
        assertEquals(10, hvac.getDesiredRoomTemp());
    }

    @Test
    public void testSetDesiredRoomTemp() {
        hvac.setDesiredRoomTemp(20);
        assertEquals(20, hvac.getDesiredRoomTemp());
    }


}

