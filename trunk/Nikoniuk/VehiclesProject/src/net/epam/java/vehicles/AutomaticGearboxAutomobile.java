/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

/**
 * @author Alexander Nikoniuk
 *
 */
public class AutomaticGearboxAutomobile extends Automobile {
  
	/**
     * Changes engine's gear(used for supporting identical interface of class Automobile for automatic and non-automatic gearboxes)
     * @param newGear
     * 			new car's gear
	 * @throws VehicleException
	 * 			This option is not supported for user of automatic gearbox 
	 */
	@Override
	public void changeGear(int newGear) throws VehicleException {
		throw new VehicleException("Automatic gearbox: changing gear is not supported");		
	}
	
    /**
     * Increases car's speed
	 * @throws VehicleException
	 */
	@Override
	public void increaseSpeed() throws VehicleException {
		int newGear = gearFromSpeed(engine.getSpeed() + engine.SPEED_INCREACEMENT); 
		engine.changeGear(newGear);
		super.increaseSpeed();
	}
	
    /**
     * Decreases car's speed
	 * @throws VehicleException
	 */
	@Override
	public void decreaseSpeed()  throws VehicleException {
		int newGear = gearFromSpeed(engine.getSpeed() - engine.SPEED_DECREACEMENT); 
		engine.changeGear(newGear);
		super.decreaseSpeed();
	}

}
