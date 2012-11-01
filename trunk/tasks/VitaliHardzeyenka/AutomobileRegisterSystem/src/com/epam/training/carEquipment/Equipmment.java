package com.epam.training.carEquipment;

import java.util.ArrayList;
import java.util.Iterator;

import com.epam.training.observer.BaseObserver;

public abstract class Equipmment {
	private ArrayList<BaseObserver> observersList = new ArrayList<>();
	
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
