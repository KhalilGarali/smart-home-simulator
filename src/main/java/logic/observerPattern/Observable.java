package main.java.logic.observerPattern;

public interface Observable {
    void notifyObserver();
    void addObserver(Observer o);
    void removeObserver(Observer o);
}
