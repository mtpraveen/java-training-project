package patterns.Events;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/29/12
 * Time: 4:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class FuelEvent extends EventObject {
    private int fuelLevel;

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public FuelEvent(Object source, int fuelLevel) {
        super(source);
        this.fuelLevel = fuelLevel;
    }
}
