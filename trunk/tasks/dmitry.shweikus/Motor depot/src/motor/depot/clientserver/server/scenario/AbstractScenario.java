/**
 * 
 */
package motor.depot.clientserver.server.scenario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import motor.depot.clientserver.ClientServerCommand;
import motor.depot.clientserver.GetStringCommandImpl;
import motor.depot.clientserver.server.ClientThread;

/**
 * @author dima
 *
 */
public abstract class AbstractScenario
{
	private final static Logger LOGGER = Logger.getLogger(AbstractScenario.class);  
	protected BufferedReader reader;
	protected PrintWriter writer;
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
	private String readAnswerFromServer()
	{
		String s = "";
		try
		{
			s = reader.readLine();
		} catch (IOException e)
		{
			hasErrors = true;
			LOGGER.error("IOException by reading string from client");
		}
		if (s==null)
			s = "";
		return s;
	}
	protected String readPassword()
	{
		((GetStringCommandImpl) ClientServerCommand.GET_STRING.getImpl()).sendToClient(writer,true);
		return readAnswerFromServer();
	}
	protected String readString()
	{
		ClientServerCommand.GET_STRING.getImpl().sendToClient(writer);
		return readAnswerFromServer();
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
		while(res == null)
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
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
