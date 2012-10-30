package patterns.models;

import patterns.models.enums.GearType;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class Gear {
    public static int maxPosition = 5;
    public static int minPosition = 1;

    private GearType gearType;
    private int position;

    public Gear(GearType gearType) {
        this.gearType = gearType;
        position = 0;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
