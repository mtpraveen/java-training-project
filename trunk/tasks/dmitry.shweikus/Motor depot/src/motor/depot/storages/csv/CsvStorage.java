/**
 * 
 */
package motor.depot.storages.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import motor.depot.storages.csv.utils.CsvBuilder;
import motor.depot.storages.csv.utils.CsvSplitter;
import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractItemStateSaver;
import motor.depot.storages.interfaces.AbstractStorage;

/**
 * @author dima
 *
 */
public class CsvStorage implements AbstractStorage
{
	private static final String FILEPATH = "data.csv";
	private final static Logger LOGGER = Logger.getLogger(CsvStorage.class);
	ArrayList<CsvItemStateLoader> loaders = new ArrayList<CsvItemStateLoader>();
	ArrayList<CsvItemStateSaver> savers = new ArrayList<CsvItemStateSaver>();
	@Override
	public void save()
	{
		File file = new File(FILEPATH);
		CsvBuilder csvBuilder = new CsvBuilder();
		file.delete();
		try {
			PrintStream stream = new PrintStream(file);
			for (CsvItemStateSaver saver : savers) {
				stream.println(csvBuilder.create(saver.getData()));								
			}
			stream.close();
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException by saving database", e);
		}
	}
	@Override
	public AbstractItemStateSaver createNewSaver(String itemId)
	{
		return new CsvItemStateSaver(itemId);
	}

	/* (non-Javadoc)
	 * @see motor.depot.storages.interfaces.AbstractStorage#addSaver(motor.depot.storages.interfaces.AbstractItemStateSaver)
	 */
	@Override
	public void addSaver(AbstractItemStateSaver saver)
	{
		savers.add((CsvItemStateSaver)saver);
	}

	@Override
	public void load()
	{
		File file = new File(FILEPATH);
		if (file.isFile())
			if (file.exists())
			{
				FileReader freader;
				try {
					CsvSplitter splitter = new CsvSplitter();
					freader = new FileReader(file);
					BufferedReader reader = new BufferedReader(freader);
					String csvData;
					while ((csvData=reader.readLine())!=null)
					{
						ArrayList<String> data = splitter.parse(csvData);
						if (data.size()>0)
							if (!data.get(0).equals(""))
							{
								CsvItemStateLoader loader = new CsvItemStateLoader(data);
								loaders.add(loader);
							}
					}
				} catch (FileNotFoundException e) {
					LOGGER.error("FileNotFoundException by loading database", e);
				} catch (IOException e) {
					LOGGER.error("IOException by loading database", e);
				}
			}
	}

	@Override
	public ArrayList<AbstractItemStateLoader> getLoaders(String classId)
	{
		ArrayList<AbstractItemStateLoader> res = new ArrayList<AbstractItemStateLoader>();
		for (CsvItemStateLoader loader : loaders) {
			if (loader.getClassId().equals(classId))
				res.add(loader);
		}
		return res;
	}
}
