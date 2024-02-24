package modules;
import commands.*;
import commands.close.CloseDoor;
import commands.close.CloseWindow;
import commands.open.OpenDoor;
import commands.open.OpenWindow;
import rooms.*;

public class SHC {
    //this potentially has to be a list
    Room kitchen = new Kitchen();

    private Command openWindow;
    private Command closeWindow;
    private Command openDoor;
    private Command closeDoor;

    
    public SHC (Command openWindow, Command closeWindow, Command openDoor, Command closeDoor){
        this.openWindow = openWindow;
        this.closeWindow = closeWindow;
        this.openDoor = openDoor;
        this.closeDoor = closeDoor;
    }

    public void addCommand(){

    }
    public void OpenWindowTest(){
        openWindow.execute();
    }
}
