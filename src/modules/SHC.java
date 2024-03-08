package modules;
import commands.*;
import commands.close.CloseADoor;
import commands.close.CloseAWindow;
import commands.open.OpenADoor;
import commands.open.OpenAWindow;
import rooms.*;

public class SHC {
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
}
