/**
 * 
 */
package event.recording.car;

import event.recording.car.parts.AbstractGearBox;
import event.recording.car.parts.AutomaticGearBox;
import event.recording.car.parts.Battery;
import event.recording.car.parts.Brake;
import event.recording.car.parts.Engine;
import event.recording.car.parts.ManualGearBox;
import event.recording.car.parts.Tank;
import event.recording.enums.GearBoxKind;
import event.recording.system.IEventRecordingSystem;

/**
 * @author dima
 * 
 */
public class CarBuilder
{
	public Car buildCar(GearBoxKind gearBoxKind, IEventRecordingSystem eventRecordingSystem)
	{
		AbstractGearBox gearBox = null;
		switch (gearBoxKind) {
		case AUTOMATIC:
			gearBox = new AutomaticGearBox(eventRecordingSystem);
			break;
		case MANUAL:
			gearBox = new ManualGearBox(eventRecordingSystem);
			break;
		}
		;
		Car car = new Car(
				new Engine(eventRecordingSystem), 
				gearBox, 
				new Tank(eventRecordingSystem), 
				new Battery(eventRecordingSystem), 
				new Brake(eventRecordingSystem));
		return car;
	}
}
