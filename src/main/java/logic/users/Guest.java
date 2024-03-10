package main.java.logic.users;

public class Guest extends User{
    public Guest(String name){
        this.name = name;

        permissions.add(Permissions.LIGHT);
    }
    @Override
    public String toString() {
        return "Guest " + this.name;
    }
}
