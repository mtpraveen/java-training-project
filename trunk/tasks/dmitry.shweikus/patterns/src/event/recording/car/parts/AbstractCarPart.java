/**
 * 
 */
package event.recording.car.parts;

import event.recording.system.IEventRecordingSystem;

/**
 * @author dima
 *
 */
public class AbstractCarPart
{
	private IEventRecordingSystem eventRecordingSystem;

	/**
	 * @return the eventRecordingSystem
	 */
	public IEventRecordingSystem getEventRecordingSystem()
	{
		return eventRecordingSystem;
	}

	/**
	 * @param eventRecordingSystem
	 */
	public AbstractCarPart(IEventRecordingSystem eventRecordingSystem) {
		super();
		this.eventRecordingSystem = eventRecordingSystem;
	}	
}
