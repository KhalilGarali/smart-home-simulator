package main.java.model.rooms;

public class Bathroom extends Room{
    public Bathroom(String name){
        super(name);
    }

    @Override
    public String toString() {
        return name + super.toString();
    }
}
