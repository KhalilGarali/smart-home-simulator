package main.java.model.rooms;

import main.java.logic.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

public class Porch extends Room{
    User user;
    public Porch(String name){
        super(name);
    }

    @Override
    public String toString() {
        return getName() + super.toString();
    }

}


