package main.java.logic.modules;
import main.java.logic.commands.*;
import main.java.logic.commands.close.CloseADoor;
import main.java.logic.commands.close.CloseAWindow;
import main.java.logic.commands.open.OpenADoor;
import main.java.logic.commands.open.OpenAWindow;
import main.java.logic.observerPattern.Observable;
import main.java.logic.users.Parent;
import main.java.logic.users.User;
import main.java.model.rooms.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Smart Home Controller (SHC) module.
 * This module is responsible for handling commands related to room operations,
 * such as opening or closing doors and windows, based on user permissions.
 */
public class SHC extends Module{
    //this potentially has to be a list
    private Command aCommand;

    /**
     * Default constructor for the SHC module.
     */
    public SHC (){
        
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
    public Boolean userAction(User u, Command c, Room room){
        if(u instanceof Parent){
            if (u.hasPermission(c.requirePermissions())){
                addCommand(c);
                executeCommand();
                return true;
            }
        }
        else if(u.getRoom().equals(room)){
            if (u.hasPermission(c.requirePermissions())){
                addCommand(c);
                executeCommand();
                return true;
            }else {
                System.out.println("\n--------------------------------------------------------------------------");
                System.out.println(u + " doesn't have permission to " + c);
                System.out.println("--------------------------------------------------------------------------");
            }
        }

        System.out.println(u.getName() + " is not in "+ room.getName() );
        return false;
    }

    /**
     * Sets the command to be executed.
     *
     * @param c The command to add to the module.
     */
    public void addCommand(Command c){
        aCommand = c;
    }

    /**
     * Executes the command stored in the module.
     */
    public void executeCommand(){
        aCommand.execute();
    }

    //TODO to be implemented as needed
    @Override
    public void update(Observable o){
    }
}
