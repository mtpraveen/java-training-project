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
public class Brake extends AbstractCarPart
{

	/**
	 * @param eventRecordingSystem
	 */
	public Brake(IEventRecordingSystem eventRecordingSystem) {
		super(eventRecordingSystem);
		// TODO Auto-generated constructor stub
	}

	public void brake()
	{
		getEventRecordingSystem().acceptMessage(new Message(MessageKind.BREAKING));
	}

}
