package com.epam.training.test;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.logging.ConsoleHandler;

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
import com.epam.training.observer.BaseObserver;
import com.epam.training.observer.RegistrationSystemObserver;

/**
 * Obsere system test.
 * @author Gordeenko
 * There are some patterns used in this project as: Command, Observer, Singleton, FactoryMethod.
 * First car created, then driver get a car and send a commands to different car equipment.
 * All changes reflects in console by RegistrationSystemObserver.
 * 
 */
public class ObserverTest {

	@Test
	public void testRegistrationSystemObserver() {		

		// create car
		Battery battery = new Battery(true, false); // battery charged, not running
		Engine engine = Engine.getInstance(false); // engine not running
		FuelTank fuelTank = new FuelTank((byte) 0); // tank is empty
		TransmissionFactory transmissionFactory = new TransmissionFactory();
		AbstractTransmission transmission = transmissionFactory.getTransmission("Manual"); // manual transmission
		RegistrationSystemObserver registrationSystemObserver = new RegistrationSystemObserver();		
		Car car = Car.getInstance(battery, fuelTank, engine, transmission, registrationSystemObserver); // create car
		
		
		// create commands
		AbstractCommand startCommand = new Start(battery); // start battery
		AbstractCommand decreaseVelocityCommand = new DecreaseVelocity(transmission); // increase gear
		AbstractCommand increaseVelocityCommand = new IncreaseVelocity(transmission); // decrease gear
		AbstractCommand stopCommand = new Stop(car); // stop car
		
		// create observer
		BaseObserver observer = new RegistrationSystemObserver();
		
		// add observer to objects
		battery.addObserver(observer);
		engine.addObserver(observer);
		fuelTank.addObserver(observer);
		transmission.addObserver(observer);
		car.addObserver(observer);
		
		Driver driver = new Driver(); // create driver
		driver.setCar(car); // driver get car
		
		fuelTank.setFullnessPercents((byte) 100);
		System.out.println(fuelTank.toString());
		
		// battery start command
		driver.setCommand(startCommand);
		driver.execute(); // start battery
		System.out.println(battery.toString()); // battery status
		engine.run();
		System.out.println(engine.toString()); // engine status
		car.setIsRun(true);

		// gear increase command
		driver.setCommand(increaseVelocityCommand);
		driver.execute(); // increase gear
		System.out.println(transmission.toString()); // gear status
		
		// gear increase command
		driver.setCommand(increaseVelocityCommand);
		driver.execute(); // increase gear
		System.out.println(transmission.toString()); // gear status

		// gear decrease command
		driver.setCommand(decreaseVelocityCommand);
		driver.execute(); // decrease gear
		System.out.println(transmission.toString()); // gear status

		// car stop command
		driver.setCommand(stopCommand);
		driver.execute(); // stop car
		System.out.println(car.toString()); // car status
		
	}
}
