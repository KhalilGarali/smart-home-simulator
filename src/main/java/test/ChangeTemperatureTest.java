package main.java.test;

import main.java.logic.commands.change.ChangeTemperature;
import main.java.model.fixtures.Light;
import main.java.model.rooms.Room;
import main.java.model.rooms.Kitchen;
import main.java.logic.users.Permissions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangeTemperatureTest {
    private ChangeTemperature changeTemperature;
    private Room room;


    @BeforeEach
    void setUp() {
        Light light = new Light();
        room = new Kitchen("Test Kitchen");
        room.setLight(light);
        changeTemperature = new ChangeTemperature(room, 25);
    }

    @Test
    void testGetRoom() {
        assertEquals(room, changeTemperature.getRoom());
    }

    @Test
    void testGetTemperature() {
        assertEquals(25, changeTemperature.getTemperature());
    }

    @Test
    void testExecute() {
        assertTrue(changeTemperature.execute());
        assertEquals(25, room.getCurrentTemperature());
    }

    @Test
    void testToString() {
        assertEquals("change temperature", changeTemperature.toString());
    }
}

