package com.epam.training.test;

import org.junit.Test;

import com.epam.training.carEquipment.Battery;
import com.epam.training.carEquipment.Car;
import com.epam.training.carEquipment.Engine;
import com.epam.training.carEquipment.FuelTank;
import com.epam.training.command.AbstractCommand;
import com.epam.training.command.DecreaseVelocity;
import com.epam.training.command.Driver;
import com.epam.training.command.IncreaseVelocity;
import com.epam.training.command.Start;
import com.epam.training.command.Stop;
import com.epam.training.factoryMethod.AbstractTransmission;
import com.epam.training.factoryMethod.TransmissionFactory;
import com.epam.training.observer.RegistrationSystemObserver;

public class CommandTest {

	@Test
	public void testCoommands() {
		
		TransmissionFactory transmissionFactory = new TransmissionFactory();
		
		Battery battery = new Battery(true, false);
		Engine engine = Engine.getInstance(true);
		FuelTank fuelTank = new FuelTank((byte) 50);
		AbstractTransmission transmission = transmissionFactory.getTransmission("Manual");
		RegistrationSystemObserver registrationSystemObserver = new RegistrationSystemObserver();
		
		Car car = Car.getInstance(battery, fuelTank, engine, transmission, registrationSystemObserver);
		car.setIsRun(true);
		
		AbstractCommand startCommand = new Start(battery);
		AbstractCommand decreaseVelocityCommand = new DecreaseVelocity(transmission);
		AbstractCommand increaseVelocityCommand = new IncreaseVelocity(transmission);
		AbstractCommand stopCommand = new Stop(car);
		
		Driver driver = new Driver();
		
		driver.setCommand(startCommand);
		driver.execute();
		
		driver.setCommand(increaseVelocityCommand);
		driver.execute();
		
		driver.setCommand(decreaseVelocityCommand);
		driver.execute();
		
		driver.setCommand(stopCommand);
		driver.execute();
	}

}
