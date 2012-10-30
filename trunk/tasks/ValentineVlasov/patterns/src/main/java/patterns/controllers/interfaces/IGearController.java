package patterns.controllers.interfaces;

import patterns.Events.EventListeners.IGearEventListener;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:14 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IGearController {
    void increase();
    void decrease();
    void back();

    void addEventListener(IGearEventListener listener);
    void removeEventListener(IGearEventListener listener);

    int getGear();
}
