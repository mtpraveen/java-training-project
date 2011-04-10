/**
 * The code is licensed under GPL v.3.
 */
package net.epam.java.vehicles;

public interface IVehicle {
	void increaseSpeed() throws VehicleException;
	void decreaseSpeed() throws VehicleException;
	void brake() throws VehicleException;
	void move() throws VehicleException;
	double distance();	
}
