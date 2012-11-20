/**
 * 
 */
package motor.depot.storages.interfaces;

/**
 * @author dima
 * load item state from storage
 */
public abstract class AbstractItemStateLoader
{
	/**
	 * @return the classId
	 */
	public abstract String getClassId();
	
	public abstract int getValuesCount();
	public abstract String getValue(int index);
	public int getValueInt(int index)
	{
		return Integer.parseInt(getValue(index));
	}
}
