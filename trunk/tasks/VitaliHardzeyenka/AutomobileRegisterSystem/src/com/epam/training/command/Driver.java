package com.epam.training.command;

import com.epam.training.carEquipment.Car;

public class Driver {
	private Car car;
	private AbstractCommand command;
	
	public Driver() {}
	
	public Driver(AbstractCommand command) {
		this.command = command;
	}
	
	public void setCommand(AbstractCommand command) {
		this.command = command;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public void execute() {
		command.execute();
	}	
}
