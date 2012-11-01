package com.epam.training.carEquipment;

public class Engine extends Equipmment {
	private boolean isWindUp;
	
	private static Engine INSTANCE = null;
	
	private Engine() {}
	
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
	
	private Engine(boolean isWindUp) {
		this.isWindUp = isWindUp;
	}
	
	public boolean getIsWindUp() {
		return isWindUp;
	}
	
	public void run() {
		isWindUp = true;
		notifyObserver();
		System.out.println("The engine status is: " + isWindUp);
	}

	public void setIsWindUp(boolean isWindUp) {
		this.isWindUp = isWindUp;
		notifyObserver();
	}
}
