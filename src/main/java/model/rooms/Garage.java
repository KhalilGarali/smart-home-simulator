package main.java.model.rooms;

import main.java.logic.users.User;

public class Garage extends Room{

    public Garage(String name){
        super(name);
    }

    @Override
    public String toString() {
        return getName() + super.toString();
    }

}


