/**
 * 
 */
package event.recording.filters;

import event.recording.loggers.AbstractEventLogger;
import event.recording.system.Message;

/**
 * @author dima
 *
 */
public abstract class AbstractEventFilter
{
	private AbstractEventLogger logger;
	/**
	 * @param logger
	 */
	public AbstractEventFilter(AbstractEventLogger logger) {
		super();
		this.logger = logger;
	}
	/**
	 * @return the logger
	 */
	public AbstractEventLogger getLogger()
	{
		return logger;
	}
	public abstract boolean acceptMessage(Message message);
	
}
