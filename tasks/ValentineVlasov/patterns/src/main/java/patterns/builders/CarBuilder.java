package patterns.builders;

import patterns.controllers.ChargeController;
import patterns.controllers.EngineController;
import patterns.controllers.FuelController;
import patterns.controllers.interfaces.IGearController;
import patterns.decorators.EventingCar;
import patterns.models.Battery;
import patterns.models.Car;
import patterns.models.Engine;
import patterns.models.enums.GearType;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class CarBuilder {
    private GearBuilder gearBuilder;

    public Car buildCar(GearType gearType, boolean supportsEventing) {
        this.gearBuilder = new GearBuilder();

        IGearController gearController = gearBuilder.getGearController(gearType);
        EngineController engineController = new EngineController(new Engine(), gearController);
        ChargeController chargeController = new ChargeController(new Battery());
        FuelController fuelController = new FuelController();
        if(!supportsEventing)
        if(!supportsEventing)
            return new Car(engineController,gearController,chargeController,fuelController);
        return new EventingCar(engineController, gearController, chargeController, fuelController);
    }
}
