/**
 * 
 */
package motor.depot.model;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.exceptions.CannotChangeAssignedDriverException;
import motor.depot.model.enums.TripState;
import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ITableRowProvider;

/**
 * @author dima
 *
 */
public class Trip implements ICanBeSaved,ITableRowProvider
{
	//int id;
	String start;
	String destination;
	String description;
	TripState state = TripState.STARTED;
	Car car;
	private static final long serialVersionUID = 22112012;
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}
	Driver driver;	
	/**
	 * @return the state
	 */
	public TripState getState()
	{
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(TripState state)
	{
		this.state = state;
	}
	/**
	 * @return the id
	 */
	/*public int getId()
	{
		return id;
	}*/
	/**
	 * @return the start
	 */
	public String getStart()
	{
		return start;
	}
	/**
	 * @return the destination
	 */
	public String getDestination()
	{
		return destination;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @return the car
	 */
	public Car getCar()
	{
		return car;
	}
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
		if (this.driver != null)
			if(!this.driver.equals(driver))
				throw new CannotChangeAssignedDriverException(this);
		this.driver = driver;
	}
	@Override
	public String getClassId()
	{
		return "trip"; //$NON-NLS-1$
	}
	@Override
	public ICanBeSaved newInstance()
	{
		return new Trip();
	}
	/*@Override
	public void setId(int id)
	{
		this.id = id;
	}*/
	@Override
	public int getColCount()
	{
		return 6;
	}
	@Override
	public String getColName(int col)
	{
		switch (col) {
		case 0:	return "Start"; //$NON-NLS-1$
		case 1:	return "Destination"; //$NON-NLS-1$
		case 2:	return "Descr."; //$NON-NLS-1$
		case 3:	return "State"; //$NON-NLS-1$
		case 4:	return "Car"; //$NON-NLS-1$
		case 5:	return "Driver"; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
	@Override
	public String getTranslatedColumn(int col, ClientThread thread) {
		switch (col) {
		case 0:	return thread.getString("Start"); //$NON-NLS-1$
		case 1:	return thread.getString("Destination"); //$NON-NLS-1$
		case 2:	return thread.getString("Descr."); //$NON-NLS-1$
		case 3:	return thread.getString("State"); //$NON-NLS-1$
		case 4:	return thread.getString("Car"); //$NON-NLS-1$
		case 5:	return thread.getString("Driver"); //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
	@Override
	public String getValue(int col)
	{
		switch (col) {
		case 0:	return start;
		case 1:	return destination;
		case 2:	return description;
		case 3:	return state.toString();
		case 4:	return car.toString();
		case 5:	return driver.getLogin();
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
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Trip other = (Trip) obj;
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
		if (destination == null)
		{
			if (other.destination != null)
				return false;
		}
		else if (!destination.equals(other.destination))
			return false;
		if (driver == null)
		{
			if (other.driver != null)
				return false;
		}
		else if (!driver.equals(other.driver))
			return false;
		if (start == null)
		{
			if (other.start != null)
				return false;
		}
		else if (!start.equals(other.start))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
}
