package main.java.logic.commands;

import java.util.ArrayList;

import main.java.gui.OutputPanel;
import main.java.logic.users.Permissions;

public abstract class Command {
    public Permissions REQUIRED_PERMISSIONS = Permissions.WINDOW;

    public static OutputPanel outpanel = OutputPanel.getInstance();
    
    public abstract Boolean execute();

    public abstract Permissions requirePermissions();

    public abstract ArrayList<String> toConsole();
}
