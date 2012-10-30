package patterns.builders;

import patterns.controllers.AutoGearController;
import patterns.controllers.ManualGearController;
import patterns.controllers.interfaces.IGearController;
import patterns.models.enums.GearType;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class GearBuilder {

    public IGearController getGearController(GearType gearType){
        if(gearType.equals(GearType.AUTO))
            return new AutoGearController();
        return new ManualGearController(GearType.MANUAL);
    }
}
