package main.java.logic.users;

import main.java.model.rooms.Room;

public class Stranger extends User{

    public Stranger(){
        super();
    }
    public Stranger(String name){
        super(name);
    }

    public Stranger(String name, Room room)
    {
        super(name, room);
    }

    @Override
    public String toString() {
        return "Stranger " + this.name;
    }
    
}
