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
public class Battery extends AbstractCarPart
{

	/**
	 * 
	 */
	public static final float MIN_VOLTAGE = (float) 11.5;
	/**
	 * @param eventRecordingSystem
	 */
	public Battery(IEventRecordingSystem eventRecordingSystem) {
		super(eventRecordingSystem);
		// TODO Auto-generated constructor stub
	}
	public void setVoltage(float voltage)
	{
		if (voltage <= MIN_VOLTAGE)
		{
			getEventRecordingSystem().acceptMessage(new Message(MessageKind.LOW_BATTERY_VOLTAGE, new Float(voltage)));
		}
	}

}
