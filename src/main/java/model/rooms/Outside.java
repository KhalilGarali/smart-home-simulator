package main.java.model.rooms;

import main.java.model.fixtures.HVAC;
import main.java.model.rooms.zones.OutSideZone;

public class Outside extends Room{

    public Outside(String name) {
        super(name);
        this.zone = OutSideZone.getInstance();
        this.zone.addRoomToZone(this);
        this.installHVAC(new HVAC(this));
    }
    
}
