package main.java.test;

import main.java.logic.commands.close.CloseAllDoors;
import main.java.logic.users.Permissions;
import main.java.model.fixtures.Light;
import main.java.model.openings.Door;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CloseAllDoorsTest {
    private Room room;
    private CloseAllDoors closeAllDoors;

    @BeforeEach
    public void setUp() {
        Room room = new Kitchen("Test Room");
        Light light = new Light();
        Door door1 = new Door("door1");
        Door door2 = new Door("door2");
        room.setDoor(door1);
        room.setDoor(door2);
        room.setLight(light);
        closeAllDoors = new CloseAllDoors(room);
    }

    @Test
    public void testConstructor() {
        assertEquals(Permissions.DOOR, closeAllDoors.requirePermissions());
    }

    @Test
    public void testExecute() {
        closeAllDoors.execute();
        assertEquals(false, room.getDoor1().isOpen());
    }

    @Test
    public void testToString() {
        assertEquals("close all doors", closeAllDoors.toString());
    }
}
