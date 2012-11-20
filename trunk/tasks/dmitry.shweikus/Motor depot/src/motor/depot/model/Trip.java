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
	int id;
	String start;
	String destination;
	String description;
	TripState state;
	Car car;
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
	public int getId()
	{
		return id;
	}
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
	/* (non-Javadoc)
	 * @see motor.depot.storages.interfaces.ICanBeSaved#getSaver(motor.depot.storages.interfaces.IAbstractItemStateSaverCreator)
	 */
	@Override
	public AbstractItemStateSaver getSaver(AbstractStorage saverCreator)
	{
		AbstractItemStateSaver saver = saverCreator.createNewSaver(getClassId());
		saver.addValue(id);
		saver.addValue(start);
		saver.addValue(destination);
		saver.addValue(description);
		saver.addValue(state.ordinal());
		saver.addValue(car.getId());
		saver.addValue(driver.getId());
		return saver;
	}
	@Override
	public void loadPrimitives(AbstractItemStateLoader stateGetter)
	{
		id = stateGetter.getValueInt(0);
		start = stateGetter.getValue(1);
		destination = stateGetter.getValue(3);
		description = stateGetter.getValue(2);
		state = TripState.values()[stateGetter.getValueInt(4)];
	}
	@Override
	public void loadObjects(MotorDepot motorDepot, AbstractItemStateLoader getter)
	{
		car = motorDepot.findCarById(getter.getValueInt(5));
		driver = motorDepot.findDriverById(getter.getValueInt(5));
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
	@Override
	public void setId(int id)
	{
		this.id = id;
	}
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
}
