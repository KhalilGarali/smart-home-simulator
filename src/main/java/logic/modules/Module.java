package main.java.logic.modules;

import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;

public abstract class Module implements Observer{
    @Override
    public abstract void update(Observable o);
}
