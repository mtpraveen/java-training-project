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
public class BrakeFilter extends AbstractEventFilter
{

	/**
	 * 
	 */
	public static final String BREAK = "Break";

	/**
	 * @param logger
	 */
	public BrakeFilter(AbstractEventLogger logger) {
		super(logger);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see event.recording.filters.AbstractEventFilter#acceptMessage(event.recording.system.Message)
	 */
	@Override
	public boolean acceptMessage(Message message)
	{
		if (message.getKind() == MessageKind.BREAKING)
		{
			getLogger().Event(BREAK);
			return true;
		}
		else
			return false;
	}

}
