package main.java.test;

import main.java.model.openings.Window;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WindowTest {
    private Window window;

    @Before
    public void setUp() {
        window = new Window("Bedroom Window");
    }

    @Test
    public void testOpen() {
        window.open();
        assertTrue(window.isOpen());
    }

    @Test
    public void testClose() {
        window.close();
        assertFalse(window.isOpen());
    }

    @Test
    public void testIsOpen() {
        assertFalse(window.isOpen());
        window.open();
        assertTrue(window.isOpen());
    }

    @Test
    public void testBlockWindow() {
        window.blockWindow();
        window.open();
        assertFalse(window.isOpen());
        window.close();
        assertFalse(window.isOpen());
    }

    @Test
    public void testUnblockWindow() {
        window.blockWindow();
        window.unblockWindow();
        window.open();
        assertTrue(window.isOpen());
    }

    @Test
    public void testGetName() {
        assertEquals("Bedroom Window", window.getName());
    }
}

