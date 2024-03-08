package modules;
import openings.*;
import commands.close.*;
import commands.open.*;


import java.util.*;
import rooms.*;

public class SHS {

    // this won't be the main function anymore, it will be the client.
    public static void main(String[] args){

        Room kitchen = new Kitchen();
        Window window1 = new Window();
        Window window2 = new Window();
        kitchen.setWindow(window1);
        kitchen.setWindow(window2);

        //this is optional for now, might be used later by SHP for security reasons
        List<Room> houseLayout = new ArrayList<>();
        houseLayout.add(kitchen);

        // SHC commands to be instantiated at the start of the simulation
        OpenAWindow openWindow1 = new OpenAWindow(kitchen, 1);
        OpenAWindow openWindow2 = new OpenAWindow(kitchen, 2);
        // CloseWindow closeWindow = new CloseWindow(kitchen);
        // OpenDoor openDoor = new OpenDoor(kitchen);
        // CloseDoor closeDoor = new CloseDoor(kitchen);

        // instaniate an SHC with it's default commands 
        SHC anSHC = new SHC();
        
        //those two commands should somehow be linked to the GUI button trigger
        anSHC.addCommand(openWindow1);
        anSHC.executeCommand();

        anSHC.addCommand(openWindow2);
        anSHC.executeCommand();

    }
    
}
