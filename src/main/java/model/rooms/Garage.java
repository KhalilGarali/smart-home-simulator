package main.java.model.rooms;

import main.java.logic.users.User;
import main.java.model.fixtures.HVAC;
import main.java.model.rooms.zones.CommonZone;
import main.java.model.rooms.zones.GatewayZone;

public class Garage extends Room{

    public Garage(String name){
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


