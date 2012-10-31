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
public class EngineFilter extends AbstractEventFilter
{

	/**
	 * 
	 */
	public static final String SPEED_UP = "Speed up";
	/**
	 * 
	 */
	public static final String SPEED_DOWN = "Speed down";
	/**
	 * 
	 */
	public static final String ENGINE_ERROR = "Engine error";
	/**
	 * 
	 */
	public static final String ENGINE_STOPPED = "Engine stopped";
	/**
	 * 
	 */
	public static final String ENGINE_STARTED = "Engine started";

	/**
	 * @param logger
	 */
	public EngineFilter(AbstractEventLogger logger) {
		super(logger);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see event.recording.filters.AbstractEventFilter#acceptMessage(event.recording.system.Message)
	 */
	@Override
	public boolean acceptMessage(Message message)
	{
		switch (message.getKind()) {
		case START_ENGINE:
			getLogger().Event(ENGINE_STARTED);
			break;
		case STOP_ENGINE:
			getLogger().Event(ENGINE_STOPPED);
			break;
		case ENGINE_ERROR:
			getLogger().Error(ENGINE_ERROR);
			break;
		case SPEED_DOWN:
			getLogger().Event(SPEED_DOWN);
			break;
		case SPEED_UP:
			getLogger().Event(SPEED_UP);
			break;
		default:
			return false;
		}
		return true;
	}

}
