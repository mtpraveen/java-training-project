/**
 * 
 */
package event.recording.filters;

import event.recording.enums.MessageKind;
import event.recording.loggers.AbstractEventLogger;
import event.recording.system.Message;

/**
 * @author dima
 *
 */
public class BatteryFilter extends AbstractEventFilter
{

	/**
	 * 
	 */
	public static final String LOW_BATTERY_VOLTAGE = "Low battery voltage : ";

	/**
	 * @param logger
	 */
	public BatteryFilter(AbstractEventLogger logger) {
		super(logger);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see event.recording.filters.AbstractEventFilter#acceptMessage(event.recording.system.Message)
	 */
	@Override
	public boolean acceptMessage(Message message)
	{
		if (message.getKind() == MessageKind.LOW_BATTERY_VOLTAGE)
		{
			float voltage = ((Float)message.getExtraData()).floatValue();
			getLogger().Error(LOW_BATTERY_VOLTAGE + voltage);
			return true;
		}
		else
			return false;
	}

}
