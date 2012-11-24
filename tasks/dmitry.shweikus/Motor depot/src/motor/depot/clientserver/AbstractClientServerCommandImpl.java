/**
 * 
 */
package motor.depot.clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;

import org.apache.log4j.Logger;

/**
 * @author dima
 *
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
	public abstract void processDataOnClient(DataInputStream fromServer, DataOutputStream toServer);
	public void sendToClient(DataOutputStream toClient)
	{
		//by default send name of this command
		try {
			toClient.writeUTF(command.toString());
		} catch (IOException e) {
			LOGGER.error("IOException by sending message to client",e);
		}
	}
}
