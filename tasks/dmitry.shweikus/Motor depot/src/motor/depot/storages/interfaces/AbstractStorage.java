/**
 * 
 */
package motor.depot.storages.interfaces;

import java.util.ArrayList;

/**
 * @author dima
 *
 */
public interface AbstractStorage
{
	void save();
	AbstractItemStateSaver createNewSaver(String itemId);
	void addSaver(AbstractItemStateSaver saver);
	void load();
	public ArrayList<AbstractItemStateLoader> getLoaders(String classId);
}
