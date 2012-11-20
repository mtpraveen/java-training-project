/**
 * 
 */
package motor.depot.clientserver;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author dima
 * 
 */
final class ShowTextCommandImpl extends AbstractClientServerCommandImpl
{
	/**
	 * @param command
	 */
	public ShowTextCommandImpl(ClientServerCommand command) {
		super(command);
	}

	@Override
	public void processDataOnClient(BufferedReader fromServer, PrintWriter toServer)
	{
		try
		{
			String s = fromServer.readLine() + "\n";
			byte[] b;
			if (System.console() == null)
			{
				b = s.getBytes();
			}
			else
			{
				String cp = System.getProperty("console.encoding", "Cp866");
				try
				{
					b = s.getBytes(cp);
				} catch (UnsupportedEncodingException e)
				{
					b = s.getBytes();
				}
			}
			System.out.write(b);
		} catch (IOException e)
		{
			//errors here are impossible
		}
	}

	public void sendString(PrintWriter toServer, String s)
	{
		sendToClient(toServer);
		toServer.println(s);
	}
}