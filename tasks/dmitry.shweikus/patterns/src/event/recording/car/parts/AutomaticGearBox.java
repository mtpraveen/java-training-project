/**
 * 
 */
package event.recording.car.parts;

import event.recording.enums.AutoGearBoxDriveMode;
import event.recording.enums.MessageKind;
import event.recording.enums.AutoGearBoxDriveMode.DriveModeObject;
import event.recording.system.IEventRecordingSystem;
import event.recording.system.Message;

/**
 * @author dima
 *
 */
public class AutomaticGearBox extends AbstractGearBox
{

	/**
	 * @param eventRecordingSystem
	 */
	public AutomaticGearBox(IEventRecordingSystem eventRecordingSystem) {
		super(eventRecordingSystem);
		// TODO Auto-generated constructor stub
	}
	public void changeDriveMode(AutoGearBoxDriveMode mode)
	{
		DriveModeObject obj = new DriveModeObject(mode);
		getEventRecordingSystem().acceptMessage(new Message(MessageKind.AUTO_DRIVE_MODE_CHANGED,obj));
	}

}
