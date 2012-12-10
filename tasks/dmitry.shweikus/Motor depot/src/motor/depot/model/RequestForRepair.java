/**
 * 
 */
package motor.depot.model;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ITableRowProvider;

/**
 * @author dima
 *
 */
public class RequestForRepair implements ICanBeSaved,ITableRowProvider
{
	//int id;
	Driver driver;
	Car car;
	String description;
	private static final long serialVersionUID = 22112012;
	/**
	 * @return the driver
	 */
	public Driver getDriver()
	{
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}
	/**
	 * @return the car
	 */
	public Car getCar()
	{
		return car;
	}
	/**
	 * @param car the car to set
	 */
	public void setCar(Car car)
	{
		this.car = car;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	/*@Override
	public int getId()
	{
		return id;
	}*/
	@Override
	public String getClassId()
	{
		return "RequestForRepair"; //$NON-NLS-1$
	}
	@Override
	public ICanBeSaved newInstance()
	{
		return new RequestForRepair();
	}
	/*@Override
	public void setId(int id)
	{
		this.id = id;
	}*/
	@Override
	public int getColCount()
	{
		return 3;
	}
	@Override
	public String getColName(int col)
	{
		switch (col) {
		case 0: return "Car"; //$NON-NLS-1$
		case 1: return "Descr."; //$NON-NLS-1$
		case 2: return "Driver"; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
	@Override
	public String getTranslatedColumn(int col, ClientThread thread) {
		switch (col) {
		case 0: return thread.getString("Car"); //$NON-NLS-1$
		case 1: return thread.getString("Descr."); //$NON-NLS-1$
		case 2: return thread.getString("Driver"); //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
	@Override
	public String getValue(int col)
	{
		switch (col) {
		case 0: return car.toString();
		case 1: return description;
		case 2: return driver.getLogin();
		}
		return ""; //$NON-NLS-1$
	}
	@Override
	public ITableRowProvider getRowProvider()
	{
		return this;
	}
	@Override
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestForRepair other = (RequestForRepair) obj;
		if (car == null)
		{
			if (other.car != null)
				return false;
		}
		else if (!car.equals(other.car))
			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (driver == null)
		{
			if (other.driver != null)
				return false;
		}
		else if (!driver.equals(other.driver))
			return false;
		return true;
	}
}
