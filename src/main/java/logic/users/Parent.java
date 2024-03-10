package main.java.logic.users;
 
public class Parent extends FamilyMember{
    public Parent(String name){
        this.name = name;

        permissions.add(Permissions.WINDOW);
        permissions.add(Permissions.DOOR);
        permissions.add(Permissions.LIGHT);
        permissions.add(Permissions.TEMP);
    }

    @Override
    public String toString() {
        return "Parent " + this.name;
    }
    
}
