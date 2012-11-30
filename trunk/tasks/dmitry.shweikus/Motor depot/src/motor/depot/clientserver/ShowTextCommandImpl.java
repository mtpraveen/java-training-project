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
				//we are running from IDE
				b = s.getBytes();
			}
			else
			{
				//we are running from "real" console
				String cp = System.getProperty("console.encoding", "Cp866");
				try
				{
					//try convert string to system default encoding
					b = s.getBytes(cp);
				} catch (UnsupportedEncodingException e)
				{
					//show string "as is"
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