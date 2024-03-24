/**
 * parent class for all user classes
 * contains the name and the list of permissions for every class
 */

package main.java.logic.users;
import main.java.model.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    List<Permissions> permissions;
    String name;
    Room room = null;

    public User(){
        permissions = new ArrayList<>();
    }

    //non-default constructor
    public User(String name){
        permissions = new ArrayList<>();
        this.name = name;
    }
    public User(String name, Room room){
        permissions = new ArrayList<>();
        this.name = name;
        this.room = room;
        room.addUserToRoom(this);
    }



    // PERMISSION MANAGEMENT FUNCTIONS
    // check the user has a specific permission, function to be used in SHC
    public Boolean hasPermission(Permissions permission){
        return permissions.contains(permission);
    }
    // get user's permissions
    public List<Permissions> getPermissions() {
        return permissions;
    }
    // assign user permissions
    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }


    //TODO check with Wade if it's cool!
    /**  when user changes rooms:
    * - if he's in a room he leaves it and enters the new room and update console
    * - if he's outside he enters the room and update console.
    */ 
    public void moveToRoom(Room toRoom){
        if(this.room != null){
            exitRoom();
            enterRoom(toRoom);  
            if (toRoom != null){
                System.out.println(name + " is in this room now: " + this.room.getName());
            } else {
                System.out.println(name + " has now left the house.");
            }     
        }
        else{
            enterRoom(toRoom);
            if (toRoom != null){
                System.out.println(name + " is in this room now: " + this.room.getName());       
            }
        }
    }

    /**  when user enters the room:
    * - the room is assigned as his room
    * - update the console
    * - update the room with the new user inside it
    * - turn the light on in the room only if it was off before
    */ 
    private void enterRoom(Room room){
        this.room = room;
        if (room !=null){
            System.out.println(this.name + " is entering " + this.room.getName());
            room.addUserToRoom(this);
            room.turnLightOn();  
        }
    }
    
    /**  when user leaves the room he's already in:
    * - he's removed from the user list in the room
    * - try to turn light off in the room is user was last to leave
    * - set the user's current room to null (incase they are away from the house)
    */ 
    public void exitRoom(){
        System.out.println(this.name + " is exiting " + this.room.getName());
        this.room.removeUserFromRoom(this);
        this.room.turnLightOff();
        this.room = null; //FIXME needed if the user leaves the HOUSE 
    }

    // getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Room getRoom() {
        return room;
    }
}
