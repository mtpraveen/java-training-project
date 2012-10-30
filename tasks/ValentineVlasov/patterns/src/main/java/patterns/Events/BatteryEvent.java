package patterns.Events;

import patterns.models.Battery;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class BatteryEvent extends EventObject {
    private Battery battery;

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public BatteryEvent(Object source, Battery battery) {
        super(source);
        this.battery = battery;
    }
}
