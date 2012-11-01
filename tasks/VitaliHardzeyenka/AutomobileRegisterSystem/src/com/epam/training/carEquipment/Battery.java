package com.epam.training.carEquipment;

public class Battery extends Equipmment {
	private boolean charged;
	private boolean isLaunched;
	
	public Battery(boolean charged, boolean isLaunched) {
		this.charged = charged;
		this.isLaunched = isLaunched;		
	}
	
	public boolean getCharged() {
		return charged;
	}

	public void setCharged(boolean charged) {
		this.charged = charged;		
		notifyObserver();
	}
	
	public boolean getIsLaunched() {
		return isLaunched;
	}

	public void run() {
		isLaunched = charged ? true : false;		
		if (isLaunched) {
			Engine engine = Engine.getInstance();
			engine.run();			
		}
		notifyObserver();
	}
	
	public void stop() {
		isLaunched = false;
		notifyObserver();
	}
}
