package main.java.test;

import main.java.logic.commands.open.OpenAWindow;
import main.java.model.fixtures.Light;
import main.java.model.openings.Window;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;
import main.java.logic.users.Permissions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class OpenAWindowTest {

    private Room room;
    private OpenAWindow openAWindow;

    @BeforeEach
    void setUp() {
        Room room = new Kitchen("room");
        Window window = new Window("Window");
        Light light = new Light();
        room.setWindow(window);
        room.setLight(light);
        openAWindow = new OpenAWindow(room, 1);
    }

    @Test
    void testGetWindowNumber() {
        assertEquals(1, openAWindow.getWindowNumber());
    }

    @Test
    void testRequirePermissions() {
        assertEquals(Permissions.WINDOW, openAWindow.requirePermissions());
    }

    @Test
    void testExecute() {
        assertTrue(openAWindow.execute());
    }

    @Test
    void testToString() {
        assertEquals("open a window", openAWindow.toString());
    }
}
