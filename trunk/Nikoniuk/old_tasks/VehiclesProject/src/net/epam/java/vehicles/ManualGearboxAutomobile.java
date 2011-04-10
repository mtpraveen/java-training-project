/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

/**
 * @author Alexander Nikoniuk
 *
 */
public class ManualGearboxAutomobile extends Automobile {

    /**
     * Changes engine's gear
     * @param newGear
     * 			new car's gear
	 * @throws VehicleException
	 */
	@Override
	public void changeGear(int newGear) throws VehicleException {
		engine.changeGear(newGear);		
		move();
	}

}
