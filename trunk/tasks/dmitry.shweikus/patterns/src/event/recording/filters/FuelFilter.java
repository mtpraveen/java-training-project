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
public class FuelFilter extends AbstractEventFilter
{

	/**
	 * 
	 */
	public static final String LOW_FUEL = "Low fuel : ";

	/**
	 * @param logger
	 */
	public FuelFilter(AbstractEventLogger logger) {
		super(logger);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see event.recording.filters.AbstractEventFilter#acceptMessage(event.recording.system.Message)
	 */
	@Override
	public boolean acceptMessage(Message message)
	{
		if (message.getKind() == MessageKind.LOW_FUEL)
		{
			int level = ((Integer)message.getExtraData()).intValue();
			getLogger().Error(LOW_FUEL + level);
			return true;
		}
		else
			return false;
	}

}
