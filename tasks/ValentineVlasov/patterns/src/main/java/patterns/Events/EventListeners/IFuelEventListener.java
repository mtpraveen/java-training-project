package patterns.Events.EventListeners;

import patterns.Events.FuelEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/29/12
 * Time: 4:53 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IFuelEventListener {
    void lowFuel(FuelEvent e);
    void noFuel(FuelEvent e);
}
