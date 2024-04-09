package main.java.model.openings;

public class Door implements Opening {
    private boolean open = false;
    private String name;

    public Door(String name){
        this.name = name;
    }
    @Override
    public void open() {
        open = true;
    }

    @Override
    public void close() {
        open = false;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public String toString() {
        return "Door [open=" + open + "]";
    }
    
}
