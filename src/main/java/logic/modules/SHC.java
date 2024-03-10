package main.java.logic.modules;
import main.java.logic.commands.*;
import main.java.logic.commands.close.CloseADoor;
import main.java.logic.commands.close.CloseAWindow;
import main.java.logic.commands.open.OpenADoor;
import main.java.logic.commands.open.OpenAWindow;
import main.java.logic.observerPattern.Observable;
import main.java.model.rooms.*;

public class SHC extends Module{
    //this potentially has to be a list
    private Command aCommand;

    //default constructor
    public SHC (){
        
    }

    public void addCommand(Command c){
        aCommand = c;
    }

    public void executeCommand(){
        aCommand.execute();
    }

    @Override
    public void update(Observable o){

    }
}
