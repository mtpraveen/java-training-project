/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ILoadableFromCsv;
import motor.depot.storages.interfaces.ITableRowProvider;

/**
 * @author dima
 * 
 */
public class Car implements ICanBeSaved, ITableRowProvider,ILoadableFromCsv
{
	//int id;
	String number;
	String model;
	String description;
	String state;
	private static final long serialVersionUID = 22112012;

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
	/*public int getId()
	{
		return id;
	}*/

	/**
	 * @return the model
	 */
	public String getModel()
	{
		return model;
	}

	@Override
	public String getClassId()
	{
		return "car";
	}

	@Override
	public ICanBeSaved newInstance()
	{
		return new Car();
	}

	/*@Override
	public void setId(int id)
	{
		this.id = id;
	}*/

	@Override
	public int getColCount()
	{
		return 4;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Car other = (Car) obj;
		if (description == null)
		{
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (model == null)
		{
			if (other.model != null)
				return false;
		}
		else if (!model.equals(other.model))
			return false;
		if (number == null)
		{
			if (other.number != null)
				return false;
		}
		else if (!number.equals(other.number))
			return false;
		if (state == null)
		{
			if (other.state != null)
				return false;
		}
		else if (!state.equals(other.state))
			return false;
		return true;
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

	@Override
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	@Override
	public void setField(int index, String value)
	{
		switch (index) {
		case 0:
			number = value;
			break;
		case 1:
			model = value;
			break;
		case 2:
			description = value;
			break;
		case 3:
			state = value;
			break;
		}
	}
}
