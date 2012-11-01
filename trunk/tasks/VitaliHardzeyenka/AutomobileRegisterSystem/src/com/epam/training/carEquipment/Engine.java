package com.epam.training.carEquipment;

/**
 * Singleton
 * Describe engine.
 * @author Gordeenko
 */
public class Engine extends Equipmment {
	private boolean isWindUp;
	
	private static Engine INSTANCE = null;
	
	private Engine() {}
	
	private Engine(boolean isWindUp) {
		this.isWindUp = isWindUp;
	}
	
	public static Engine getInstance(boolean isWindUp) {
		if (INSTANCE == null) {
			INSTANCE = new Engine(isWindUp);
		}
		return INSTANCE;
	}
	
	public static Engine getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Engine();
		}
		return INSTANCE;
	}
	
	public boolean getIsWindUp() {
		return isWindUp;
	}
	
	/**
	 * Run engine.
	 */
	public void run() {
		isWindUp = true;
		notifyObserver();
	}

	public void setIsWindUp(boolean isWindUp) {
		this.isWindUp = isWindUp;
		notifyObserver();
	}
}
