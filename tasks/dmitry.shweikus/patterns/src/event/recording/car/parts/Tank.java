/**
 * 
 */
package event.recording.car.parts;

import event.recording.enums.MessageKind;
import event.recording.system.IEventRecordingSystem;
import event.recording.system.Message;

/**
 * @author dima
 * 
 */
public class Tank extends AbstractCarPart
{

	/**
	 * 
	 */
	public static final int MIN_FUEL_LEVEL = 5;

	/**
	 * @param eventRecordingSystem
	 */
	public Tank(IEventRecordingSystem eventRecordingSystem) {
		super(eventRecordingSystem);
		// TODO Auto-generated constructor stub
	}

	public void setFuelLevel(int level)
	{
		if (level <= MIN_FUEL_LEVEL)
		{
			getEventRecordingSystem().acceptMessage(
					new Message(MessageKind.LOW_FUEL, new Integer(level)));
		}
	}

}
