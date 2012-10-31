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
public class Engine extends AbstractCarPart
{

	/**
	 * @param eventRecordingSystem
	 */
	public Engine(IEventRecordingSystem eventRecordingSystem) {
		super(eventRecordingSystem);
		// TODO Auto-generated constructor stub
	}
	public void startEngine()
	{
		getEventRecordingSystem().acceptMessage(new Message(MessageKind.START_ENGINE));
	}
	public void stopEngine()
	{
		getEventRecordingSystem().acceptMessage(new Message(MessageKind.STOP_ENGINE));
	}
	public void fireEngineError()
	{
		getEventRecordingSystem().acceptMessage(new Message(MessageKind.ENGINE_ERROR));
	}
	public void SpeedUp()
	{
		getEventRecordingSystem().acceptMessage(new Message(MessageKind.SPEED_UP));
	}
	public void SpeedDown()
	{
		getEventRecordingSystem().acceptMessage(new Message(MessageKind.SPEED_DOWN));
	}
}
