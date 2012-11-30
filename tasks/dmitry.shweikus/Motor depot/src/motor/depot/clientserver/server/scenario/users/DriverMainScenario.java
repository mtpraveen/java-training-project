package motor.depot.clientserver.server.scenario.users;

import java.io.IOException;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.MenuScenario;
import motor.depot.model.Driver;

public class DriverMainScenario extends AbstractScenario {

	/**
	 * @param thread
	 */
	public DriverMainScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() throws IOException {
		Driver driver = (Driver) thread.getUser();
		MenuScenario menu = new MenuScenario(thread);
		menu.setRepeatable(true);
		menu.addMenuItem(thread.getString("Change_password"), new ChangePasswordScenario(thread)); //$NON-NLS-1$
		menu.addMenuItem(thread.getString("Show_my_requests_for_repair"), new ShowMyRequestsForRepairScenario(thread)); //$NON-NLS-1$
		if (driver.isActive())
		{
			menu.addMenuItem(thread.getString("New_request_for_repair"), new NewRequestForRepairScenario(thread)); //$NON-NLS-1$
			menu.addMenuItem(thread.getString("Finish_trip"), new FinishTripScenario(thread)); //$NON-NLS-1$
		}
		else
		{
			str(thread.getString("WARNING_You_are_removed")); //$NON-NLS-1$
		}
		menu.addExitItem(thread.getString("Exit")); //$NON-NLS-1$
		menu.run();
	}

}
