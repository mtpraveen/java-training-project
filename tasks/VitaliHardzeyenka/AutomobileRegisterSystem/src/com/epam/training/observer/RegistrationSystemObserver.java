package com.epam.training.observer;

import com.epam.training.carEquipment.Battery;
import com.epam.training.carEquipment.Car;
import com.epam.training.carEquipment.Engine;
import com.epam.training.carEquipment.Equipmment;
import com.epam.training.carEquipment.FuelTank;
import com.epam.training.factoryMethod.AbstractTransmission;

public class RegistrationSystemObserver extends BaseObserver {
	private boolean chargedBattery;
	private boolean isLaunchedBattery;
	private boolean isRunCar;
	private boolean isWindUpEngine;
	private byte fullnessPercentsFuelTank;
	private byte gear;
	private String message;
	
	public Object valueChanged(Equipmment observed) {
		if (observed instanceof Battery) {
			message = "Battery charge status is: " +  ((Battery) observed).getCharged() + ". Launched is: " + ((Battery) observed).getIsLaunched();
			return this.chargedBattery = ((Battery) observed).getCharged();
		} else if (observed instanceof FuelTank) {
			message = "Fuel tank fullness by persends is: " + ((FuelTank) observed).getFullnessPercents();
			return fullnessPercentsFuelTank = ((FuelTank) observed).getFullnessPercents(); 
		} else if (observed instanceof Engine) {
			message = "Engine wind up status is: " + ((Engine) observed).getIsWindUp();
			return isWindUpEngine = ((Engine) observed).getIsWindUp();
		} else {
			message = "Error. Value not changed";
			return null;
		}
	}
	
	public Object valueChanged(Car observed) {
		message = "Car running status is: " + observed.getIsRun();
		return observed.getIsRun();
	}
	
	public Object valueChanged(AbstractTransmission observed) {
		message =  "Gear now is: " + observed.getGear();
		return observed.getGear();
	}	
	
	/**
	 * 
	 * @param observed
	 * @return
	 */
	public String toString() {
		return message;
	}

	
}
