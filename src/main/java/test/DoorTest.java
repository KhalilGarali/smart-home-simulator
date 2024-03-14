package main.java.test;

import main.java.model.openings.Door;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoorTest {
    private Door door;

    @Before
    public void setUp() {
        door = new Door("Front Door");
    }

    @Test
    public void testOpen() {
        door.open();
        assertTrue(door.isOpen());
    }

    @Test
    public void testClose() {
        door.close();
        assertFalse(door.isOpen());
    }

    @Test
    public void testIsOpen() {
        assertFalse(door.isOpen());
        door.open();
        assertTrue(door.isOpen());
    }

    @Test
    public void testGetName() {
        assertEquals("Front Door", door.getName());
    }
}

