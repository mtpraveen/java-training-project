/**
 * 
 */
package motor.depot.clientserver.server.scenario.tables;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.storages.interfaces.ICanBeSaved;

/**
 * @author dima
 *
 */
public class ShowModelTable extends AbstractScenario
{
	ListWithIds<? extends ICanBeSaved> list;
	public ShowModelTable(ClientThread thread, ListWithIds<? extends ICanBeSaved> list) {
		super(thread);
		this.list = list;
	}
	@Override
	public void run()
	{
		TableScenario scenario = new TableScenario(thread, list.getTableProvider());
		scenario.run();
	}

}
