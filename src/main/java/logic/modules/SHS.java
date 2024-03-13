package main.java.logic.modules;

import main.java.gui.HomeSimulatorFrame;
import main.java.logic.commands.Command;
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

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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

    public List<Room> getHouseLayout() {
        return houseLayout;
    }

    public List<User> getHouseUsers() {
        return houseUsers;
    }

    public List<Opening> getHouseOpenings() {
        return houseOpenings;
    }

    public List<Object> getHousefixtures() {
        return housefixtures;
    }

    public void startProgram(){
        //GUI init
        boolean on = true;
        while(on) {
            //create father command
            //father.enterRoom(kitchen)

            SwingUtilities.invokeLater(() -> {
                HomeSimulatorFrame frame = new HomeSimulatorFrame();
                frame.setVisible(true);
            });
            SHS shs = SHS.getInstance();

            // FIXME all this work will be done by the Layout class
            Room kitchen = shs.makeKitchen("My Kitchen");
            Room masterBedroom = shs.makeBedRoom("Master bedroom");
            Room kidsBedroom = shs.makeBedRoom("kid's bedroom");
            Room garage = shs.makeGarage("my garage");
            Room bathroom1 = shs.makeBathroom("u bathroom 1");
            Room bathroom2 = shs.makeBathroom("d bathroom 2");
            Room livingRoom = shs.makeLivingRoom("living room");
            Room porch = shs.makePorch("porch");
            Room basement = shs.makeBasement("basement");

            Opening kitchenWindow = shs.makeWindow("kitchen Window");
            Opening window2 = shs.makeWindow("Window 2");
            Opening masterWindow = shs.makeWindow("master Window");
            Opening kidsWindow = shs.makeWindow("kids Window");
            Opening livingRoomWindow = shs.makeWindow("living Room Window");
            Opening basementWindow = shs.makeWindow("basement Window");
            Opening uBathroomWindow = shs.makeWindow("uBathroom Window");
            Opening dBathroomWindow = shs.makeWindow("dBathroom Window");

            Opening garageDoor = shs.makeDoor("garage door");
            Opening livingRoomDoor = shs.makeDoor("living Room Door");
            Opening uBathroomDoor = shs.makeDoor("u Bathroom Door");
            Opening dBathroomDoor = shs.makeDoor("d Bathroom Door");
            Opening basementDoor = shs.makeDoor("basement Door");
            Opening masterDoor = shs.makeDoor("master bedroom Door");
            Opening kidsDoor = shs.makeDoor("Kids bedroom Door");
            Opening kitchenDoor = shs.makeDoor("kitchen door");

            kitchen.setWindow((Window) kitchenWindow);
            masterBedroom.setWindow((Window) masterWindow);
            kidsBedroom.setWindow((Window) kidsWindow);
            livingRoom.setWindow((Window) livingRoomWindow);
            basement.setWindow((Window) basementWindow);
            bathroom1.setWindow((Window) uBathroomWindow);
            bathroom2.setWindow((Window) dBathroomWindow);

            garage.setDoor((Door) garageDoor);
            masterBedroom.setDoor((Door) masterDoor);
            kidsBedroom.setDoor((Door) kidsDoor);
            livingRoom.setDoor((Door) livingRoomDoor);
            basement.setDoor((Door) basementDoor);
            bathroom1.setDoor((Door) uBathroomDoor);
            bathroom2.setDoor((Door) dBathroomDoor);
            kitchen.setDoor((Door) kitchenDoor);

            User father = shs.makeParent("John");
            // father.moveToRoom(kitchen);
            User child = shs.makeChild("Joseph");
            User guest = shs.makeGuest("Julie");
            User cousin = shs.makeFamilyMember("Jordan");
            User stranger = shs.makeStranger("Jamal");

            // examples for the command factory application
            Command openKitchenWindow1 = shs.cf.createCommand("openawindow", kitchen, 1);
            // **************************************
            OpenAWindow openKitchenWindow = shs.makeOpenAWindow(kitchen, 1);
            OpenAWindow openMasterWindow = shs.makeOpenAWindow(masterBedroom, 1);
            OpenAWindow openKidsWindow = shs.makeOpenAWindow(kidsBedroom, 1);
            OpenAWindow openBasementWindow = shs.makeOpenAWindow(basement, 1);
            OpenAWindow openLivingWindow = shs.makeOpenAWindow(livingRoom, 1);
            OpenAWindow openUBathroomWindow = shs.makeOpenAWindow(bathroom1, 1);
            OpenAWindow openDBathroomWindow = shs.makeOpenAWindow(bathroom2, 1);
            OpenADoor openKitchenDoor = shs.makeOpenADoor(kitchen, 1);
            // TurnLightOn turnLivingLightOn = shs.makeTurnLightOn(livingRoom);
            // TurnLightOff turnKitchenLightOff = shs.makeTurnLightOff(kitchen);
            TurnCoolingOn turnMasterCoolingOn = shs.makeTurnCoolingOn(masterBedroom);
            TurnCoolingOff turnMasterCoolingOff = shs.makeTurnCoolingOff(masterBedroom);
            TurnHeatingOn turnMasterHeatingOn = shs.makeTurnHeatingOn(masterBedroom);
            TurnHeatingOff turnMasterHeatingOff = shs.makeTurnHeatingOff(masterBedroom);
            TurnAutoLightOff turnKitchenAutoLightOff = shs.makeTurnAutoLightOff(kitchen);
            ChangeTemperature changeLivingTemperature = shs.makeChangeTemperature(livingRoom, 27);

            //tester for moving a user form room using the new command
            System.out.println("\n----------------------------------------");
            System.out.println("kitchen has " + kitchen.getUserFromRoom());
            father.moveToRoom(null);
            System.out.print("kitchen has " + kitchen.getUserFromRoom());
            System.out.print("\n----------------------------------------");
            // end of test

            //test for open window
            bathroom1.setDesiredTemperature(25);
            bathroom1.setCurrentTemperature(57);

            shs.shcDoAction(father, turnKitchenAutoLightOff, kitchen);
            shs.shcDoAction(father, openKitchenWindow1, kitchen);
            shs.shcDoAction(father, openKitchenDoor, kitchen);
            father.moveToRoom(kitchen);
            shs.shcDoAction(father, changeLivingTemperature, livingRoom);
            shs.shhDoAction(turnMasterHeatingOn, bathroom1);

            Scanner sc = new Scanner(System.in);
            System.out.println("To end the program, type 1");
            int num = sc.nextInt();
            if (num == 1) {
                on = false;
            }
        }
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
            System.out.println("This command is for SHC");
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