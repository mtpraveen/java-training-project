/**
 * 
 */
package event.recording.filters;

import event.recording.enums.AutoGearBoxDriveMode;
import event.recording.loggers.AbstractEventLogger;
import event.recording.system.Message;

/**
 * @author dima
 * 
 */
public class GearBoxFilter extends AbstractEventFilter
{

	/**
	 * @param logger
	 */
	public GearBoxFilter(AbstractEventLogger logger) {
		super(logger);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * event.recording.filters.AbstractEventFilter#acceptMessage(event.recording
	 * .system.Message)
	 */
	@Override
	public boolean acceptMessage(Message message)
	{
		switch (message.getKind()) {
		case MANUAL_GEAR_CHANGE:
			int gear = ((Integer) message.getExtraData()).intValue();
			getLogger().Event("Manual gear changed : " + gear);
			break;
		case AUTO_DRIVE_MODE_CHANGED:
			AutoGearBoxDriveMode mode = ((AutoGearBoxDriveMode.DriveModeObject) message
					.getExtraData()).getMode();
			getLogger().Event("Drive mode is set to " + mode);
			break;
		default:
			return false;
		}
		return true;
	}

}
