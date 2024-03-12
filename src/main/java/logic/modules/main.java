package main.java.logic.modules;

import javax.swing.*;
import main.java.gui.HomeSimulatorFrame;
import main.java.logic.commands.Command;
import main.java.logic.commands.CommandFactory;
import main.java.logic.commands.change.ChangeTemperature;
import main.java.logic.commands.off.TurnAutoLightOff;
import main.java.logic.commands.off.TurnCoolingOff;
import main.java.logic.commands.off.TurnHeatingOff;
import main.java.logic.commands.off.TurnLightOff;
import main.java.logic.commands.on.TurnCoolingOn;
import main.java.logic.commands.on.TurnHeatingOn;
import main.java.logic.commands.on.TurnLightOn;
import main.java.logic.commands.open.*;
import main.java.logic.modules.SHS;
import main.java.logic.users.*;
import main.java.model.openings.*;
import main.java.model.rooms.*;

import java.util.*;

public class main {

    // this won't be the main function anymore, it will be the client.
    public static void main(String[] args){
        // SwingUtilities.invokeLater(() -> {
        //     HomeSimulatorFrame frame = new HomeSimulatorFrame();
        //     frame.setVisible(true);
        // });

        SHS shs = SHS.getInstance();

        //FIXME all this work will be done by the Layout class
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
        Opening kitchenDoor = shs.makeDoor("kitchen door");

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
        kitchen.setDoor((Door) kitchenDoor);

        User father = shs.makeParent("John");
        father.moveToRoom(kitchen);
        User child = shs.makeChild("Joseph");
        User guest = shs.makeGuest("Julie");
        User cousin = shs.makeFamilyMember("Jordan");
        User stranger = shs.makeStranger("Jamal");

        // examples for the command factory application
        Command openKitchenWindow1 = shs.cf.createCommand("openawindow",kitchen, 1);
        // **************************************
        OpenAWindow openKitchenWindow = shs.makeOpenAWindow(kitchen, 1);
        OpenAWindow openMasterWindow = shs.makeOpenAWindow(masterBedroom, 1);
        OpenAWindow openKidsWindow = shs.makeOpenAWindow(kidsBedroom, 1);
        OpenAWindow openBasementWindow = shs.makeOpenAWindow(basement, 1);
        OpenAWindow openLivingWindow = shs.makeOpenAWindow(livingRoom, 1);
        OpenAWindow openUBathroomWindow = shs.makeOpenAWindow(bathroom1, 1);
        OpenAWindow openDBathroomWindow = shs.makeOpenAWindow(bathroom2, 1);
        OpenADoor openKitchenDoor = shs.makeOpenADoor(kitchen,1);
        TurnLightOn turnLivingLightOn = shs.makeTurnLightOn(livingRoom);
        TurnLightOff turnKitchenLightOff = shs.makeTurnLightOff(kitchen);
        TurnCoolingOn turnMasterCoolingOn = shs.makeTurnCoolingOn(masterBedroom);
        TurnCoolingOff turnMasterCoolingOff = shs.makeTurnCoolingOff(masterBedroom);
        TurnHeatingOn turnMasterHeatingOn = shs.makeTurnHeatingOn(masterBedroom);
        TurnHeatingOff turnMasterHeatingOff = shs.makeTurnHeatingOff(masterBedroom);
        TurnAutoLightOff turnKitchenAutoLightOff = shs.makeTurnAutoLightOff(kitchen);
        ChangeTemperature changeLivingTemperature = shs.makeChangeTemperature(livingRoom, 27);

        //tester for moving a user form room using the new command
        System.out.println("\n----------------------------------------");
        System.out.println("kitchen has " + kitchen.getUserFromRoom());
        father.moveToRoom(null);
        System.out.print("kitchen has " + kitchen.getUserFromRoom());
        System.out.print("\n----------------------------------------");
        // end of test

        //test for open window
        bathroom1.setDesiredTemperature(25);
        bathroom1.setCurrentTemperature(57);

        shs.shcDoAction(father, turnKitchenAutoLightOff, kitchen);
        shs.shcDoAction(father, openKitchenWindow1, kitchen);
        shs.shcDoAction(father, openKitchenDoor, kitchen);
        father.moveToRoom(kitchen);
        shs.shcDoAction(father, changeLivingTemperature, livingRoom);
        shs.shhDoAction(turnMasterHeatingOn, bathroom1);

        //FIXME to be moved to shc and called from shs. Wade
        // shs.doAction(father,openDBathroomWindow, kitchen);
        // shs.doAction(child,openBasementWindow, kitchen);
        // shs.doAction(father, turnLivingLightOn, kidsBedroom);
        // shs.doAction(father, turnKitchenLightOff, kidsBedroom);
        // shs.doAction(father, turnMasterCoolingOn, kidsBedroom);
        // shs.doAction(father, turnMasterCoolingOff, kidsBedroom);
        // shs.doAction(father, turnMasterHeatingOn, kidsBedroom);
        // shs.doAction(father, turnMasterHeatingOff, kidsBedroom);
        // shs.doAction(father, changeLivingTemperature, kidsBedroom);
        // shs.doAction(father, openUBathroomWindow, kidsBedroom);
    }
    
}
