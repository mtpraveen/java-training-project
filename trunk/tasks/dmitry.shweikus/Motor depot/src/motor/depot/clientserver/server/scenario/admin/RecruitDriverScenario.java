package motor.depot.clientserver.server.scenario.admin;

import java.util.Iterator;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.tables.TableScenario;
import motor.depot.listclasses.ListWithIds;
import motor.depot.model.Driver;
import motor.depot.model.MotorDepot;

public class RecruitDriverScenario extends AbstractScenario {

	public RecruitDriverScenario(ClientThread thread) {
		super(thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		ListWithIds<Driver> drivers = MotorDepot.getInstance().drivers.getCopy();
		Iterator<Driver> iterator = drivers.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().isActive())
				iterator.remove();
		}
		if (drivers.size() == 0)
		{
			str(thread.getString("RecruitDriverScenario.All_drivers_active")); //$NON-NLS-1$
			waitForInput();
			return;
		}
		TableScenario scenario = new TableScenario(thread, drivers.getTableProvider());
		scenario.setSelectable(true);
		scenario.setCaption(thread.getString("RecruitDriverScenario.Select_driver")); //$NON-NLS-1$
		scenario.run();
		if(scenario.getSelectedRow() == -1)
			return;
		Driver driver = drivers.get(scenario.getSelectedRow());
		MotorDepot.getInstance().setNewDriverActiveState(driver, true);
		waitForInput();
	}

}
