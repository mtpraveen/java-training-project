package patterns.controllers;

import patterns.Events.EventListeners.IFuelEventListener;
import patterns.Events.FuelEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/29/12
 * Time: 3:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class FuelController {
    private int fuelLevel;
    private List<IFuelEventListener> eventListeners;

    public FuelController() {
        fuelLevel = 0;
        eventListeners = new ArrayList<IFuelEventListener>();
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        if(fuelLevel > 60)
            this.fuelLevel = 60;
        else if(fuelLevel < 0)
            this.fuelLevel = 0;
        else
            this.fuelLevel = fuelLevel;

        if(fuelLevel <= 5 && fuelLevel > 0){
            fireLowFuel();
        }else if(fuelLevel == 0){
            fireNoFuel();
        }
    }

    public void addEventListener(IFuelEventListener listener){
        if(!eventListeners.contains(listener)){
            eventListeners.add(listener);
        }
    }

    public void removeEventListener(IFuelEventListener listener){
        if(eventListeners.contains(listener)){
            eventListeners.remove(listener);
        }
    }

    private void fireLowFuel(){
        for(IFuelEventListener listener : eventListeners){
            listener.lowFuel(new FuelEvent(this, getFuelLevel()));
        }
    }

    private void fireNoFuel(){
        for(IFuelEventListener listener : eventListeners){
            listener.noFuel(new FuelEvent(this, getFuelLevel()));
        }
    }
}
