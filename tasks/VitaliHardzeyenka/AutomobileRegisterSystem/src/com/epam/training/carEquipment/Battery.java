package com.epam.training.carEquipment;

/**
 * Desctibe car battery.
 * @author Gordeenko *
 */
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

	/**
	 * If battery is charged, run it, then run engine.
	 */	
	public void run() {
		isLaunched = charged ? true : false;		
		if (isLaunched) {
			Engine engine = Engine.getInstance();
			engine.run();
			notifyObserver(engine);
		}
		notifyObserver();
	}
	
	/**
	 * Stop battery.
	 */
	public void stop() {
		isLaunched = false;
		notifyObserver();
	}
}
