/**
 * 
 */
package motor.depot.storages.interfaces;

/**
 * @author dima
 * saves item state to storage
 */
public abstract class AbstractItemStateSaver
{
	String classId;

	/**
	 * @return the classId
	 */
	public String getClassId()
	{
		return classId;
	}

	/**
	 * @param classId
	 */
	public AbstractItemStateSaver(String classId) {
		super();
		this.classId = classId;
	}
	public abstract AbstractItemStateSaver addValue(String value);
	public AbstractItemStateSaver addValue(int value)
	{
		return addValue(String.valueOf(value));
	}
}
