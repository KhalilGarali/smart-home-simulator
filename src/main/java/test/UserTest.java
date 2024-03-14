package main.java.test;

import main.java.logic.users.Permissions;
import main.java.test.TestUser;
import main.java.logic.users.User;
import main.java.model.rooms.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private Room room;

    @Before
    public void setUp() {
        user = new TestUser("John Doe");
        room = mock(Room.class);
    }

    @Test
    public void testMoveToRoom() {
        user.moveToRoom(room);
        verify(room).addUserToRoom(user);
        verify(room).turnLightOn();
        assertEquals(room, user.getRoom());

        user.moveToRoom(null);
        verify(room).removeUserFromRoom(user);
        verify(room).turnLightOff();
        assertNull(user.getRoom());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testSetName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }
}

