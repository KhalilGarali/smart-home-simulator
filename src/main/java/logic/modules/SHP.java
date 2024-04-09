package main.java.logic.modules;

import main.java.logic.MediatorPattern.Component;
import main.java.logic.commands.Command;
import main.java.logic.observerPattern.Observable;
import main.java.model.rooms.Room;

// FIXME must also implement the observer pattern to observe the rooms
public class SHP extends Module implements Component{

    private SHC shc;
    private static SHP shp;
    private SHS shs;

    private SHP(SHC ashc){
        this.shc = ashc;
    }

    public static synchronized SHP getInstance(SHC ashc){
        if(shp == null){
            shp = new SHP(ashc);
        }
        return shp;
    }

    public void doAction(Command command, Room room){
        shc.moduleAction(command, room);
    }

    @Override
    public void update(Observable o){

    }

    public void houseIsEmpty() {
        shs.notify(this, "houseIsEmpty");
    }

    public void houseIsNotEmpty() {
        shs.notify(this, "houseIsNotEmpty");
    }

    public void setSHS(SHS shs){
        this.shs = shs;
    }
}
