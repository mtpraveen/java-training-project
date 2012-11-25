/**
 * 
 */
package motor.depot.storages.csv;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import motor.depot.listclasses.ListWithIds;
import motor.depot.model.Driver;
import motor.depot.model.MotorDepot;
import motor.depot.storages.interfaces.ICanBeSaved;

import org.apache.log4j.Logger;

/**
 * @author dima
 * 
 */
public class ZippedCsvLoader
{
	private final static Logger LOGGER = Logger.getLogger(ZippedCsvLoader.class);

	private int appendFromCurrentEntry(ListWithIds<? extends ICanBeSaved> list, ZipInputStream zip)
			throws IOException
	{
		File tmpFile = File.createTempFile("csv", ".tmp");
		byte[] buffer = new byte[2048];
		FileOutputStream fos = new FileOutputStream(tmpFile);
		int size = 0;
		while ((size = zip.read(buffer, 0, buffer.length)) != -1)
		{
			fos.write(buffer, 0, size);
		}
		fos.flush();
		fos.close();
		InputStream stream = new FileInputStream(tmpFile);
		InputStreamReader reader = new InputStreamReader(stream);
		int res = list.appendFromCsvStream(new BufferedReader(reader));
		tmpFile.delete();
		return res;
	}

	public int appendFromZippedCsv(String filepath, MotorDepot depot)
	{
		int res = 0;
		try
		{
			ZipInputStream zip = new ZipInputStream(new FileInputStream(filepath));
			ZipEntry entry;
			while ((entry = zip.getNextEntry()) != null)
			{
				if (!entry.isDirectory())
				{
					String name = entry.getName();
					int separatorIdx = name.indexOf('.');
					if (separatorIdx > 0)
					{
						name = name.substring(0, separatorIdx);
					}
					ListWithIds<? extends ICanBeSaved> list = depot.getLoadableFromCsvList(name);
					if (list != null)
						res += appendFromCurrentEntry(list, zip);
				}
			}
		} catch (FileNotFoundException e)
		{
		} catch (IOException e)
		{
		}
		return res;
	}
}
