/**
 * The code is licensed under GPL v.3.
 */

package net.epam.java.vehicles;

class VehicleException extends Exception {
	public VehicleException(String message) {
		super(message);
	}
}

class EmptyTankException extends VehicleException {
	public EmptyTankException(String message) {
		super(message);
	}
}

class EngineOutOfOrderException extends VehicleException {
	public EngineOutOfOrderException(String message) {
		super(message);
	}
}

class GearBoxException extends VehicleException {
	public GearBoxException(String message) {
		super(message);
	}
}