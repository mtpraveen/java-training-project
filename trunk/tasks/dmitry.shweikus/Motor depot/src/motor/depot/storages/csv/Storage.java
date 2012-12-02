/**
 * 
 */
package motor.depot.storages.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import motor.depot.storages.csv.utils.CsvSplitter;
import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractStorage;

import org.apache.log4j.Logger;

/**
 * @author dima
 *
 */
public class Storage implements AbstractStorage
{
	private static final String DATA_FILEPATH = "data.bin";
	private final static Logger LOGGER = Logger.getLogger(Storage.class);
	ArrayList<CsvItemStateLoader> loaders = new ArrayList<CsvItemStateLoader>();
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	FileOutputStream fileOutputStream;
	FileInputStream fileInputStream;
	@Override
	public void save()
	{
		try {
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			LOGGER.error("IOException by saving database", e);
		}
	}
	@Override
	public boolean load()
	{
		File file = new File(DATA_FILEPATH);
		if (!file.exists())
			return false;
		try {
			fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			return true;
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException by loading database", e);
			return false;
		} catch (IOException e) {
			LOGGER.error("IOException by loading database", e);
			return false;
		}
	}

	@Override
	public void initSave() {
		File file = new File(DATA_FILEPATH);
		file.delete();
		try {
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException by saving database", e);
		} catch (IOException e) {
			LOGGER.error("IOException by saving database", e);
		}
	}
	@Override
	public ObjectOutputStream getOutputStream() {
		return objectOutputStream;
	}
	@Override
	public ObjectInputStream getInputStream() {
		return objectInputStream;
	}
}
