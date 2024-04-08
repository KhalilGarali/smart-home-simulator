package main.java.logic.modules;

import javax.swing.*;
import main.java.gui.LogInFrame;


public class main {

    // this won't be the main function anymore, it will be the client.
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            LogInFrame logInF = new LogInFrame();
            logInF.setVisible(true);
        });

    }
    
}
