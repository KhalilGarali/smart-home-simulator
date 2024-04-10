package main.java.logic.modules;

import java.util.ArrayList;

import main.java.logic.MediatorPattern.Component;
import main.java.logic.commands.*;
import main.java.logic.layout.House;
import main.java.logic.observerPattern.Observable;
import main.java.logic.users.Parent;
import main.java.logic.users.User;
import main.java.model.rooms.*;

/**
 * Represents the Smart Home Controller (SHC) module.
 * This module is responsible for handling commands related to room operations,
 * such as opening or closing doors and windows, based on user permissions.
 */
public class SHC extends Module implements Component {

    //this potentially has to be a list
    private Command aCommand;
    private static SHC instance = null;
    private SHS shs;
    private House house = House.getInstance();
    private ArrayList<Room> rooms = house.getRooms();

    private SHC() {

    }

    /**
     * Default constructor for the SHC module.
     */
    public static synchronized SHC getIntance() {
        if (instance == null) {
            instance = new SHC();
        }
        return instance;
    }

    /**
     * Attempts to execute a given command based on the user's permissions.
     * If the user has the required permissions, the command is added and then executed.
     * Otherwise, it prints a message indicating lack of permission.
     *
     * @param u The user attempting to execute a command.
     * @param c The command to be executed.
     * @return Boolean indicating if the action was successful. Currently always returns false.
     */

    //SHS
    //userAction(father, OpenAWindow, kitchen) //upon an event from GUI
    public Boolean userAction(User u, Command c, Room room) {
        if (u instanceof Parent) {
            if (u.hasPermission(c.requirePermissions())) {
                addCommand(c);
                executeCommand();
                return true;
            }
        } else if (u.getRoom().equals(room)) {
            if (u.hasPermission(c.requirePermissions())) {
                addCommand(c);
                executeCommand();
                return true;
            } else {
                System.out.println("\n--------------------------------------------------------------------------");
                System.out.println(u + " doesn't have permission to " + c);
                System.out.println("--------------------------------------------------------------------------");
                //  JOptionPane.showMessageDialog(null,u + " doesn't have permission to " + c);
            }
        } else {
            System.out.println(u.getName() + " is not in " + room.getName());
//            JOptionPane.showMessageDialog(null,u.getName() + " is not in "+ room.getName() );

        }

        return false;
    }

    //SHS -->
    //SHH.regulatetemp() -->
    //regulatetemp(){
    // shc.moduleAction
    //}
    // FIXME must be called from SHH when temp is up for example.
    public Boolean moduleAction(Command c){
            addCommand(c);
            executeCommand();
            return true;
    }

    /**
     * Sets the command to be executed.
     *
     * @param c The command to add to the module.
     */
    public void addCommand(Command c) {
        aCommand = c;
    }

    /**
     * Executes the command stored in the module.
     */
    public void executeCommand() {
        aCommand.execute();
    }

    //TODO to be implemented as needed
    @Override
    public void update(Observable o) {
    }

    public void closeAllOpenings() {
        //go thru every room and close all windows and doors
        for (Room room : this.rooms) {
            
        }
    }

    public void setSHS(SHS shs){
        this.shs = shs;
    }
}
