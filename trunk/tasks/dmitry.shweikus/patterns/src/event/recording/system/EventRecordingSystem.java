/**
 * 
 */
package event.recording.system;

import java.util.ArrayList;

import event.recording.filters.AbstractEventFilter;
import event.recording.filters.BatteryFilter;
import event.recording.filters.BrakeFilter;
import event.recording.filters.EngineFilter;
import event.recording.filters.FuelFilter;
import event.recording.filters.GearBoxFilter;
import event.recording.loggers.AbstractEventLogger;

/**
 * @author dima
 *
 */
public class EventRecordingSystem implements IEventRecordingSystem
{
	private ArrayList<AbstractEventFilter> eventFilters = new ArrayList<AbstractEventFilter>(); 
	private AbstractEventLogger logger;
	/* (non-Javadoc)
	 * @see event.recording.system.IEventRecordingSystem#acceptMessage(event.recording.system.Message)
	 */
	@Override
	public void acceptMessage(Message message)
	{
		for (AbstractEventFilter filter : eventFilters)
		{
			if (filter.acceptMessage(message))
				return;
		}
		getLogger().UnknownMessage(message);
	}
	/**
	 * simply add filter. 
	 */
	private void addF(AbstractEventFilter filter)
	{
		eventFilters.add(filter);
	}
	/**
	 * @return the logger
	 */
	public AbstractEventLogger getLogger()
	{
		return logger;
	}
	/**
	 * @param logger
	 */
	public EventRecordingSystem(AbstractEventLogger logger) {
		super();
		this.logger = logger;
		addF(new BatteryFilter(logger));
		addF(new BrakeFilter(logger));
		addF(new EngineFilter(logger));
		addF(new FuelFilter(logger));
		addF(new GearBoxFilter(logger));
	}

}
