package com.epam.training.command;

import com.epam.training.factoryMethod.AbstractTransmission;

/**
 * Describe gear decreasing. 
 * @author Gordeenko
 *
 */
public class DecreaseVelocity extends AbstractCommand {
	
	private AbstractTransmission transmission;
	
	public DecreaseVelocity(AbstractTransmission transmission) {
		this.transmission = transmission;
	}
	
	public void execute() {
		transmission.decreaseVelocity();
	}
}
