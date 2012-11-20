/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;


import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;

/**
 * @author dima
 *
 */
public class ShowConnectedUsersScenario extends AbstractScenario
{

	/**
	 * @param thread
	 */
	public ShowConnectedUsersScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run()
	{
		new TableScenario(thread, new ConnectedUsersList()).run();
	}

}
