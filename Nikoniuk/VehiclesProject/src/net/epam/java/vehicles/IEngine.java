/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

/**
 * @author epam0001
 *
 */
public interface IEngine {
	void consumeFuel() throws VehicleException;
	void changeGear(int newGear) throws VehicleException;
	void start() throws VehicleException;
	void stop(); 
	void increaseSpeed() throws VehicleException;
	void decreaseSpeed();
	int getSpeed();
	void repair();
}
