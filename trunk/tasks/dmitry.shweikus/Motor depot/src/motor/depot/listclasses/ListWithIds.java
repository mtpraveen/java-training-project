/**
 * 
 */
package motor.depot.listclasses;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import motor.depot.clientserver.server.scenario.tables.ITableProvider;
import motor.depot.storages.interfaces.ICanBeSaved;

/**
 * @author dima
 *
 */
public class ListWithIds<T extends ICanBeSaved> extends ArrayList<T>
{
	private T prototype;
	/**
	 * @param prototype
	 */
	public ListWithIds(T prototype) {
		super();
		this.prototype = prototype;
	}
	public T findById(int id)
	{
		for (T withId : this)
		{
			if (withId.getId() == id)
				return withId;
		}
		return null;
	}
	/*public void save(AbstractStorage storage)
	{
		for (T canBeSaved : this)
		{
			AbstractItemStateSaver saver = canBeSaved.getSaver(storage);
			storage.addSaver(saver);
		}
	}*/
	public ITableProvider getTableProvider()
	{
		return new ITableProvider() {
			
			@Override
			public int getRowCount()
			{
				return size();
			}
			
			@Override
			public String getColName(int col)
			{
				return prototype.getRowProvider().getColName(col);
			}
			
			@Override
			public int getColCount()
			{
				return prototype.getRowProvider().getColCount();
			}
			
			@Override
			public String getCellValue(int row, int col)
			{
				return get(row).getRowProvider().getValue(col);
			}
		};
	}
	public ListWithIds<T> getCopy()
	{
		ListWithIds<T> copy = new ListWithIds<T>(prototype);
		for (T iCanBeSaved : this)
		{
			copy.add(iCanBeSaved);
		}
		return copy;
	}
	public void saveToStream(ObjectOutputStream stream) throws IOException
	{
		for (T element : this) {
				stream.writeObject(element);
		}
	}
	public boolean addObjectIfMatchType(Object object)
	{
		if(object.getClass().equals(prototype.getClass()))
		{
			add((T)object);
			return true;
		}
		return false;
	}
}
