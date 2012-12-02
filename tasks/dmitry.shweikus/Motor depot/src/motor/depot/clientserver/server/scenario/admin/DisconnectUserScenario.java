/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import java.io.IOException;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.Server;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;
import motor.depot.model.MotorDepot;
import motor.depot.model.User;

/**
 * @author dima
 *
 */
public class DisconnectUserScenario extends AbstractScenario
{

	/**
	 * @param thread
	 */
	public DisconnectUserScenario(ClientThread thread) {
		super(thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws IOException
	{
		ConnectedUsersList users = new ConnectedUsersList();
		users.getUsers().remove(thread.getMotorDepot().getDispatcher());//cannot kill item self
		TableScenario table = new TableScenario(thread, users);
		table.setSelectable(true);
		table.run();
		if (table.getSelectedRow() != -1)
		{
			User user = users.getUsers().get(table.getSelectedRow());
			ClientThread clientThread = null;
			for (ClientThread clientThread1 : Server.getInstance().threads)
			{
				if(clientThread1.getUser() == user)
				{
					clientThread = clientThread1;
					break;
				}
			}
			if(clientThread != null)
			{
				Server.getInstance().disconnectUser(clientThread , thread.getString("DisconnectUserScenario.You_disconnected_by_admin")); //$NON-NLS-1$
				str(thread.getString("DisconnectUserScenario.User_disconnected") + user.getLogin()); //$NON-NLS-1$
				waitForInput();
			}
		}
			
	}

}
