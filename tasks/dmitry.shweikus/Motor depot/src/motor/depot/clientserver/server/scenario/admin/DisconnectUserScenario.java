/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

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
	public void run()
	{
		ConnectedUsersList users = new ConnectedUsersList();
		users.getUsers().remove(MotorDepot.getInstance().getDispatcher());//cannot kill item self
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
				Server.getInstance().disconnectUser(clientThread , "You disconnected by admin");
				str("User " + user.getLogin() + " disconnected");
				waitForInput();
			}
		}
			
	}

}
