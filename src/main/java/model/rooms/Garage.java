package main.java.model.rooms;

public class Garage extends Room{
    public Garage(String name){
        super(name);
    }

    @Override
    public String toString() {
        return name + super.toString();
    }
}
