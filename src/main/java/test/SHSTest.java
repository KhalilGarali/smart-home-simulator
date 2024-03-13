package main.java.test;

import main.java.logic.commands.change.ChangeTemperature;
import main.java.logic.commands.close.*;
import main.java.logic.commands.off.TurnAutoLightOff;
import main.java.logic.commands.off.TurnCoolingOff;
import main.java.logic.commands.off.TurnHeatingOff;
import main.java.logic.commands.off.TurnLightOff;
import main.java.logic.commands.on.TurnAutoLightOn;
import main.java.logic.commands.on.TurnCoolingOn;
import main.java.logic.commands.on.TurnHeatingOn;
import main.java.logic.commands.on.TurnLightOn;
import main.java.logic.commands.open.*;
import main.java.logic.modules.SHS;
import main.java.logic.users.*;
import main.java.model.fixtures.Light;
import main.java.model.fixtures.Temperature;
import main.java.model.openings.Door;
import main.java.model.openings.Opening;
import main.java.model.openings.Window;
import main.java.model.rooms.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SHSTest {
    private SHS shs;

    @Before
    public void setUp() {
        shs = SHS.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(shs);
    }

    @Test
    public void testMakeParent() {
        User parent = shs.makeParent("John");
        assertTrue(parent instanceof Parent);
        assertEquals("John", parent.getName());
    }

    @Test
    public void testMakeFamilyMember() {
        User member = shs.makeFamilyMember("Jane");
        assertTrue(member instanceof FamilyMember);
        assertEquals("Jane", member.getName());
    }

    @Test
    public void testMakeChild() {
        User child = shs.makeChild("Jimmy");
        assertTrue(child instanceof Child);
        assertEquals("Jimmy", child.getName());
    }

    @Test
    public void testMakeStranger() {
        User stranger = shs.makeStranger("Jack");
        assertTrue(stranger instanceof Stranger);
        assertEquals("Jack", stranger.getName());
    }

    @Test
    public void testMakeGuest() {
        User guest = shs.makeGuest("Jill");
        assertTrue(guest instanceof Guest);
        assertEquals("Jill", guest.getName());
    }

    @Test
    public void testRemoveUser() {
        User user = shs.makeParent("Test");
        shs.removeuser(user);
        assertFalse(shs.getHouseUsers().contains(user));
    }

    @Test
    public void testMakeKitchen() {
        Room kitchen = shs.makeKitchen("Kitchen");
        assertTrue(kitchen instanceof Kitchen);
        assertEquals("Kitchen", kitchen.getName());
    }

    @Test
    public void testMakeLivingRoom() {
        Room livingRoom = shs.makeLivingRoom("Living Room");
        assertTrue(livingRoom instanceof LivingRoom);
        assertEquals("Living Room", livingRoom.getName());
    }

    @Test
    public void testMakeBedRoom() {
        Room bedRoom = shs.makeBedRoom("Bedroom");
        assertTrue(bedRoom instanceof BedRoom);
        assertEquals("Bedroom", bedRoom.getName());
    }

    @Test
    public void testMakeBasement() {
        Room basement = shs.makeBasement("Basement");
        assertTrue(basement instanceof Basement);
        assertEquals("Basement", basement.getName());
    }

    @Test
    public void testMakeGarage() {
        Room garage = shs.makeGarage("Garage");
        assertTrue(garage instanceof Garage);
        assertEquals("Garage", garage.getName());
    }

    @Test
    public void testMakePorch() {
        Room porch = shs.makePorch("Porch");
        assertTrue(porch instanceof Porch);
        assertEquals("Porch", porch.getName());
    }

    @Test
    public void testMakeBathroom() {
        Room bathroom = shs.makeBathroom("Bathroom");
        assertTrue(bathroom instanceof Bathroom);
        assertEquals("Bathroom", bathroom.getName());
    }

    @Test
    public void testMakeWindow() {
        Opening window = shs.makeWindow("Window");
        assertTrue(window instanceof Window);
        assertEquals("Window", ((Window) window).getName());
    }

    @Test
    public void testMakeDoor() {
        Opening door = shs.makeDoor("Door");
        assertTrue(door instanceof Door);
        assertEquals("Door", ((Door) door).getName());
    }

    @Test
    public void testMakeLight() {
        Light light = shs.makeLight("Light");
        assertTrue(light instanceof Light);
    }

    @Test
    public void testMakeTemp() {
        Temperature temp = shs.makeTemp("Temperature");
        assertTrue(temp instanceof Temperature);
    }

    @Test
    public void testMakeOpenAWindow() {
        Room room = shs.makeKitchen("Test Kitchen");
        OpenAWindow command = shs.makeOpenAWindow(room, 1);
        assertTrue(command instanceof OpenAWindow);
        assertEquals(room, command.getRoom());
        assertEquals(1, command.getWindowNumber());
    }

    @Test
    public void testMakeOpenAllWindows() {
        Room room = shs.makeKitchen("Test Kitchen");
        OpenAllWindows command = shs.makeOpenAllWindows(room);
        assertTrue(command instanceof OpenAllWindows);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeOpenADoor() {
        Room room = shs.makeKitchen("Test Kitchen");
        OpenADoor command = shs.makeOpenADoor(room, 1);
        assertTrue(command instanceof OpenADoor);
        assertEquals(room, command.getRoom());
        assertEquals(1, command.getDoorNumber());
    }

    @Test
    public void testMakeOpenAllDoors() {
        Room room = shs.makeKitchen("Test Kitchen");
        OpenAllDoors command = shs.makeOpenAllDoors(room);
        assertTrue(command instanceof OpenAllDoors);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeOpenAllOpenings() {
        Room room = shs.makeKitchen("Test Kitchen");
        OpenAllOpenings command = shs.makeOpenAllOpenings(room);
        assertTrue(command instanceof OpenAllOpenings);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeCloseAWindow() {
        Room room = shs.makeKitchen("Test Kitchen");
        CloseAWindow command = shs.makeCloseAWindow(room, 1);
        assertTrue(command instanceof CloseAWindow);
        assertEquals(room, command.getRoom());
        assertEquals(1, command.getWindowNumber());
    }

    @Test
    public void testMakeCloseAllWindows() {
        Room room = shs.makeKitchen("Test Kitchen");
        CloseAllWindows command = shs.makeCloseAllWindows(room);
        assertTrue(command instanceof CloseAllWindows);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeCloseADoor() {
        Room room = shs.makeKitchen("Test Kitchen");
        CloseADoor command = shs.makeCloseADoor(room, 1);
        assertTrue(command instanceof CloseADoor);
        assertEquals(room, command.getRoom());
        assertEquals(1, command.getDoorNumber());
    }

    @Test
    public void testMakeCloseAllDoors() {
        Room room = shs.makeKitchen("Test Kitchen");
        CloseAllDoors command = shs.makeCloseAllDoors(room);
        assertTrue(command instanceof CloseAllDoors);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeCloseAllOpenings() {
        Room room = shs.makeKitchen("Test Kitchen");
        CloseAllOpenings command = shs.makeCloseAllOpenings(room);
        assertTrue(command instanceof CloseAllOpenings);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeTurnLightOn() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnLightOn command = shs.makeTurnLightOn(room);
        assertTrue(command instanceof TurnLightOn);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeTurnLightOff() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnLightOff command = shs.makeTurnLightOff(room);
        assertTrue(command instanceof TurnLightOff);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeTurnAutoLightOn() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnAutoLightOn command = shs.makeTurnAutoLightOn(room);
        assertTrue(command instanceof TurnAutoLightOn);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeTurnAutoLightOff() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnAutoLightOff command = shs.makeTurnAutoLightOff(room);
        assertTrue(command instanceof TurnAutoLightOff);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeChangeTemperature() {
        Room room = shs.makeKitchen("Test Kitchen");
        ChangeTemperature command = shs.makeChangeTemperature(room, 25);
        assertTrue(command instanceof ChangeTemperature);
        assertEquals(room, command.getRoom());
        assertEquals(25, command.getTemperature());
    }

    @Test
    public void testMakeTurnCoolingOn() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnCoolingOn command = shs.makeTurnCoolingOn(room);
        assertTrue(command instanceof TurnCoolingOn);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeTurnCoolingOff() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnCoolingOff command = shs.makeTurnCoolingOff(room);
        assertTrue(command instanceof TurnCoolingOff);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeTurnHeatingOn() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnHeatingOn command = shs.makeTurnHeatingOn(room);
        assertTrue(command instanceof TurnHeatingOn);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testMakeTurnHeatingOff() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnHeatingOff command = shs.makeTurnHeatingOff(room);
        assertTrue(command instanceof TurnHeatingOff);
        assertEquals(room, command.getRoom());
    }

    @Test
    public void testShhDoAction() {
        Room room = shs.makeKitchen("Test Kitchen");
        TurnHeatingOn command = shs.makeTurnHeatingOn(room);
        shs.shhDoAction(command, room);
    }

    @Test
    public void testShcDoAction() {
        Room room = shs.makeKitchen("Test Kitchen");
        User user = shs.makeParent("Test Parent");
        TurnHeatingOn command = shs.makeTurnHeatingOn(room);
        Light light = new Light();
        room.setLight(light);
        shs.shcDoAction(user, command, room);
    }
}
