package patterns.controllers;

import patterns.Events.BatteryEvent;
import patterns.Events.EventListeners.IBatteryEventListener;
import patterns.models.Battery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChargeController {
    private Battery battery;
    private List<IBatteryEventListener> eventListeners;

    public ChargeController(Battery battery){
        this.battery = battery;
        eventListeners = new ArrayList<IBatteryEventListener>();
    }

    public void setBatteryCharge(int batteryCharge){
        battery.setCharge(batteryCharge);
        if(batteryCharge <= 5)
            fireLowCharge();
    }

    public int  getBatteryCharge(){
        return battery.getCharge();
    }

    public void addEventListener(IBatteryEventListener listener){
        if(!eventListeners.contains(listener))
            eventListeners.add(listener);
    }

    public void removeEventListener(IBatteryEventListener listener){
        if(eventListeners.contains(listener))
            eventListeners.remove(listener);
    }

    public void fireLowCharge(){
        for(IBatteryEventListener listener : eventListeners){
            listener.lowCharge(new BatteryEvent(this, battery));
        }
    }
}
