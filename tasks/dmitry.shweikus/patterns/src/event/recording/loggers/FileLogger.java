/**
 * 
 */
package event.recording.loggers;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dima
 *
 */
public class FileLogger extends AbstractEventLogger
{
	private String fileName; 

	/**
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * @param fileName
	 */
	public FileLogger(String fileName) {
		super();
		this.fileName = fileName;
	}

	/* (non-Javadoc)
	 * @see event.recording.loggers.AbstractEventLogger#Write(java.lang.String)
	 */
	@Override
	protected void Write(String messageDescription)
	{
		FileWriter writer;
		try
		{
			writer = new FileWriter(fileName,true);
			writer.append(messageDescription + "\n");
			writer.close();
		} catch (IOException e)
		{
			// TODO add log4j message here
		}
	}

}
