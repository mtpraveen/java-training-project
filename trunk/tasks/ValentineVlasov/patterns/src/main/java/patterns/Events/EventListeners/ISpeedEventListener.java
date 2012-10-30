package patterns.Events.EventListeners;

import patterns.Events.SpeedEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/29/12
 * Time: 7:22 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ISpeedEventListener {
    void increased(SpeedEvent e);
    void decreased(SpeedEvent e);
}
