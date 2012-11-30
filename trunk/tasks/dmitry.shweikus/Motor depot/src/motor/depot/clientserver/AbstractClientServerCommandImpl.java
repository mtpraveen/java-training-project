/**
 * 
 */
package motor.depot.clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author dima
 * Abstract class that implements client command
 */
public abstract class AbstractClientServerCommandImpl
{
	private final static Logger LOGGER = Logger.getLogger(AbstractClientServerCommandImpl.class);
	protected ClientServerCommand command; 
	/**
	 * @param command
	 */
	public AbstractClientServerCommandImpl(ClientServerCommand command) {
		super();
		this.command = command;
	}
	/**
	 * executes on client
	 * @param fromServer - data, received from server
	 * @param toServer - data, that will be send to server
	 */
	public abstract void processDataOnClient(DataInputStream fromServer, DataOutputStream toServer);
	/**
	 * executes on server 
	 * @param toClient - data, that will be send to server
	 * @throws IOException TODO
	 * @throws IOException 
	 */
	public void sendToClient(DataOutputStream toClient) throws IOException
	{
		//by default send name of this command
		toClient.writeUTF(command.toString());
	}
}
