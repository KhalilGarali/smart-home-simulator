package main.java.logic.modules;

import main.java.logic.commands.Command;
import main.java.logic.commands.open.OpenAWindow;
import main.java.logic.users.*;
import main.java.model.openings.Door;
import main.java.model.openings.Opening;
import main.java.model.openings.Window;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.LivingRoom;
import main.java.model.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public class SHS {
    SHH shh;
    SHC shc;
    SHP shp ;
    private List<Room> houseLayout;
    private List<User> houseUsers;
    private List<Opening> houseOpenings;
    public SHS(){
        this.shc = new SHC();
        this.shh = new SHH();
        this.shp = new SHP();
        this.houseLayout = new ArrayList<Room>();
        this.houseUsers = new ArrayList<User>();
        this.houseOpenings = new ArrayList<Opening>();
    }

    public void removeRoom(Room room){
        houseLayout.remove(room);
    }
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

    public Room makeKitchen(String roomName){
        Room room = new Kitchen(roomName);
        houseLayout.add(room);
        return room;
    }
    public Room makeRoom(String roomName){
        Room room = new LivingRoom(roomName);
        houseLayout.add(room);
        return room;
    }
    public Window makeWindow(String openingName){
        Window window = new Window(openingName);
        houseOpenings.add(window);
        return window;
    }
    public Door makeDoor(String openingName){
        Door door = new Door(openingName);
        houseOpenings.add(door);
        return door;
    }


    public OpenAWindow makeOpenAWindow(Room room, Integer number){
        OpenAWindow command = new OpenAWindow(room, number);
        shc.addCommand(command);
        return command;
    }

    public void doAction(User user, Command command, Room room){
        shc.userAction(user, command, room);
    }
    public void enterRoom(User user, Room room){
        if(user.getRoom() != null){
            user.getRoom().removeUserFromRoom(user);
        }
        user.enterRoom(room);
    }
}
