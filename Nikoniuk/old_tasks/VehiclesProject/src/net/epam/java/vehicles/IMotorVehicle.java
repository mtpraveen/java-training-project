/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

/**
 * @author Alexander Nikoniuk
 *
 */
public interface IMotorVehicle extends IVehicle {

	public void start() throws VehicleException;
	public void stop() throws VehicleException;	
	void changeGear(int newGear) throws VehicleException;
	void refuel();
	void repair();
}
