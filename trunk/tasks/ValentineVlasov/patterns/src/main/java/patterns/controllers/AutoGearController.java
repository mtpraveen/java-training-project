package patterns.controllers;

import patterns.Events.EventListeners.IGearEventListener;
import patterns.Events.GearEvent;
import patterns.controllers.interfaces.IGearController;
import patterns.models.Gear;
import patterns.models.enums.GearType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class AutoGearController implements IGearController {
    private Gear gear;
    private List<IGearEventListener> eventListeners;

    public AutoGearController(){
        gear = new Gear(GearType.AUTO);
        eventListeners = new ArrayList<IGearEventListener>();
    }

    @Override
    public void increase() {
        if(gear.getPosition() < gear.maxPosition){
            gear.setPosition(gear.getPosition() + 1);
            fireIncrease();
        }
    }

    @Override
    public void decrease() {
        if(gear.getPosition() > gear.minPosition){
            gear.setPosition(gear.getPosition() - 1);
            fireDecrease();
        }
    }

    @Override
    public void back() {
        if(gear.getPosition() != 0){
            gear.setPosition(0);
            fireBack();
        }
    }

    @Override
    public int getGear() {
        return gear.getPosition();
    }

    public void addEventListener(IGearEventListener listener){
        if(!eventListeners.contains(listener))
            eventListeners.add(listener);
    }

    public void removeEventListener(IGearEventListener listener){
        if(eventListeners.contains(listener))
            eventListeners.remove(listener);
    }

    private void fireIncrease(){
        for(IGearEventListener listener : eventListeners)
            listener.gearIncreased(new GearEvent(this, gear));
    }

    private void fireDecrease(){
        for(IGearEventListener listener : eventListeners)
            listener.gearDecreased(new GearEvent(this, gear));
    }

    private void fireBack(){
        for(IGearEventListener listener: eventListeners)
            listener.gearSetToBack(new GearEvent(this, gear));
    }
}
