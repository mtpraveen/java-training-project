package motor.depot.clientserver.server.scenario.users;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.MenuScenario;
import motor.depot.clientserver.server.scenario.SimpleEchoScenario;
import motor.depot.model.Driver;
import motor.depot.model.RequestForRepair;

public class DriverMainScenario extends AbstractScenario {

	/**
	 * @param thread
	 */
	public DriverMainScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() {
		Driver driver = (Driver) thread.getUser();
		MenuScenario menu = new MenuScenario(thread);
		menu.setRepeatable(true);
		menu.addMenuItem("Change password", new ChangePasswordScenario(thread));
		menu.addMenuItem("Show my requests for repair", new ShowMyRequestsForRepairScenario(thread));
		if (driver.isActive())
		{
			menu.addMenuItem("New request for repair", new NewRequestForRepairScenario(thread));
			menu.addMenuItem("Finish trip", new FinishTripScenario(thread));
		}
		else
		{
			str("WARNING : You are removed!");
		}
		menu.addExitItem("Exit");
		menu.run();
	}

}
