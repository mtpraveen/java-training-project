/**
 * 
 */
package motor.depot.storages.csv;

import java.util.ArrayList;

import motor.depot.storages.interfaces.AbstractItemStateSaver;

/**
 * @author dima
 *
 */
public class CsvItemStateSaver extends AbstractItemStateSaver
{
	private ArrayList<String> data = new ArrayList<String>();
	/**
	 * @param classId
	 */
	public CsvItemStateSaver(String classId) {
		super(classId);
		data.add(classId);
	}

	@Override
	public AbstractItemStateSaver addValue(String value)
	{
		data.add(value);
		return this;
	}
	
	public ArrayList<String> getData()
	{
		ArrayList<String> res = new ArrayList<String>();
		res.addAll(data);
		return res;
	}
}
