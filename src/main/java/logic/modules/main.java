package main.java.logic.modules;

import main.java.logic.commands.Command;
import main.java.logic.commands.open.*;
import main.java.logic.users.*;
import main.java.model.openings.*;
import main.java.model.rooms.*;

import java.util.*;

public class main {

    // this won't be the main function anymore, it will be the client.
    public static void main(String[] args){

        SHS shs = new SHS();

        Room kitchen = shs.makeKitchen("My Kitchen");
        Room bedroom = shs.makeKitchen("My bedroom");

        Window window1 = shs.makeWindow("Window 1");
        Window window2 = shs.makeWindow("Window 2");

        kitchen.setWindow(window1);
        bedroom.setWindow(window2);

        User father = shs.makeParent("John");
        User child = shs.makeChild("Joseph");
        User guest = shs.makeGuest("Julie");

        shs.enterRoom(father, bedroom);

        OpenAWindow openAWindow1 = shs.makeOpenAWindow(kitchen, 1);
        shs.doAction(father,openAWindow1, kitchen);
//        shs.doAction(child,openAWindow1, kitchen);
//        Room kitchen = new Kitchen();
//        Window window1 = new Window();
//        Window window2 = new Window();
//        kitchen.setWindow(window1);
//        kitchen.setWindow(window2);
//
//        //this is optional for now, might be used later by SHP for security reasons
//        List<Room> houseLayout = new ArrayList<>();
//        houseLayout.add(kitchen);
//
//        // SHC commands to be instantiated at the start of the simulation
//        OpenAWindow openWindow1 = new OpenAWindow(kitchen, 1);
//        OpenAWindow openWindow2 = new OpenAWindow(kitchen, 2);
//        // CloseWindow closeWindow = new CloseWindow(kitchen);
//        // OpenDoor openDoor = new OpenDoor(kitchen);
//        // CloseDoor closeDoor = new CloseDoor(kitchen);
//
//        // instaniate an SHC with its default commands
//        SHC anSHC = new SHC();
//        SHH anSHH = new SHH();
//        kitchen.addObserver(anSHH);
//
//        User stranger = new Stranger("IT");
//        User father = new Parent("Jordan");
//        User child = new Child("Joseph");
//        User guest = new Guest("Margery");
//
//
//        //those two commands should somehow be linked to the GUI button trigger
//        anSHC.userAction(stranger, openWindow1);
//        anSHC.userAction(father, openWindow1);
//        anSHC.userAction(child, openWindow1);
//        anSHC.userAction(guest, openWindow1);


        // anSHC.addCommand(openWindow2);
        // anSHC.executeCommand();

    }
    
}
