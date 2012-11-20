/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractItemStateSaver;
import motor.depot.storages.interfaces.AbstractStorage;
import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ITableRowProvider;

/**
 * @author dima
 *
 */
public class RequestForRepair implements ICanBeSaved,ITableRowProvider
{
	int id;
	Driver driver;
	Car car;
	String description;
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
	@Override
	public int getId()
	{
		return id;
	}
	@Override
	public AbstractItemStateSaver getSaver(AbstractStorage saverCreator)
	{
		AbstractItemStateSaver saver = saverCreator.createNewSaver(getClassId());
		saver.addValue(id);
		saver.addValue(description);
		saver.addValue(driver.getId());
		saver.addValue(car.getId());
		return saver;
	}
	@Override
	public void loadPrimitives(AbstractItemStateLoader stateGetter)
	{
		id = stateGetter.getValueInt(0);
		description = stateGetter.getValue(1);
	}
	@Override
	public void loadObjects(MotorDepot motorDepot, AbstractItemStateLoader getter)
	{
		driver = motorDepot.findDriverById(getter.getValueInt(2));
		car = motorDepot.findCarById(getter.getValueInt(3));
	}
	@Override
	public String getClassId()
	{
		return "RequestForRepair";
	}
	@Override
	public ICanBeSaved newInstance()
	{
		return new RequestForRepair();
	}
	@Override
	public void setId(int id)
	{
		this.id = id;
	}
	@Override
	public int getColCount()
	{
		return 3;
	}
	@Override
	public String getColName(int col)
	{
		switch (col) {
		case 0: return "Car";
		case 1: return "Descr.";
		case 2: return "Driver";
		}
		return "";
	}
	@Override
	public String getValue(int col)
	{
		switch (col) {
		case 0: return car.toString();
		case 1: return description;
		case 2: return driver.getLogin();
		}
		return "";
	}
	@Override
	public ITableRowProvider getRowProvider()
	{
		return this;
	}
}
