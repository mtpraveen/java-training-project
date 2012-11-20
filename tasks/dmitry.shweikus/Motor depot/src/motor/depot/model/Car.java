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
public class Car implements ICanBeSaved, ITableRowProvider
{
	int id;
	String number;
	String model;
	String description;
	String state;

	/**
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model)
	{
		this.model = model;
	}

	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state)
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
	 * @return the model
	 */
	public String getModel()
	{
		return model;
	}

	@Override
	public AbstractItemStateSaver getSaver(AbstractStorage saverCreator)
	{
		AbstractItemStateSaver saver = saverCreator.createNewSaver(getClassId());
		saver.addValue(id);
		saver.addValue(description);
		saver.addValue(model);
		saver.addValue(number);
		saver.addValue(state);
		return saver;
	}

	@Override
	public void loadPrimitives(AbstractItemStateLoader stateGetter)
	{
		id = stateGetter.getValueInt(0);
		description = stateGetter.getValue(1);
		model = stateGetter.getValue(2);
		number = stateGetter.getValue(3);
		state = stateGetter.getValue(4);
	}

	@Override
	public String getClassId()
	{
		return "car";
	}

	@Override
	public void loadObjects(MotorDepot motorDepot, AbstractItemStateLoader getter)
	{
		// do nothings here, because we have no objects here
	}

	@Override
	public ICanBeSaved newInstance()
	{
		return new Car();
	}

	@Override
	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public int getColCount()
	{
		return 4;
	}

	@Override
	public String getColName(int col)
	{
		switch (col) {
		case 0:
			return "N";
		case 1:
			return "Model";
		case 2:
			return "Descr.";
		case 3:
			return "State";
		}
		return "";
	}

	@Override
	public String getValue(int col)
	{
		switch (col) {
		case 0:
			return number;
		case 1:
			return model;
		case 2:
			return description;
		case 3:
			return state;
		}
		return "";
	}

	@Override
	public ITableRowProvider getRowProvider()
	{
		return this;
	}

	@Override
	public String toString()
	{
		return number + " " + model;
	}
}
