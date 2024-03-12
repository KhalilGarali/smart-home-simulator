package main.java.logic.commands;

import main.java.logic.commands.change.ChangeTemperature;
import main.java.logic.commands.close.*;
import main.java.logic.commands.off.*;
import main.java.logic.commands.on.*;
import main.java.logic.commands.open.*;
import main.java.logic.modules.SHC;
import main.java.model.rooms.Room;

public class CommandFactory {

    private SHC shc;
    Command command;

    public CommandFactory(SHC m){
        this.shc = m;
    }
    
    public Command createCommand(String commandType, Room room, int param) {
        switch (commandType.toLowerCase()) {
            case "openawindow":
                command = new OpenAWindow(room, param);
                break;
            case "openallwindows":
                command = new OpenAllWindows(room);
                break;
            case "openadoor":
                command = new OpenADoor(room, param);
                break;
            case "openalldoors":
                command = new OpenAllDoors(room);
                break;
            case "openallopenings":
                command = new OpenAllOpenings(room);
                break;
            case "closeawindow":
                command = new CloseAWindow(room, param);
                break;
            case "closeallwindows":
                command = new CloseAllWindows(room);
                break;
            case "closeadoor":
                command = new CloseADoor(room, param);
                break;
            case "closealldoors":
                command = new CloseAllDoors(room);
                break;
            case "closeallopenings":
                command = new CloseAllOpenings(room);
                break;
            case "turnlighton":
                command = new TurnLightOn(room);
                break;
            case "turnlightoff":
                command = new TurnLightOff(room);
                break;
            case "turnautolighton":
                command = new TurnAutoLightOn(room);
                break;
            case "turnautolightoff":
                command = new TurnAutoLightOff(room);
                break;
            case "changetemperature":
                command = new ChangeTemperature(room, param);
                break;
            case "turncoolingon":
                command = new TurnCoolingOn(room);
                break;
            case "turncoolingoff":
                command = new TurnCoolingOff(room);
                break;
            case "turnheatingon":
                command = new TurnHeatingOn(room);
                break;
            case "turnheatingoff":
                command = new TurnHeatingOff(room);
                break;
            default:
                throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
        shc.addCommand(command);
        return command;
    }
}
