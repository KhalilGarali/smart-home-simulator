package main.java.model.rooms;

import main.java.logic.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

public class Basement extends Room{
    User user;
    public Basement(String name){
        super(name);
    }

    @Override
    public String toString() {
        return "Bathroom" + super.toString();
    }

}



