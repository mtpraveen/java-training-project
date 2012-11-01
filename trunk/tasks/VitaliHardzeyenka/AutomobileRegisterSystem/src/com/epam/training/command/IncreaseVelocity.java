package com.epam.training.command;

import com.epam.training.factoryMethod.AbstractTransmission;

public class IncreaseVelocity extends AbstractCommand {

	private AbstractTransmission transmission;
	
	public IncreaseVelocity(AbstractTransmission transmission) {
		this.transmission = transmission;
	}
	
	public void execute() {
		transmission.increaseVelocity();
	}
}
