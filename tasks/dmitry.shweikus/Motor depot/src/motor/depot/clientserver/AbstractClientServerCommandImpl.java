/**
 * 
 */
package motor.depot.clientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dima
 *
 */
public abstract class AbstractClientServerCommandImpl
{
	protected ClientServerCommand command; 
	/**
	 * @param command
	 */
	public AbstractClientServerCommandImpl(ClientServerCommand command) {
		super();
		this.command = command;
	}
	public abstract void processDataOnClient(BufferedReader fromServer, PrintWriter toServer);
	public void sendToClient(PrintWriter toClient)
	{
		//by default send name of this command
		toClient.println(command.toString());
	}
}
