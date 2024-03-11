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
        Room masterBedroom = shs.makeBedRoom("Master bedroom");
        Room kidsBedroom = shs.makeBedRoom("kid's bedroom");
        Room garage = shs.makeGarage("my garage");
        Room bathroom1 = shs.makeBathroom("u bathroom 1");
        Room bathroom2 = shs.makeBathroom("d bathroom 2");
        Room livingRoom = shs.makeLivingRoom("living room");
        Room porch = shs.makePorch("porch");
        Room basement = shs.makeBasement("basement");

        Opening kitchenWindow = shs.makeWindow("kitchen Window");
        Opening window2 = shs.makeWindow("Window 2");
        Opening masterWindow = shs.makeWindow("master Window");
        Opening kidsWindow = shs.makeWindow("kids Window");
        Opening livingRoomWindow = shs.makeWindow("living Room Window");
        Opening basementWindow = shs.makeWindow("basement Window");
        Opening uBathroomWindow = shs.makeWindow("uBathroom Window");
        Opening dBathroomWindow = shs.makeWindow("dBathroom Window");

        Opening garageDoor = shs.makeDoor("garage door");
        Opening livingRoomDoor = shs.makeDoor("living Room Door");
        Opening uBathroomDoor = shs.makeDoor("u Bathroom Door");
        Opening dBathroomDoor = shs.makeDoor("d Bathroom Door");
        Opening basementDoor = shs.makeDoor("basement Door");
        Opening masterDoor = shs.makeDoor("master bedroom Door");
        Opening kidsDoor = shs.makeDoor("Kids bedroom Door");

        kitchen.setWindow((Window) kitchenWindow);
        masterBedroom.setWindow((Window) masterWindow);
        kidsBedroom.setWindow((Window) kidsWindow);
        livingRoom.setWindow((Window) livingRoomWindow);
        basement.setWindow((Window) basementWindow);
        bathroom1.setWindow((Window) uBathroomWindow);
        bathroom2.setWindow((Window) dBathroomWindow);

        garage.setDoor((Door) garageDoor);
        masterBedroom.setDoor((Door) masterDoor);
        kidsBedroom.setDoor((Door) kidsDoor);
        livingRoom.setDoor((Door) livingRoomDoor);
        basement.setDoor((Door) basementDoor);
        bathroom1.setDoor((Door) uBathroomDoor);
        bathroom2.setDoor((Door) dBathroomDoor);

        User father = shs.makeParent("John");
        User child = shs.makeChild("Joseph");
        User guest = shs.makeGuest("Julie");
        User cousin = shs.makeFamilyMember("Jordan");
        User stranger = shs.makeStranger("J...");

        shs.enterRoom(father, masterBedroom);
        shs.enterRoom(child, kidsBedroom);

        OpenAWindow openKitchenWindow = shs.makeOpenAWindow(kitchen, 1);
        OpenAWindow openMasterWindow = shs.makeOpenAWindow(masterBedroom, 1);
        OpenAWindow openKidsWindow = shs.makeOpenAWindow(kidsBedroom, 1);
        OpenAWindow openBasementWindow = shs.makeOpenAWindow(basement, 1);
        OpenAWindow openLivingWindow = shs.makeOpenAWindow(livingRoom, 1);
        OpenAWindow openUBathroomWindow = shs.makeOpenAWindow(bathroom1, 1);
        OpenAWindow openDBathroomWindow = shs.makeOpenAWindow(bathroom2, 1);

        shs.enterRoom(child, kitchen);

        shs.doAction(father,openDBathroomWindow, kitchen);
        shs.doAction(child,openBasementWindow, kitchen);
        shs.doAction(child, openKidsWindow, kidsBedroom);






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
