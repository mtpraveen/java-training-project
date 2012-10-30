package patterns.Events.EventListeners;

import patterns.Events.GearEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:50 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IGearEventListener {
    void gearIncreased(GearEvent e);
    void gearDecreased(GearEvent e);
    void gearSetToBack(GearEvent e);
}
