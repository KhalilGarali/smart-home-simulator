package main.java.model.openings;

public class Window implements Opening {
    private boolean open = false;
    private boolean isBlocked = false;
    private String name;

    public Window(String name){
        this.name = name;
    }

    @Override
    public void open() {
        if (this.isBlocked == false) {
            open = true;
        } else {
            System.out.println("Cannot perform action. Window is currently blocked from movement.");
        } 
    }

    @Override
    public void close() {
        if (this.isBlocked == false) {
            open = false;
        } else {
            System.out.println("Cannot perform action. Window is currently blocked from movement.");
        } 
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public String toString() {
        return "Window [open=" + open + "]";
    }

    public void blockWindow() {
        this.isBlocked = true;
    }

    public void unblockWindow() {
        this.isBlocked = false;
    }
}