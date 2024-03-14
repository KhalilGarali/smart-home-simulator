package main.java.logic.users;

import main.java.model.rooms.Room;

public class FamilyMember extends User{
    public FamilyMember(String name) {
        super(name);
    }
    public FamilyMember(String name, Room room)
    {
        super(name, room);
    }
}
