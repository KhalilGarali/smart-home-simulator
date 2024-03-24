package main.java.logic.commands;

import main.java.logic.users.Permissions;

public abstract class Command {
    public Permissions REQUIRED_PERMISSIONS = Permissions.WINDOW;

    public abstract Boolean execute();

    public abstract Permissions requirePermissions();
}
