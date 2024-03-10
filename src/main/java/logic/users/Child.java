package main.java.logic.users;

public class Child extends FamilyMember{
    public Child(String name){
        this.name = name;

        permissions.add(Permissions.LIGHT);
    }
    @Override
    public String toString() {
        return "Child " + this.name;
    }
}
