/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ILoadableFromCsv;

/**
 * @author dima
 *
 */
public class Driver extends User implements ILoadableFromCsv
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (active ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (active != other.active)
			return false;
		return true;
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
		return 	super.getColCount() + 1;
	}
	
	@Override
	public String getColName(int col) {
		if (col < super.getColCount())
			return super.getColName(col);
		else
			return "Active";
	}
	@Override
	public String getValue(int col) {
		
		if (col < super.getColCount())
			return super.getValue(col);
		else
			return active?"Yes":"No";
	}

	@Override
	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public void setField(int index, String value)
	{
		switch (index) {
		case 0: 
			setLogin(value);
			break;
		case 1: setPassword(value);
			break;
		case 2: active = !(value.trim().equalsIgnoreCase("No")||value.trim().equalsIgnoreCase("0"));
			break;
		}
	}
}
