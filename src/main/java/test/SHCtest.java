package main.java.test;

import main.java.logic.commands.Command;
import main.java.logic.modules.SHC;
import main.java.logic.users.Parent;
import main.java.logic.users.User;
import main.java.model.rooms.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class SHCTest {
    private SHC shc;
    private User user;
    private Command command;
    private Room room;

    @BeforeEach
    void setUp() {
        shc = SHC.getIntance();
        user = mock(User.class);
        command = mock(Command.class);
        room = mock(Room.class);
    }

    @Test
    void userActionWithParentAndPermission() {
        when(user instanceof Parent).thenReturn(true);
        when(user.hasPermission(any())).thenReturn(true);

        boolean result = shc.userAction(user, command, room);

        assertTrue(result);
        verify(command, times(1)).execute();
    }

    @Test
    void userActionWithNonParentInRoomAndPermission() {
        when(user instanceof Parent).thenReturn(false);
        when(user.getRoom()).thenReturn(room);
        when(user.hasPermission(any())).thenReturn(true);

        boolean result = shc.userAction(user, command, room);

        assertTrue(result);
        verify(command, times(1)).execute();
    }

    @Test
    void userActionWithNonParentNotInRoom() {
        when(user instanceof Parent).thenReturn(false);
        when(user.getRoom()).thenReturn(null);

        boolean result = shc.userAction(user, command, room);

        assertFalse(result);
        verify(command, never()).execute();
    }

    @Test
    void userActionWithNonParentNoPermission() {
        when(user instanceof Parent).thenReturn(false);
        when(user.getRoom()).thenReturn(room);
        when(user.hasPermission(any())).thenReturn(false);

        boolean result = shc.userAction(user, command, room);

        assertFalse(result);
        verify(command, never()).execute();
    }

    @Test
    void moduleActionExecution() {
        boolean result = shc.moduleAction(command, room);

        assertTrue(result);
        verify(command, times(1)).execute();
    }
}
