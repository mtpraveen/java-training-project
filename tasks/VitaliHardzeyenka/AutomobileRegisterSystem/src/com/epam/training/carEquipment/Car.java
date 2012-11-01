package com.epam.training.carEquipment;

import java.util.ArrayList;
import java.util.Iterator;

import com.epam.training.factoryMethod.AbstractTransmission;
import com.epam.training.observer.BaseObserver;
import com.epam.training.observer.RegistrationSystemObserver;

public class Car {
	private static Car INSTANCE = null; 
	private Battery battery;
	private FuelTank fuelTank;
	private Engine engine;
	private AbstractTransmission transmission;
	private RegistrationSystemObserver registrationSystemObserver;
	private boolean isRun;
	private ArrayList<BaseObserver> observersList = new ArrayList<>();	
	
	/**
	 * 
	 * @param battery
	 * @param fuelTank
	 * @param engine
	 * @param transmission
	 * @param registrationSystemObserver
	 */
	private Car(Battery battery, FuelTank fuelTank, Engine engine, AbstractTransmission transmission, RegistrationSystemObserver registrationSystemObserver) {
		this.battery = battery;
		this.fuelTank = fuelTank;
		this.engine = engine;
		this.transmission = transmission;
		this.registrationSystemObserver = registrationSystemObserver;
	}
	
	public static Car getInstance(Battery battery, FuelTank fuelTank, Engine engine, AbstractTransmission transmission, RegistrationSystemObserver registrationSystemObserver) {
		if (INSTANCE == null) {
			INSTANCE = new Car(battery, fuelTank, engine, transmission, registrationSystemObserver);
		}
		return INSTANCE;
	}
	
	public boolean getIsRun() {
		return isRun;
	}

	public void setIsRun(boolean isRun) {
		this.isRun = isRun;
		notifyObserver();
	}

	public void stop() {
		if (isRun) {
			setIsRun(false);
			notifyObserver();
		}
	}
	
	public void addObserver(BaseObserver observer) {
		observersList.add(observer);
	}
	
	protected void notifyObserver() {
		Iterator iterator = observersList.iterator();
		while (iterator.hasNext()) {
			((BaseObserver) iterator.next()).valueChanged(this);
		}
	}
	
	public String toString() {
		StringBuilder message = new StringBuilder();
		Iterator iterator = observersList.iterator();
		
		while (iterator.hasNext()) {
			message.append(((BaseObserver) iterator.next()).toString() + '\n');
		}
		
		return message.toString();
	}
}
