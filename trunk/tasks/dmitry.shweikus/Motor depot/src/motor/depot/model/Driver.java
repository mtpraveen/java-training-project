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
public class Driver extends User
{
	boolean active = true;
	private static final long serialVersionUID = 22112012;
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
	
	@Override
	public int getColCount() {
		return 2;
	}
	
	@Override
	public String getColName(int col) {
		if (col == 0)
			return super.getColName(col);
		else
			return "Active";
	}
	@Override
	public String getValue(int col) {
		if (col == 0)
			return super.getValue(col);
		else
			return active?"Yes":"No";
	}

	@Override
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
}
