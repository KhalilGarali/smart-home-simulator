package modules;

import observerPattern.Observer;
import observerPattern.Observable;

public abstract class Module implements Observer{
    @Override
    public abstract void update(Observable o);
}
