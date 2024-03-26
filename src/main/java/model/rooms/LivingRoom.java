package main.java.model.rooms;

import main.java.logic.users.User;
import main.java.model.fixtures.HVAC;
import main.java.model.rooms.zones.CommonZone;

public class LivingRoom extends Room{

    public LivingRoom(String name){
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

