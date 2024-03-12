package main.java.logic.modules;

//can remove alot of the commands from here
import main.java.logic.commands.Command;
import main.java.logic.commands.CommandFactory;
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
import main.java.logic.commands.close.*;
import main.java.logic.layout.Layout;
import main.java.logic.users.*;
import main.java.model.fixtures.Light;
import main.java.model.fixtures.Temperature;
import main.java.model.openings.Door;
import main.java.model.openings.Opening;
import main.java.model.openings.Window;
import main.java.model.rooms.*;
import main.java.logic.dashboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.concurrent.TimeUnit;

public class SHS {
    SHH shh;
    SHC shc;
    SHP shp ;
    private static SHS shs;
    public CommandFactory cf;
    private List<Room> houseLayout;
    private List<User> houseUsers;
    private List<Opening> houseOpenings;
    private List<Object> housefixtures;
    private SHS(){
        this.shc = SHC.getIntance();
        //FIXME temp changes that might be permanent. (added the shc as arg)
        this.shh = SHH.getInstance(shc);
        this.shp = SHP.getInstance(shc);
        this.cf = new CommandFactory(shc);
        this.houseLayout = new ArrayList<Room>();
        this.houseUsers = new ArrayList<User>();
        this.houseOpenings = new ArrayList<Opening>();
        this.housefixtures = new ArrayList<Object>();
    }

    public static SHS getInstance(){
        if(shs == null){
            shs = new SHS();
        }
        return shs;
    }
    public void init(){
        //GUI init
        while(true){
            //create father command
            //father.enterRoom(kitchen)
        }
    }
    
    /**
     *  Make/Delete users: ////////////////////////////////////
     */
    public User makeParent(String name){
        User parent = new Parent(name);
        houseUsers.add(parent);
        return parent;
    }
    public User makeFamilyMember(String name){
        User member = new FamilyMember(name);
        houseUsers.add(member);
        return member;
    }
    public User makeChild(String name){
        User child = new Child(name);
        houseUsers.add(child);
        return child;
    }
    public User makeStranger(String name){
        User stranger = new Stranger(name);
        houseUsers.add(stranger);
        return stranger;
    }
    public User makeGuest(String name){
        User guest = new Guest(name);
        houseUsers.add(guest);
        return guest;
    }
    public void removeuser(User user){
        houseUsers.remove(user);
    }

    /**
     *  Make/Delete rooms: ////////////////////////////////////
     */
    //FIXME will be used only in Layout, not needed here
    public void removeRoom(Room room){
        houseLayout.remove(room);
    }
    public Room makeKitchen(String roomName){
        Room room = new Kitchen(roomName);
        houseLayout.add(room);
        return room;
    }
    public Room makeLivingRoom(String roomName){
        Room room = new LivingRoom(roomName);
        houseLayout.add(room);
        return room;
    }
    public Room makeBedRoom(String roomName){
        Room room = new BedRoom(roomName);
        houseLayout.add(room);
        return room;
    }
    public Room makeBasement(String roomName){
        Room room = new Basement(roomName);
        houseLayout.add(room);
        return room;
    }
    public Room makeGarage(String roomName){
        Room room = new Garage(roomName);
        houseLayout.add(room);
        return room;
    }
    public Room makePorch(String roomName){
        Room room = new Porch(roomName);
        houseLayout.add(room);
        return room;
    }
    public Room makeBathroom(String roomName){
        Room room = new Bathroom(roomName);
        houseLayout.add(room);
        return room;
    }

    //FIXME same here, the Layout class will handle the creation
    /**
     *  Make/Delete openings:  ////////////////////////////////////
     */
    public Opening makeWindow(String openingName){
        Opening window = new Window(openingName);
        houseOpenings.add(window);
        return window;
    }
    public Opening makeDoor(String openingName){
        Opening door = new Door(openingName);
        houseOpenings.add(door);
        return door;
    }

    /**
     *  Make/Delete fixtures:  ////////////////////////////////////
     */
    public Light makeLight(String openingName){
        Light light = new Light();
        housefixtures.add(light);
        return light;
    }
    public Temperature makeTemp(String openingName){
        Temperature temperature = new Temperature();
        housefixtures.add(temperature);
        return temperature;
    }

