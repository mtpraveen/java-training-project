/**
 * 
 */
package motor.depot.clientserver.server.scenario.users;

import java.util.Iterator;
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
	public void run()
	{
		str("");
		Driver driver;
		if (thread.getUser() instanceof Driver)
			driver = (Driver) thread.getUser();
		else
		{
			ListWithIds<Driver> drivers = MotorDepot.getInstance().drivers.getCopy();
			ListIterator<Driver> iterator = drivers.listIterator();
			while(iterator.hasNext())
			{
				if(!iterator.next().isActive())
					iterator.remove();
			}
			str("Select driver:");
			TableScenario driversTable = new TableScenario(thread, drivers.getTableProvider());
			driversTable.setSelectable(true);
			driversTable.run();
			if (driversTable.getSelectedRow() == -1)
				return;
			driver = drivers.get(driversTable.getSelectedRow());
		}
		str("");
		str("Select car:");
		TableScenario carTable = new TableScenario(thread, MotorDepot.getInstance().cars.getTableProvider());
		carTable.setSelectable(true);
		carTable.run();
		if (carTable.getSelectedRow() == -1)
			return;
		Car car = MotorDepot.getInstance().cars.get(carTable.getSelectedRow());
		str("");
		str("Enter reason:");
		String description = readString();
		
		RequestForRepair request = MotorDepot.getInstance().addRequestForRepair(driver, car, description);
		if (request == null)
			str("Request not added.");
		else
			str("Request added.");
		waitForInput();
	}
}
