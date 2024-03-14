package main.java.test;
import main.java.logic.observerPattern.Observer;
import main.java.logic.users.Parent;
import main.java.logic.users.User;
import main.java.model.fixtures.Light;
import main.java.model.openings.Door;
import main.java.model.openings.Window;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomTest {
    private Room room;

    @Before
    public void setUp() {
        room = new Kitchen("Test Kitchen"); // Using Kitchen as a concrete implementation of Room
    }

    @Test
    public void testSetName() {
        room.setName("New Name");
        assertEquals("New Name", room.getName());
    }

    @Test
    public void testSetWindow() {
        Window window = new Window("Window");
        room.setWindow(window);
        assertEquals(window, room.getWindow(1));
    }

    @Test
    public void testSetDoor() {
        Door door = new Door("Door");
        room.setDoor(door);
        assertEquals(door, room.getDoor1());
    }

    @Test
    public void testSetCurrentTemperature() {
        room.setCurrentTemperature(25);
        assertEquals(25, room.getCurrentTemperature());
    }

    @Test
    public void testSetLight() {
        Light light = new Light();
        room.setLight(light);
        assertEquals(light, room.getLight());
    }

    @Test
    public void testSetDesiredTemperature() {
        room.setDesiredTemperature(20);
        assertEquals(20, room.getDesiredTemp());
    }

    @Test
    public void testOpenAllOpenings() {
        // Implement this test based on the behavior of openAllOpenings method
    }

    @Test
    public void testOpenAllWindows() {
        // Implement this test based on the behavior of openAllWindows method
    }

    @Test
    public void testOpenAllDoors() {
        // Implement this test based on the behavior of openAllDoors method
    }

    @Test
    public void testCloseAllOpenings() {
        // Implement this test based on the behavior of closeAllOpenings method
    }

    @Test
    public void testCloseAllWindows() {
        // Implement this test based on the behavior of closeAllWindows method
    }

    @Test
    public void testCloseAllDoors() {
        // Implement this test based on the behavior of closeAllDoors method
    }

    @Test
    public void testOpenWindow() {
        // Implement this test based on the behavior of openWindow method
    }

    @Test
    public void testOpenDoor() {
        // Implement this test based on the behavior of openDoor method
    }

    @Test
    public void testCloseWindow() {
        // Implement this test based on the behavior of closeWindow method
    }

    @Test
    public void testCloseDoor() {
        // Implement this test based on the behavior of closeDoor method
    }

    @Test
    public void testTurnLightOn() {
        // Implement this test based on the behavior of turnLightOn method
    }

    @Test
    public void testTurnLightOff() {
        // Implement this test based on the behavior of turnLightOff method
    }

    @Test
    public void testTurnAutoLightOn() {
        // Implement this test based on the behavior of turnAutoLightOn method
    }

    @Test
    public void testTurnAutoLightOff() {
        // Implement this test based on the behavior of turnAutoLightOff method
    }

    @Test
    public void testSetTemperature() {
        room.setTemperature(22);
        assertEquals(22, room.getCurrentTemperature());
    }

    @Test
    public void testTurnHeatingOn() {
        // Implement this test based on the behavior of turnHeatingOn method
    }

    @Test
    public void testTurnHeatingOff() {
        // Implement this test based on the behavior of turnHeatingOff method
    }

    @Test
    public void testTurnCoolingOn() {
        // Implement this test based on the behavior of turnCoolingOn method
    }

    @Test
    public void testTurnCoolingOff() {
        // Implement this test based on the behavior of turnCoolingOff method
    }

    @Test
    public void testAddUserToRoom() {
        User user = new Parent("John");
        room.addUserToRoom(user);
        assertTrue(room.getUserFromRoom().contains(user));
    }

    @Test
    public void testRemoveUserFromRoom() {
        User user = new Parent("John");
        room.addUserToRoom(user);
        room.removeUserFromRoom(user);
        assertFalse(room.getUserFromRoom().contains(user));
    }
}
