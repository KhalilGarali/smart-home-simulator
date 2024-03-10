package main.java.logic.modules;

import main.java.logic.commands.open.*;
import main.java.logic.layout.Layout;
import main.java.logic.users.*;
import main.java.model.openings.*;
import main.java.model.rooms.*;

import java.util.*;

public class SHS {

    // this won't be the main function anymore, it will be the client.
    public static void main(String[] args){

        //hardcode a room array for now to test layout file
        List<Room> houseLayout = new ArrayList<>();
        Layout aLayout = new Layout("src/main/java/logic/modules/houseLayoutFile.txt");
        houseLayout = aLayout.getRooms();
        System.out.println("***** The classes obtained from the example file are: ****");
        System.out.println("\n------------------------------------------------------------");
        for (Room r: houseLayout){
            System.out.println(r);
        }
        System.out.println("------------------------------------------------------------");



        Room kitchen = new Kitchen("kitchen");
        Window window1 = new Window();
        Window window2 = new Window();
        kitchen.setWindow(window1);
        kitchen.setWindow(window2);

        //this is optional for now, might be used later by SHP for security reasons
        // List<Room> houseLayout = new ArrayList<>();
        // houseLayout.add(kitchen);

        // SHC commands to be instantiated at the start of the simulation
        OpenAWindow openWindow1 = new OpenAWindow(kitchen, 1);
        OpenAWindow openWindow2 = new OpenAWindow(kitchen, 2);
        // CloseWindow closeWindow = new CloseWindow(kitchen);
        // OpenDoor openDoor = new OpenDoor(kitchen);
        // CloseDoor closeDoor = new CloseDoor(kitchen);

        // instaniate an SHC with it's default commands 
        SHC anSHC = new SHC();
        SHH anSHH = new SHH();
        kitchen.addObserver(anSHH);

        User stranger = new Stranger("IT");
        User father = new Parent("Jordan");
        User child = new Child("Joseph");
        User guest = new Guest("Margery");

        
        //those two commands should somehow be linked to the GUI button trigger
        System.out.println("****** Testing permissions on the OpenAWindow command: ******");

        anSHC.userAction(stranger, openWindow1);
        anSHC.userAction(father, openWindow1);
        anSHC.userAction(child, openWindow1);
        anSHC.userAction(guest, openWindow1);

    }
    
}
