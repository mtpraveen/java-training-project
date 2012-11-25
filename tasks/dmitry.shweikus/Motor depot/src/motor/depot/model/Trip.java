/**
 * 
 */
package motor.depot.model;

import motor.depot.exceptions.CannotChangeAssignedDriverException;
import motor.depot.model.enums.TripState;
import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractItemStateSaver;
import motor.depot.storages.interfaces.AbstractStorage;
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
		return "trip";
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
		case 0:	return "Start";
		case 1:	return "Destination";
		case 2:	return "Descr.";
		case 3:	return "State";
		case 4:	return "Car";
		case 5:	return "Driver";
		}
		return "";
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
		return "";
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
}
