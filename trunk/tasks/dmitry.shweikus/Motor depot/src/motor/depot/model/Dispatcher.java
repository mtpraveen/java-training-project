/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.ICanBeSaved;

/**
 * @author dima
 * 
 */
public class Dispatcher extends User
{
	int nextId = 1;
	private static final long serialVersionUID = 22112012;
	
	@Override
	public boolean isAdmin()
	{
		return true;
	}

	public Trip sendTrip(Car car, Trip trip, Driver driver)
	{
		return null;
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
	@Deprecated
	public int generateNewId()
	{
		return nextId++;
	}

	@Override
	public long getSerialVersionUID() {
		return serialVersionUID;
	}

}
