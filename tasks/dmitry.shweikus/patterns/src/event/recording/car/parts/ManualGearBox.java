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
public class ManualGearBox extends AbstractGearBox
{

	/**
	 * @param eventRecordingSystem
	 */
	public ManualGearBox(IEventRecordingSystem eventRecordingSystem) {
		super(eventRecordingSystem);
		// TODO Auto-generated constructor stub
	}

	public void changeGear(int Gear)
	{
		getEventRecordingSystem().acceptMessage(
				new Message(MessageKind.MANUAL_GEAR_CHANGE, new Integer(Gear)));
	}

}
