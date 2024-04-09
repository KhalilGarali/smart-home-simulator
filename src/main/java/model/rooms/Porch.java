package main.java.model.rooms;

import main.java.logic.users.User;
import main.java.model.fixtures.HVAC;
import main.java.model.rooms.zones.CommonZone;
import main.java.model.rooms.zones.GatewayZone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

public class Porch extends Room{
    User user;
    public Porch(String name){
        super(name);
        this.zone = GatewayZone.getInstance();
        this.zone.addRoomToZone(this);
        this.installHVAC(new HVAC(this)); 
    }

    @Override
    public String toString() {
        return getName() + super.toString();
    }

}


