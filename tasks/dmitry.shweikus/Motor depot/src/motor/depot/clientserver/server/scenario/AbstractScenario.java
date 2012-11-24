/**
 * 
 */
package motor.depot.clientserver.server.scenario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import motor.depot.clientserver.CLIENT_CONTENT_KIND;
import motor.depot.clientserver.ClientServerCommand;
import motor.depot.clientserver.GetStringCommandImpl;
import motor.depot.clientserver.server.ClientThread;

import org.apache.log4j.Logger;

/**
 * @author dima
 * 
 */
public abstract class AbstractScenario
{
	private final static Logger LOGGER = Logger.getLogger(AbstractScenario.class);
	protected DataInputStream reader;
	protected DataOutputStream writer;
	protected ClientThread thread;
	protected boolean hasErrors;

	/**
	 * @param reader
	 * @param writer
	 */
	public AbstractScenario(ClientThread thread) {
		super();
		this.thread = thread;
		this.reader = thread.getIn();
		this.writer = thread.getOut();
	}

	private String readAnswerFromClient()
	{
		String s = "";
		try
		{
			s = reader.readUTF();
		} catch (IOException e)
		{
			hasErrors = true;
			LOGGER.error("IOException by reading string from client");
		}
		if (s == null)
			s = "";
		return s;
	}

	private String readFileFromClient()
	{
		LOGGER.debug("Start receiving file");
		String s = "";
		int size = -1;
		try
		{
			s = reader.readUTF();
			LOGGER.debug("FileSize = " + s);
			if (isInt(s))
			{
				size = Integer.parseInt(s);
				if (size > -1)
				{
					File file = File.createTempFile("data_from_client", ".tmp");
					FileOutputStream outputStream = new FileOutputStream(file);
					int bufsize = 4096;
					byte[] buf = new byte[bufsize];
					while (size > 0)
					{
						int len = reader.read(buf);
						outputStream.write(buf,0,len);
						size -= len;
					}
					outputStream.flush();
					outputStream.close();
					LOGGER.debug("Receiving successfull");
					return file.getAbsolutePath();
				}
			}
		} catch (IOException e)
		{
			LOGGER.error("IOException by reading file from client");
		}
		LOGGER.debug("Erros receiving");
		return "";
	}

	protected String readFile(String fileName)
	{
		try
		{
			((GetStringCommandImpl) ClientServerCommand.GET_STRING.getImpl()).sendToClient(writer,
					CLIENT_CONTENT_KIND.FILE, fileName);
		} catch (IOException e)
		{
		}
		return readFileFromClient();
	}

	protected String readPassword()
	{
		try
		{
			((GetStringCommandImpl) ClientServerCommand.GET_STRING.getImpl()).sendToClient(writer,
					CLIENT_CONTENT_KIND.PASSWORD, "");
		} catch (IOException e)
		{
		}
		return readAnswerFromClient();
	}

	protected String readString()
	{
		ClientServerCommand.GET_STRING.getImpl().sendToClient(writer);
		return readAnswerFromClient();
	}

	protected void waitForInput()
	{
		str("Press <enter> to continue");
		readString();
	}

	protected void str(String s)
	{
		ClientServerCommand.sendText(writer, s);
	}

	public boolean question(String q)
	{
		Boolean res = null;
		while (res == null)
		{
			str(q + " (Yes,Y,y,No,N,n)");
			String answer = readString();
			if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes"))
				res = true;
			else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("No"))
				res = false;
			if (hasErrors)
				return false;
		}
		return res;
	}

	public abstract void run();

	public boolean isInt(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}
}
