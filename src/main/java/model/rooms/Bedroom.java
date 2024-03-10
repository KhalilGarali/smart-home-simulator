package main.java.model.rooms;

public class Bedroom extends Room{
    public Bedroom(String name){
        super(name);
    }

    @Override
    public String toString() {
        return name + super.toString();
    }
}
