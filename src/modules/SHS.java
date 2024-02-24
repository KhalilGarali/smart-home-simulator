package modules;
import openings.*;
import commands.*;
import commands.close.*;
import commands.open.*;


import java.util.*;
import rooms.*;

public class SHS {


    public static void main(String[] args){

        Room kitchen = new Kitchen();
        Window window1 = new Window();
        kitchen.setWindows(window1);

        List<Room> houseLayout = new ArrayList<>();
        houseLayout.add(kitchen);

        // SHC commands to be instantiated at the start of the simulation
        OpenWindow openWindow = new OpenWindow(kitchen);
        CloseWindow closeWindow = new CloseWindow(kitchen);
        OpenDoor openDoor = new OpenDoor(kitchen);
        CloseDoor closeDoor = new CloseDoor(kitchen);

        // instaniate an SHC with it's default commands 
        SHC anSHC = new SHC(openWindow, closeWindow, openDoor, closeDoor);

        anSHC.OpenWindowTest();
        // anSHC.closeWindowTest();
        // anSHC.openDoorTest();
        // anSHC.closeDoorTest();
    }
    
}
