package main.java.logic.modules;

import javax.swing.*;
import main.java.gui.HomeSimulatorFrame;
import main.java.gui.LogInFrame;
import main.java.model.rooms.Room;

public class main {

    // this won't be the main function anymore, it will be the client.
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            HomeSimulatorFrame frame = new HomeSimulatorFrame();
            LogInFrame logInF = new LogInFrame(frame);
            //logInF.setVisible(true);
            frame.setVisible(true);
        });

        SHS shs = SHS.getInstance();
        for (Room room: shs.getHouseLayout())
        {
            System.out.println(room.getName());
        }
        shs.getHouseLayout().get(1).getZone().setZoneTemperature(30);
    }
    
}
