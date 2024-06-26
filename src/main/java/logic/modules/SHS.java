package main.java.logic.modules;

//can remove alot of the commands from here
import main.java.logic.MediatorPattern.Component;
import main.java.logic.MediatorPattern.Mediator;
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
import main.java.logic.layout.House;
import main.java.logic.layout.Layout;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
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

public class SHS implements Observable, Mediator {

    // modules
    SHC shc;
    SHH shh;
    SHP shp;

    // attributes
    private boolean isEmpty = true;
    public CommandFactory cf;
    private List<Room> houseLayout;
    private ArrayList<User> houseUsers;
    private List<Opening> houseOpenings;
    private List<Object> housefixtures;
    public User activeUser;
    private House house;
    public List<Observer> observers = new ArrayList();

    // Singleton Instance Pointer
    public static SHS shs;

    // Singleton Constructor
    private SHS(){
        this.shc = SHC.getIntance();
        this.shh = SHH.getInstance(shc);
        this.shp = SHP.getInstance(shc);
        this.shc.setSHS(this);
        this.shh.setSHS(this);
        this.shp.setSHS(this);
        this.cf = new CommandFactory(shc);
        this.houseLayout = new ArrayList<Room>();
        this.houseUsers = new ArrayList<User>();
        this.houseOpenings = new ArrayList<Opening>();
        this.housefixtures = new ArrayList<Object>();
        this.activeUser = null;
        this.house = House.getInstance();
        houseLayout = house.getRooms();
    }

    // return all rooms in the house
    public ArrayList<Room> getHouseLayout(){
        return (ArrayList<Room>) this.houseLayout;
    }
    
    // return all users in the house
    public ArrayList<User> getHouseUsers(){
        return this.houseUsers;
    }

    // return active user
    public User getActiveUser()
    {
        return this.activeUser;
    }

    // return house user
    public List<User> getHouseUser(){
        return this.houseUsers;
    }
    
    // method to get room by name
    public Room getRoomByName(String name)
    {
        for (Room room: houseLayout)
        {
            if (room.getName().equals(name))
                return room;
        }
        return null;
    }

    // getIntance method for the Singleton pattern
    public static synchronized SHS getInstance(){
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

    public void addHouseUser(User user){
        this.houseUsers.add(user);
        this.isEmpty = false;
        // notifying(this, "Not Empty House");
    }

    public void setActiveUser(User user){
        this.activeUser = user;
        notifyObservers();
    }
    /**
     *  Make/Delete users: ////////////////////////////////////
     */


    /**
     *  Make/Delete rooms: ////////////////////////////////////
     */
    //FIXME will be used only in Layout, not needed here
    public void removeRoom(Room room){
        houseLayout.remove(room);
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

    public OpenADoor makeOpenADoor(Room room, int number){
        OpenADoor command = new OpenADoor(room, number);
        shc.addCommand(command);
        return command;
    }

    public CloseAWindow makeCloseAWindow(Room room, int number){
        CloseAWindow command = new CloseAWindow(room, number);
        shc.addCommand(command);
        return command;
    }

    public CloseADoor makeCloseADoor(Room room, int number){
        CloseADoor command = new CloseADoor(room, number);
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




    // public void shhDoAction(Command command, Room room){
    //     if(command instanceof TurnHeatingOff||
    //             command instanceof TurnHeatingOn ||
    //             command instanceof TurnCoolingOn ||
    //             command instanceof TurnCoolingOff) {
    //         shh.regulateTemperature(room);
    //     } else {
    //         System.out.println("This command is for SHC");
    //         shh.doAction(command, room);
    //     }
    // }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void notify(Component c, String message) {
        if( c instanceof SHH){
            if(message.equalsIgnoreCase("")){

            }
            if(message.equalsIgnoreCase("")){

            }
        }
        if( c instanceof SHC){
            if(message.equalsIgnoreCase("")){

            }
            if(message.equalsIgnoreCase("")){

            }
        }
        if( c instanceof SHP){
            if(message.equalsIgnoreCase("HouseIsEmpty")){
                handleHouseIsEmptyFromSHP();
            }
            if(message.equalsIgnoreCase("HouseIsNotEmpty")){
                handleHouseIsNotEmptyFromSHP();
            }
        }
    }
    public void handleHouseIsEmptyFromSHP(){
        for (Room room : houseLayout) {
            shc.moduleAction(cf.createCommand("closeawindow", room, 0));
            shc.moduleAction(cf.createCommand("closeadoor", room, 0));
            //if room has a user, move the user to the Outside room
            if (room.getUserFromRoom().size() > 0) {
                for (User user : room.getUserFromRoom()) {
                    if (room instanceof Outside) {
                        continue;
                    }
                    house.getOutside().addUserToRoom(user);
                }
                
            }
            if(!(room instanceof Outside)){
                room.clearRoom();
            }
        }
    }
    public void handleHouseIsNotEmptyFromSHP(){
        // add the users back to their own room based on the room name in the user object, and add them to the room that has the same name in the houselayout
        for (User user : house.getOutside().getUserFromRoom()) {
            for (Room r : houseLayout) {
                if (r.getName().equalsIgnoreCase(user.getRoom().getName())) {
                    r.addUserToRoom(user);
                }
            }
        }
        for (Room room : houseLayout) {
            if (room instanceof Outside && room.getUserFromRoom().size() > 0 ) {
                    room.clearRoom();
            }
        }
    }
}
