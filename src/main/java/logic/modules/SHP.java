package main.java.logic.modules;

import main.java.logic.commands.Command;
import main.java.logic.observerPattern.Observable;
import main.java.logic.users.User;
import main.java.model.rooms.Room;

public class SHP extends Module{
    private SHC shc;
    private static SHP shp;

    private SHP(SHC shc){
        this.shc = shc;
    }
    public static SHP getInstance(SHC shc){
        if(shp == null){
            shp = new SHP(shc);
        }
        return shp;
    }
    public void doAction(Command command, Room room){
        shc.moduleAction(command, room);
    }

    @Override
    public void update(Observable o){

    }
}
