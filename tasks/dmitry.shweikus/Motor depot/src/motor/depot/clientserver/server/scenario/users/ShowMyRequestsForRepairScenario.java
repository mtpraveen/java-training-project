/**
 * 
 */
package motor.depot.clientserver.server.scenario.users;

import java.util.ListIterator;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.MotorDepot;
import motor.depot.model.RequestForRepair;

/**
 * @author dima
 *
 */
public class ShowMyRequestsForRepairScenario extends AbstractScenario
{
	/**
	 * @param thread
	 */
	public ShowMyRequestsForRepairScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run()
	{
		ListWithIds<RequestForRepair> requests = MotorDepot.instance().requestsForRepair.getCopy();
		ListIterator<RequestForRepair> iterator = requests.listIterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getDriver() != thread.getUser())
				iterator.remove();
		}
		TableScenario tableScenario = new TableScenario(thread, requests.getTableProvider());
		tableScenario.run();
		str("");
	}
}
