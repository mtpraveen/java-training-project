/**
 * 
 */
package motor.depot.clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author dima
 * client executes doExit() method,when receives this command 
 */
public class CloseClientCommandImpl extends AbstractClientServerCommandImpl
{
	
	/**
	 * @param command
	 * @param exitable
	 */
	public CloseClientCommandImpl(ClientServerCommand command) {
		super(command);
	}
	/**
	 * exit handler
	 */
	static IExitable exitable;
	public static void setExitable(IExitable exitListener)
	{
		exitable = exitListener;
	}

	@Override
	public void processDataOnClient(DataInputStream fromServer, DataOutputStream toServer)
	{
		if (exitable!=null)
			exitable.doExit();
	}
}