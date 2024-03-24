package main.java.logic.users;

public class Admin extends User{
    public Admin(String name){
        super(name);

        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.TEMP);
    }
}
