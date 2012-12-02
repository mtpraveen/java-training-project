/**
 * 
 */
package motor.depot.storages.interfaces;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author dima
 *
 */
public interface AbstractStorage
{
	void save();
	void initSave();
	//AbstractItemStateSaver createNewSaver(String itemId);
	//void addSaver(AbstractItemStateSaver saver);
	boolean load();
	//boolean loadFromCSV(String filePath);
	//ArrayList<AbstractItemStateLoader> getLoaders(String classId);
	ObjectOutputStream getOutputStream();
	ObjectInputStream getInputStream();
}
