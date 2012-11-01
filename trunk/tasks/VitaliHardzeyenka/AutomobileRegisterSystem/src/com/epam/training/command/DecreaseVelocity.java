package com.epam.training.command;

import com.epam.training.factoryMethod.AbstractTransmission;

public class DecreaseVelocity extends AbstractCommand {
	
	private AbstractTransmission transmission;
	
	public DecreaseVelocity(AbstractTransmission transmission) {
		this.transmission = transmission;
	}
	
	public void execute() {
		transmission.decreaseVelocity();
	}
}
