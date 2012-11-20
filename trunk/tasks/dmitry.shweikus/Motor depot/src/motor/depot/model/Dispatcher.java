/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractItemStateSaver;
import motor.depot.storages.interfaces.AbstractStorage;
import motor.depot.storages.interfaces.ICanBeSaved;

/**
 * @author dima
 * 
 */
public class Dispatcher extends User
{
	int nextId = 1;

	@Override
	public boolean isAdmin()
	{
		return true;
	}

	public Trip sendTrip(Car car, Trip trip, Driver driver)
	{
		return null;
	}

	@Override
	public AbstractItemStateSaver getSaver(AbstractStorage saverCreator)
	{
		return super.getSaver(saverCreator).addValue(nextId);
	}
	@Override
	public void loadPrimitives(AbstractItemStateLoader stateGetter)
	{
		super.loadPrimitives(stateGetter);
		if (stateGetter.getValuesCount() > 3)
			nextId = stateGetter.getValueInt(3);
	}
	public void setDriverSuspendedState(Driver driver, boolean isSuspended)
	{

	}

	@Override
	public ICanBeSaved newInstance()
	{
		return new Dispatcher();
	}

	@Override
	public String getClassId()
	{
		return "dispatcher";
	}

	public int generateNewId()
	{
		return nextId++;
	}

}
