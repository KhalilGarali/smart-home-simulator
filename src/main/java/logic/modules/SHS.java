package main.java.logic.modules;

import main.java.logic.commands.Command;
import main.java.logic.commands.open.OpenAWindow;
import main.java.logic.users.*;
import main.java.model.fixtures.Light;
import main.java.model.fixtures.Temperature;
import main.java.model.openings.Door;
import main.java.model.openings.Opening;
import main.java.model.openings.Window;
import main.java.model.rooms.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SHS {
    SHH shh;
    SHC shc;
    SHP shp ;
    private List<Room> houseLayout;
    private List<User> houseUsers;
    private List<Opening> houseOpenings;
    private List<Object> housefixtures;
    public SHS(){
        this.shc = new SHC();
        this.shh = new SHH();
        this.shp = new SHP();
        this.houseLayout = new ArrayList<Room>();
        this.houseUsers = new ArrayList<User>();
        this.houseOpenings = new ArrayList<Opening>();
        this.housefixtures = new ArrayList<Object>();
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
    public OpenAWindow makeOpenAWindow(Room room, Integer number){
        OpenAWindow command = new OpenAWindow(room, number);
        shc.addCommand(command);
        return command;
    }

    /**
     *  Make/Delete actions: ////////////////////////////////////
     */

    public void enterRoom(User user, Room room){
        if(user.getRoom() != null){
            System.out.println( "exiting " + user.getRoom().getName());
            user.getRoom().removeUserFromRoom(user);
            System.out.println(user + " is in this room now: " + room.getName());
            user.enterRoom(room);
        }
        else{
            System.out.println(user + " is in this room now: " + room.getName());
            user.enterRoom(room);
        }

    }
    public void doAction(User user, Command command, Room room){
        shc.userAction(user, command, room);
    }
    public void increaseTemperature(int temperature){

    }
}
