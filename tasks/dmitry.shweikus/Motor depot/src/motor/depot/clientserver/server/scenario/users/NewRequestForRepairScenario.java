/**
 * 
 */
package motor.depot.clientserver.server.scenario.users;

import java.io.IOException;
import java.util.ListIterator;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.Car;
import motor.depot.model.Driver;
import motor.depot.model.MotorDepot;
import motor.depot.model.RequestForRepair;

/**
 * @author dima
 * 
 */
public class NewRequestForRepairScenario extends AbstractScenario
{

	/**
	 * @param thread
	 */
	public NewRequestForRepairScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() throws IOException
	{
		str(""); //$NON-NLS-1$
		Driver driver;
		if (thread.getUser() instanceof Driver)
			driver = (Driver) thread.getUser();
		else
		{
			ListWithIds<Driver> drivers = thread.getMotorDepot().drivers.getCopy();
			ListIterator<Driver> iterator = drivers.listIterator();
			while(iterator.hasNext())
			{
				if(!iterator.next().isActive())
					iterator.remove();
			}
			str(thread.getString("NewRequestForRepairScenario.Select_driver")); //$NON-NLS-1$
			TableScenario driversTable = new TableScenario(thread, drivers.getTableProvider());
			driversTable.setSelectable(true);
			driversTable.run();
			if (driversTable.getSelectedRow() == -1)
				return;
			driver = drivers.get(driversTable.getSelectedRow());
		}
		str(""); //$NON-NLS-1$
		str(thread.getString("NewRequestForRepairScenario.Select_car")); //$NON-NLS-1$
		TableScenario carTable = new TableScenario(thread, thread.getMotorDepot().cars.getTableProvider());
		carTable.setSelectable(true);
		carTable.run();
		if (carTable.getSelectedRow() == -1)
			return;
		Car car = thread.getMotorDepot().cars.get(carTable.getSelectedRow());
		str(""); //$NON-NLS-1$
		str(thread.getString("NewRequestForRepairScenario.Enter_reason")); //$NON-NLS-1$
		String description = readString();
		
		RequestForRepair request = thread.getMotorDepot().addRequestForRepair(driver, car, description);
		if (request == null)
			str(thread.getString("NewRequestForRepairScenario.Request_not_added")); //$NON-NLS-1$
		else
			str(thread.getString("NewRequestForRepairScenario.Request_added")); //$NON-NLS-1$
		waitForInput();
	}
}
