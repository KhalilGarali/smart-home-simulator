package main.java.logic.observerPattern;

public interface Observable {
    void notifyObservers();
    void addObserver(Observer o);
    void removeObserver(Observer o);
}
