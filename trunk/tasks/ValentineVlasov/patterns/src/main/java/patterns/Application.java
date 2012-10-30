package patterns;

import patterns.builders.CarBuilder;
import patterns.builders.GearBuilder;
import patterns.controllers.AutoGearController;
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
 * Time: 7:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Application {
    public static void main(String[] args){
        CarBuilder carBuilder = new CarBuilder();

        Car car = carBuilder.buildCar(GearType.AUTO, true);
        System.out.println("\nCar with automatic gear controller test drive:");
        car.testDrive();

        car = carBuilder.buildCar(GearType.MANUAL, true);
        System.out.println("\nCar with manual gear controller test drive:");
        car.testDrive();
    }
}
