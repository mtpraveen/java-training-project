package patterns.Events.EventListeners;

import patterns.Events.EngineEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IEngineEventListener {
    void powerIncreased(EngineEvent e);
    void powerDecreased(EngineEvent e);
    void powerStarted(EngineEvent e);
    void powerStopped(EngineEvent e);
}
