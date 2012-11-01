package com.epam.training.command;

import com.epam.training.carEquipment.Car;

public class Stop extends AbstractCommand {
	
	private Car car;
	
	public Stop(Car car) {
		this.car = car;
	}
	
	public void execute() {
		car.stop();
	}	
}
