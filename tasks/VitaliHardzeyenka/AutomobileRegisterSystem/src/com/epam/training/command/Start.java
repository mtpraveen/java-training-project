package com.epam.training.command;

import com.epam.training.carEquipment.Battery;

/**
 * Start battery.
 * @author Gordeenko
 *
 */
public class Start extends AbstractCommand {
	
	private Battery battery;
	
	public Start(Battery battery) {
		this.battery = battery;
	}
	
	public void execute() {
		battery.run();
	}
}
