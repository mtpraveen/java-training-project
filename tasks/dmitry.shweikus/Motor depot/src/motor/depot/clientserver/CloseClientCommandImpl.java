/**
 * 
 */
package motor.depot.clientserver;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * @author dima
 *
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

	static IExitable exitable;
	public static void setExitable(IExitable exitListener)
	{
		exitable = exitListener;
	}

	@Override
	public void processDataOnClient(BufferedReader fromServer, PrintWriter toServer)
	{
		if (exitable!=null)
			exitable.doExit();
	}
}