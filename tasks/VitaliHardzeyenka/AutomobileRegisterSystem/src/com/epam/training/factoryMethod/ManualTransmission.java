package com.epam.training.factoryMethod;

import java.util.ArrayList;
import java.util.Iterator;

import com.epam.training.observer.BaseObserver;

public class ManualTransmission extends AbstractTransmission {
	
	private static ManualTransmission INSTANCE = null;
	
	private ManualTransmission() {}
	
	public static ManualTransmission getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManualTransmission();
		}
		
		return INSTANCE;
	}
	
	public void increaseVelocity() {
		setGear((byte) (getGear() + 1));
		notifyObserver();
		System.out.println("Gear was changed by driver. Velocity increased.");
	}
	
	public void decreaseVelocity() {
		setGear((byte) (getGear() - 1));
		notifyObserver();
		System.out.println("Gear was changed by driver. Velocity decreased.");
	}
	
}
