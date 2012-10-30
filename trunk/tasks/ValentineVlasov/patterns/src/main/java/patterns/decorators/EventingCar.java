package patterns.decorators;

import patterns.Events.*;
import patterns.Events.EventListeners.*;
import patterns.controllers.ChargeController;
import patterns.controllers.EngineController;
import patterns.controllers.FuelController;
import patterns.controllers.interfaces.IGearController;
import patterns.models.Car;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/29/12
 * Time: 7:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class EventingCar extends Car implements IEngineEventListener, IGearEventListener, IFuelEventListener, IBatteryEventListener, ISpeedEventListener {
    public EventingCar(EngineController engineController, IGearController gearController, ChargeController chargeController, FuelController fuelController) {
        super(engineController, gearController, chargeController, fuelController);

        engineController.addEventListener(this);
        gearController.addEventListener(this);
        chargeController.addEventListener(this);
        fuelController.addEventListener(this);
        this.addSpeedEventListener(this);
    }

    @Override
    public void lowCharge(BatteryEvent e) {
        System.out.println("Battery low! Charge level: " + e.getBattery().getCharge());
    }

    @Override
    public void powerIncreased(EngineEvent e) {
        System.out.println("Power increased to: " + e.getEngine().getPower());
    }

    @Override
    public void powerDecreased(EngineEvent e) {
        System.out.println("Power decreased to: " + e.getEngine().getPower());
    }

    @Override
    public void powerStarted(EngineEvent e) {
        System.out.println("Power started: " + e.getEngine().getPower());
    }

    @Override
    public void powerStopped(EngineEvent e) {
        System.out.println("Power Stopped: " + e.getEngine().getPower());
    }

    @Override
    public void lowFuel(FuelEvent e) {
        System.out.println("Low fuel level: " + e.getFuelLevel());
    }

    @Override
    public void noFuel(FuelEvent e) {
        System.out.println("No more fuel: " + e.getFuelLevel());
    }

    @Override
    public void gearIncreased(GearEvent e) {
        System.out.println("Gear increased to: " + e.getGear().getPosition());
    }

    @Override
    public void gearDecreased(GearEvent e) {
        System.out.println("Gear decreased to: " + e.getGear().getPosition());
    }

    @Override
    public void gearSetToBack(GearEvent e) {
        System.out.println("Gear set to back: " + e.getGear().getPosition());
    }

    @Override
    public void increased(SpeedEvent e) {
        System.out.println("Speed increased to: " + e.getSpeed());
    }

    @Override
    public void decreased(SpeedEvent e) {
        System.out.println("Speed decreased to: " + e.getSpeed());
    }
}