    /**
     *  Make/Delete commands: ////////////////////////////////////
     */
    //FIXME moved to the factory metho, confirm
    public OpenAWindow makeOpenAWindow(Room room, int number){
        OpenAWindow command = new OpenAWindow(room, number);
        shc.addCommand(command);
        return command;
    }
    public OpenAllWindows makeOpenAllWindows(Room room){
        OpenAllWindows command = new OpenAllWindows(room);
        shc.addCommand(command);
        return command;
    }
    public OpenADoor makeOpenADoor(Room room, int number){
        OpenADoor command = new OpenADoor(room, number);
        shc.addCommand(command);
        return command;
    }
    public OpenAllDoors makeOpenAllDoors(Room room){
        OpenAllDoors command = new OpenAllDoors(room);
        shc.addCommand(command);
        return command;
    }
    public OpenAllOpenings makeOpenAllOpenings(Room room){
        OpenAllOpenings command = new OpenAllOpenings(room);
        shc.addCommand(command);
        return command;
    }
    public CloseAWindow makeCloseAWindow(Room room, int number){
        CloseAWindow command = new CloseAWindow(room, number);
        shc.addCommand(command);
        return command;
    }
    public CloseAllWindows makeCloseAllWindows(Room room){
        CloseAllWindows command = new CloseAllWindows(room);
        shc.addCommand(command);
        return command;
    }
    public CloseADoor makeCloseADoor(Room room, int number){
        CloseADoor command = new CloseADoor(room, number);
        shc.addCommand(command);
        return command;
    }
    public CloseAllDoors makeCloseAllDoors(Room room){
        CloseAllDoors command = new CloseAllDoors(room);
        shc.addCommand(command);
        return command;
    }
    public CloseAllOpenings makeCloseAllOpenings(Room room){
        CloseAllOpenings command = new CloseAllOpenings(room);
        shc.addCommand(command);
        return command;
    }
    public TurnLightOn makeTurnLightOn(Room room){
        TurnLightOn command = new TurnLightOn(room);
        shc.addCommand(command);
        return command;
    }
    public TurnLightOff makeTurnLightOff(Room room){
        TurnLightOff command = new TurnLightOff(room);
        shc.addCommand(command);
        return command;
    }
    public TurnAutoLightOn makeTurnAutoLightOn(Room room){
        TurnAutoLightOn command = new TurnAutoLightOn(room);
        shc.addCommand(command);
        return command;
    }
    public TurnAutoLightOff makeTurnAutoLightOff(Room room){
        TurnAutoLightOff command = new TurnAutoLightOff(room);
        shc.addCommand(command);
        return command;
    }
    public ChangeTemperature makeChangeTemperature(Room room, int temperature){
        ChangeTemperature command = new ChangeTemperature(room, temperature);
        shc.addCommand(command);
        return command;
    }
    public TurnCoolingOn makeTurnCoolingOn(Room room){
        TurnCoolingOn command = new TurnCoolingOn(room);
        shc.addCommand(command);
        return command;
    }
    public TurnCoolingOff makeTurnCoolingOff(Room room){
        TurnCoolingOff command = new TurnCoolingOff(room);
        shc.addCommand(command);
        return command;
    }
    public TurnHeatingOn makeTurnHeatingOn(Room room){
        TurnHeatingOn command = new TurnHeatingOn(room);
        shc.addCommand(command);
        return command;
    }
    public TurnHeatingOff makeTurnHeatingOff(Room room){
        TurnHeatingOff command = new TurnHeatingOff(room);
        shc.addCommand(command);
        return command;
    }
    public void shhDoAction(Command command, Room room){
        if(command instanceof TurnHeatingOff||
                command instanceof TurnHeatingOn ||
                command instanceof TurnCoolingOn ||
                command instanceof TurnCoolingOff) {
            shh.regulateTemperature(room);
        } else {
            shh.doAction(command, room);
        }
    }
    public void shpDoAction(Command command, Room room){
        shp.doAction(command, room);
    }
    public void shcDoAction(User user, Command command, Room room){
        shc.userAction(user, command, room);
    }
    /**
     *  Make/Delete actions: ////////////////////////////////////
     */

    //FIXME moved to the user class for now!
    // public void enterRoom(User user, Room room){
    //     if(user.getRoom() != null){
    //         System.out.println( "exiting " + user.getRoom().getName());
    //         user.getRoom().removeUserFromRoom(user);
    //         System.out.println(user + " is in this room now: " + room.getName());
    //         user.enterRoom(room);
    //     }
    //     else{
    //         System.out.println(user + " is in this room now: " + room.getName());
    //         user.enterRoom(room);
    //     }
    // }

    //FIXME the wrapper function is not needed, we can use the shc. call directly
    // public void doAction(User user, Command command, Room room){
    //     shc.userAction(user, command, room);
    // }

    //FIXME the wrapper function is not needed, we can use the shh. call directly
    // public void increaseTemperature(int temperature){
    // }
}

// coordinate system (check wade3's branch)
// date and time increment by hour
// temperature make it change by every hour, by incrementing/decrementing with a small random number
// make sure all tests are through the command pattern