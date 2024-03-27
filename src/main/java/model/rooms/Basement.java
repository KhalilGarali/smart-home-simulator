package main.java.model.rooms;

import main.java.logic.users.User;
import main.java.model.fixtures.HVAC;
import main.java.model.rooms.zones.CommonZone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

public class Basement extends Room{
    User user;
    public Basement(String name){
        super(name);
        this.zone = CommonZone.getInstance();
        this.zone.addRoomToZone(this);
        this.installHVAC(new HVAC(this));
    }

    @Override
    public String toString() {
        return getName() + super.toString();
    }
}



