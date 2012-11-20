/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.ICanBeSaved;

/**
 * @author dima
 *
 */
public class Driver extends User
{
	boolean active = true;
	/**
	 * @return the active
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Override
	public boolean isAdmin()
	{
		return false;
	}
	
	public RequestForRepair newRequestForRepair(Car car, String Description)
	{
		return null;
	}
	
	public void markTripFinished(Trip trip)
	{
		
	}

	@Override
	public ICanBeSaved newInstance()
	{
		return new Driver();
	}

	@Override
	public String getClassId()
	{
		return "driver";
	}
}
