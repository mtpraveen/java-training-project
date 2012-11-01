package com.epam.training.factoryMethod;

public class AutomaticTransmission extends AbstractTransmission {
	
	private static AutomaticTransmission INSTANCE = null;
	
	private AutomaticTransmission() {}
	
	public static AutomaticTransmission getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AutomaticTransmission();
		}
		
		return INSTANCE;
	}
	
	public void increaseVelocity() {
		setGear((byte) (getGear() + 1));
		notifyObserver();
		System.out.println("Gear was automatically changed. Velocity increased.");
	}
	
	public void decreaseVelocity() {
		setGear((byte) (getGear() - 1));
		notifyObserver();
		System.out.println("Gear was automatically changed. Velocity decreased.");
	}
}
