package patterns.Events;

import patterns.models.Gear;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class GearEvent extends EventObject {
    private Gear gear;

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public GearEvent(Object source, Gear gear){
        super(source);
        this.gear = gear;
    }
}
