package com.epam.training.factoryMethod;

import java.util.ArrayList;
import java.util.Iterator;

import com.epam.training.observer.BaseObserver;

public abstract class AbstractTransmission {
	private ArrayList<BaseObserver> observersList = new ArrayList<>();
	private byte gear;
	
	public abstract void increaseVelocity();
	public abstract void decreaseVelocity();
	
	public void addObserver(BaseObserver observer) {
		observersList.add(observer);
	}
	
	public byte getGear() {
		return gear;
	}
	
	protected void setGear(byte gear) {
		this.gear = gear;
		notifyObserver();
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
