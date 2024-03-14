package main.java.test;

import main.java.logic.commands.close.CloseAllDoors;
import main.java.logic.commands.close.CloseAllOpenings;
import main.java.logic.users.Permissions;
import main.java.model.fixtures.Light;
import main.java.model.openings.Door;
import main.java.model.openings.Window;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CloseAllOpeningsTest {
    private Room room;
    private CloseAllOpenings closeAllOpenings;

    @BeforeEach
    public void setUp() {
        Room room = new Kitchen("Test Room");
        Light light = new Light();
        Door door1 = new Door("door1");
        Door door2 = new Door("door2");
        Window window1 = new Window("window1");
        Window window2 = new Window("window2");
        room.setWindow(window1);
        room.setWindow(window2);
        room.setDoor(door1);
        room.setDoor(door2);
        room.setLight(light);
        closeAllOpenings = new CloseAllOpenings(room);
    }

    @Test
    public void testExecute() {
        closeAllOpenings.execute();
        assertEquals(false, room.getDoor1().isOpen());
        assertEquals(false, room.getWindow(1).isOpen());
    }

    @Test
    public void testToString() {
        assertEquals("close all openings", closeAllOpenings.toString());
    }
}

