package main.java.logic.users;

public class Stranger extends User{
    public Stranger(String name){
        super(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stranger " + this.name;
    }
    
}
