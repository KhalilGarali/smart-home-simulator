package main.java.logic.modules;

import javax.swing.*;
import main.java.gui.HomeSimulatorFrame;
import main.java.gui.LogInFrame;
import main.java.model.rooms.Room;

public class main {

    // this won't be the main function anymore, it will be the client.
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            LogInFrame logInF = new LogInFrame();
            logInF.setVisible(true);
        });

    }
    
}
