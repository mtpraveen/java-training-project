package com.epam.training.carEquipment;

public class FuelTank extends Equipmment {
	private byte fullnessPercents;

	public FuelTank(byte fullnessPercents) {
		this.fullnessPercents = fullnessPercents;
	}
	
	public byte getFullnessPercents() {
		return fullnessPercents;
	}

	public void setFullnessPercents(byte fullnessPercents) {
		this.fullnessPercents = fullnessPercents;
		notifyObserver();
	}	
}
