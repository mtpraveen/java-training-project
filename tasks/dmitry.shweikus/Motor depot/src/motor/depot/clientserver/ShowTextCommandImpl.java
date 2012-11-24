/**
 * 
 */
package motor.depot.clientserver;

import java.io.DataInputStream;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.DataOutputStream;
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
	public void processDataOnClient(DataInputStream fromServer, DataOutputStream toServer)
	{
		try
		{
			String s = fromServer.readUTF() + "\n";
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

	public void sendString(DataOutputStream toServer, String s) throws IOException
	{
		sendToClient(toServer);
		toServer.writeUTF(s);
	}
}