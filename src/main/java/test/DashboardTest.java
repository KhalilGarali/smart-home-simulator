package main.java.test;
import main.java.logic.dashboard.Dashboard;
import main.java.model.fixtures.Light;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;
import main.java.model.openings.Window;
import main.java.logic.users.Admin;
import main.java.logic.users.Stranger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {
    private Dashboard dashboard;

    @BeforeEach
    void setUp() {
        dashboard = new Dashboard(new Admin("Logged User"), new Stranger("Stranger"));
    }

    @Test
    void testSetDateTime() {
        dashboard.setDateTime(2022, 1, 1, 12, 0);
        assertEquals("2022-01-01T12:00", dashboard.getDateTime().toString());
    }

    @Test
    void testAdjustTimeSpeed() {
        dashboard.adjustTimeSpeed(2.0);
        assertEquals(2.0, dashboard.getTimeSpeed());
    }

    @Test
    void testMoveLoggedUser() {
        Room kitchen = new Kitchen("kitchen");
        Light light = new Light();
        kitchen.setLight(light);
        dashboard.moveLoggedUser(kitchen);
        assertEquals(kitchen, dashboard.getCurrentRoomLoggedUser());
    }

    @Test
    void testMoveNonLoggedUser() {
        Room kitchen = new Kitchen("kitchen");
        Light light = new Light();
        kitchen.setLight(light);
        dashboard.moveNonLoggedUser(kitchen);
        assertEquals(kitchen, dashboard.getCurrentRoomNonLoggedUser());
    }

    @Test
    void testBlockUnblockWindow() {
        Window window = new Window("Test Window");
        dashboard.blockWindow(window);
        assertTrue(window.isBlocked());

        dashboard.unblockWindow(window);
        assertFalse(window.isBlocked());
    }
}

