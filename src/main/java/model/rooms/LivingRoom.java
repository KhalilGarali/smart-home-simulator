package main.java.model.rooms;

import main.java.logic.users.User;

public class LivingRoom extends Room{

    public LivingRoom(String name){
        super(name);
    }

    @Override
    public String toString() {
        return getName() + super.toString();
    }

}

