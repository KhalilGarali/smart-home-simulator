package main.java.model.openings;

public class Window implements Opening {
    private boolean open = false;

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
        return "Window [open=" + open + "]";
    }
}