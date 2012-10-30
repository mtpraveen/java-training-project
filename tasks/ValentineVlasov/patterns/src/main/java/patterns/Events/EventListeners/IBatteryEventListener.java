package patterns.Events.EventListeners;

import patterns.Events.BatteryEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:44 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IBatteryEventListener {
    void lowCharge(BatteryEvent e);
}
