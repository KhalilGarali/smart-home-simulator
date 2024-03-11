package main.java.logic.modules;

import main.java.logic.commands.open.*;
import main.java.logic.commands.close.*;
import main.java.logic.layout.Layout;
import main.java.logic.users.*;
import main.java.model.openings.*;
import main.java.model.rooms.*;
import main.java.logic.dashboard.*;

import java.util.*;

import java.util.concurrent.TimeUnit;

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
        CloseAWindow closeWindow1 = new CloseAWindow(kitchen);
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


        // *************************************
        System.out.println("------------------------------");
        Dashboard dashboard = new Dashboard(father, stranger);

        // Use Case: Modify the date and time
        System.out.println("Use Case: Modify the date and time");
        dashboard.setDateTime(2010, 6, 10, 12, 0);
        System.out.println("------------------------------");

        // Use Case: The simulated time speed can be increased or decreased
        System.out.println("Use Case: The simulated time speed can be increased or decreased");
        System.out.println("Current date time: " + dashboard.getDateTime());
        dashboard.adjustTimeSpeed(0.5); // make time run faster

        // try {
        //     TimeUnit.SECONDS.sleep(60);
        // } catch(InterruptedException e) {
        //     System.out.println("error with time sleep");
        // }
        System.out.println("new date time: " + dashboard.getDateTime());
        System.out.println("------------------------------");

        // Use Case: Move the logged user to another room
        System.out.println("Use Case: Move the logged user to another room");
        System.out.println(dashboard.getCurrentRoomLoggedUser());
        dashboard.moveLoggedUser(Dashboard.layout[3][2]); // moved to bedroom6
        System.out.println(dashboard.getCurrentRoomLoggedUser());
        System.out.println("------------------------------");

        // Use Case: Place people in specific rooms, or outside the home
        System.out.println("Use Case: Place people in specific rooms, or outside the home");
        dashboard.getCurrentRoomNonLoggedUser();
        dashboard.moveNonLoggedUser(Dashboard.layout[0][0]); // moved to kitchen
        dashboard.getCurrentRoomNonLoggedUser();
        System.out.println("------------------------------");

        // Use Case: Modify the temperature outside the home
        // System.out.println("Modify the temperature outside the home");
        // dashboard.getExternalTemperature();
        // System.out.println("------------------------------");

        // Use Case: Block window movements by putting an arbitrary object
        // dashboard.blockWindow(window1);
        // anSHC.userAction(father, closeWindow1);
        // dashboard.unblockWindow(window1);
        // anSHC.userAction(father, closeWindow1);
    }
    
}

// coordinate system (check wade3's branch)
// date and time increment by hour
// temperature make it change by every hour, by incrementing/decrementing with a small random number
// make sure all tests are through the command pattern