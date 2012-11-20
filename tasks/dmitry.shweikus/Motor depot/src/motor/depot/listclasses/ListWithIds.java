/**
 * 
 */
package motor.depot.listclasses;

import java.util.ArrayList;

import motor.depot.clientserver.server.scenario.tables.ITableProvider;
import motor.depot.model.MotorDepot;
import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractItemStateSaver;
import motor.depot.storages.interfaces.AbstractStorage;
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
	public void save(AbstractStorage storage)
	{
		for (T canBeSaved : this)
		{
			AbstractItemStateSaver saver = canBeSaved.getSaver(storage);
			storage.addSaver(saver);
		}
	}
	public void loadPrimitives(AbstractStorage storage)
	{
		for (AbstractItemStateLoader loader : storage.getLoaders(prototype.getClassId()))
		{
			ICanBeSaved item = prototype.newInstance();
			item.loadPrimitives(loader);
			add((T)item);
		}
	}
	public void loadObjects(AbstractStorage storage, MotorDepot motorDepot)
	{
		for (AbstractItemStateLoader loader : storage.getLoaders(prototype.getClassId()))
		{
			int id = loader.getValueInt(0);
			T item = findById(id);
			if (item != null)
			{
				item.loadObjects(motorDepot, loader);
			}
		}
	}
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
}
