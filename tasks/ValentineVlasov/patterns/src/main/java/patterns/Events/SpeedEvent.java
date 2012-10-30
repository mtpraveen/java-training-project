package patterns.Events;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/29/12
 * Time: 7:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpeedEvent extends EventObject {
    int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public SpeedEvent(Object source, int speed) {
        super(source);
        this.speed = speed;
    }
}
