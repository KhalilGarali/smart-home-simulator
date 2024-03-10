package main.java.logic.commands;

import main.java.logic.users.Permissions;

public interface Command {
    public final Permissions REQUIRED_PERMISSIONS = Permissions.WINDOW;

    public Boolean execute();

    public Permissions requirePermissions();
}
