package motor.depot.clientserver.server.scenario.admin;

import java.util.Iterator;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.Driver;
import motor.depot.model.MotorDepot;
import motor.depot.model.Trip;
import motor.depot.model.enums.TripState;

public class RemoveDriverFromJob extends AbstractScenario {

	public RemoveDriverFromJob(ClientThread thread) {
		super(thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		ListWithIds<Driver> drivers = MotorDepot.getInstance().drivers.getCopy();
		Iterator<Driver> iterator = drivers.iterator();
		while(iterator.hasNext())
		{
			if(!iterator.next().isActive())
				iterator.remove();
		}
		if (drivers.size() == 0)
		{
			str(thread.getString("RemoveDriverFromJob.No_active_drivers_found")); //$NON-NLS-1$
			waitForInput();
			return;
		}
		TableScenario scenario = new TableScenario(thread, drivers.getTableProvider());
		scenario.setSelectable(true);
		scenario.setCaption(thread.getString("RemoveDriverFromJob.Select_driver")); //$NON-NLS-1$
		scenario.run();
		if(scenario.getSelectedRow() == -1)
			return;
		Driver driver = drivers.get(scenario.getSelectedRow());
		if (MotorDepot.getInstance().setNewDriverActiveState(driver, false))
			str(thread.getString("RemoveDriverFromJob.Driver_removed_from_job")); //$NON-NLS-1$
		else
			str(thread.getString("RemoveDriverFromJob.Error_removing.Driver_may_be_an_trip")); //$NON-NLS-1$
		waitForInput();
	}

}
