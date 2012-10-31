/**
 * 
 */
package event.recording.application;

import event.recording.car.Car;
import event.recording.car.CarBuilder;
import event.recording.car.parts.AutomaticGearBox;
import event.recording.car.parts.Battery;
import event.recording.car.parts.ManualGearBox;
import event.recording.car.parts.Tank;
import event.recording.enums.AutoGearBoxDriveMode;
import event.recording.enums.GearBoxKind;
import event.recording.loggers.AbstractEventLogger;
import event.recording.loggers.ConsoleLogger;
import event.recording.loggers.FileLogger;
import event.recording.system.EventRecordingSystem;

/**
 * @author dima
 * 
 */
public class Application
{
	private static void runForConcreteGearBox(GearBoxKind kind, AbstractEventLogger logger)
	{
		logger.CustomMessage("");
		logger.CustomMessage("");
		logger.CustomMessage("Creating car with gearbox : " + kind);
		EventRecordingSystem recordingSystem = new EventRecordingSystem(logger);
		Car car = (new CarBuilder()).buildCar(kind, recordingSystem);
		car.getEngine().startEngine();
		car.getEngine().SpeedUp();
		car.getEngine().SpeedDown();
		switch (kind) {
		case AUTOMATIC:
			((AutomaticGearBox) car.getGearBox()).changeDriveMode(AutoGearBoxDriveMode.DRIVE);
			((AutomaticGearBox) car.getGearBox()).changeDriveMode(AutoGearBoxDriveMode.LOW);
			break;
		case MANUAL:
			((ManualGearBox) car.getGearBox()).changeGear(1);
			((ManualGearBox) car.getGearBox()).changeGear(2);
			break;
		}
		car.getBrake().brake();
		car.getEngine().stopEngine();
		car.getTank().setFuelLevel(Tank.MIN_FUEL_LEVEL + 5);
		car.getTank().setFuelLevel(Tank.MIN_FUEL_LEVEL * 9 / 10);
		car.getEngine().fireEngineError();
		car.getBattery().setVoltage(Battery.MIN_VOLTAGE + 1);
		car.getBattery().setVoltage(Battery.MIN_VOLTAGE * 9 / 10);
	}

	private static void run(AbstractEventLogger logger)
	{
		runForConcreteGearBox(GearBoxKind.AUTOMATIC, logger);
		runForConcreteGearBox(GearBoxKind.MANUAL, logger);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		run(new ConsoleLogger());
		run(new FileLogger("car_events.txt"));
	}

}
