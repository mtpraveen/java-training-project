package patterns.models;

import patterns.Events.*;
import patterns.Events.EventListeners.*;
import patterns.controllers.ChargeController;
import patterns.controllers.EngineController;
import patterns.controllers.FuelController;
import patterns.controllers.interfaces.IGearController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 6:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Car {
    private int speed;
    List<ISpeedEventListener> speedEventListeners;

    private EngineController engineController;
    private IGearController gearController;
    private ChargeController chargeController;
    private FuelController fuelController;

    public Car(EngineController engineController, IGearController gearController, ChargeController chargeController, FuelController fuelController) {
        this.engineController = engineController;
        this.gearController = gearController;
        this.chargeController = chargeController;
        this.fuelController = fuelController;
        speedEventListeners = new ArrayList<ISpeedEventListener>();
    }

    public int getSpeed(){
        return speed;
    }

    private void setSpeed(int speed){
        int currentSpeed = new Integer(this.speed);
        if(currentSpeed > speed){
            this.speed = speed;
            fireSpeedDecreased();
        }
        else{
            this.speed = speed;
            fireSpeedIncreased();
        }

    }

    private void updateSpeed(){
        int gearPosition = gearController.getGear();
        int power = engineController.getPower();
        int speed = 0;
        switch (gearPosition) {
            case 1: {
                speed = 0;
                break;
            }
            case 2: {
                speed = 40;
                break;
            }
            case 3: {
                speed = 80;
                break;
            }
            case 4: {
                speed = 120;
                break;
            }
            case 5: {
                speed = 160;
                break;
            }
        }
        speed = speed + getGearPower(power) * 10;
        setSpeed(speed);
    }

    private int getGearPower(int power){
        switch (power){
            case 1250:{return 1;}
            case 2500:{return 2;}
            case 3750:{return 3;}
            case 5000:{return 4;}
        }
        return 0;
    }

    public void setBatteryCharge(int batteryCharge){
        chargeController.setBatteryCharge(batteryCharge);
    }

    public void setFuelLevel(int fuelLevel){
        fuelController.setFuelLevel(fuelLevel);
    }

    public void start(){
        if(chargeController.getBatteryCharge() > 5 && fuelController.getFuelLevel() > 0)
            engineController.start();
    }

    public void stop(){
        engineController.stop();
    }

    public void acceleration(){
        engineController.accelerate();
        updateSpeed();
    }

    public void deceleration(){
        engineController.decelerate();
        updateSpeed();
    }

    public void testDrive(){
        setBatteryCharge(100);
        setFuelLevel(60);
        gearController.increase();
        start();
        while(speed < 130)
            acceleration();
        while (speed > 10)
            deceleration();
        stop();
        setFuelLevel(5);
        setFuelLevel(0);
        setBatteryCharge(0);

    }

    //Events
    public void addSpeedEventListener(ISpeedEventListener listener){
        if(!speedEventListeners.contains(listener))
            speedEventListeners.add(listener);
    }

    public void removeSpeedEventListener(ISpeedEventListener listener){
        if(speedEventListeners.contains(listener))
            speedEventListeners.remove(listener);
    }

    private void fireSpeedIncreased(){
        for(ISpeedEventListener listener : speedEventListeners)
            listener.increased(new SpeedEvent(this, speed));
    }

    private void fireSpeedDecreased(){
        for(ISpeedEventListener listener : speedEventListeners)
            listener.decreased(new SpeedEvent(this, speed));
    }
}
