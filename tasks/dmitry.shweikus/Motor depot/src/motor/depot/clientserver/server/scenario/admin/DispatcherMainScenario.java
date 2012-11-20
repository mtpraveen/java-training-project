package motor.depot.clientserver.server.scenario.admin;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.MenuScenario;
import motor.depot.clientserver.server.scenario.SimpleEchoScenario;
import motor.depot.clientserver.server.scenario.tables.ShowModelTable;
import motor.depot.clientserver.server.scenario.users.ChangePasswordScenario;
import motor.depot.clientserver.server.scenario.users.NewRequestForRepairScenario;
import motor.depot.model.MotorDepot;

public class DispatcherMainScenario extends AbstractScenario {

	/**
	 * @param thread
	 */
	public DispatcherMainScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run() {
		MenuScenario menu = new MenuScenario(thread);
		menu.setRepeatable(true);
		menu.addMenuItem("Add user", new AddUserScenario(thread));
		menu.addMenuItem("Show connected users", new ShowConnectedUsersScenario(thread));
		menu.addMenuItem("Disconnect user", new DisconnectUserScenario(thread));
		menu.addMenuItem("Change password", new ChangePasswordScenario(thread));
		
		MenuScenario subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem("Add user", new AddUserScenario(thread));
		subMenu.addMenuItem("Show connected users", new ShowConnectedUsersScenario(thread));
		subMenu.addMenuItem("Disconnect user", new DisconnectUserScenario(thread));
		subMenu.addMenuItem("Change password", new ChangePasswordScenario(thread));
		subMenu.addMenuItem("shutdown server and exit", new ShutdownServerScenario(thread));
		subMenu.addExitItem("exit");
		
		menu.addMenuItem("ADMINISTRATION", subMenu);
		
		menu.addMenuItem("Show all drivers", new ShowModelTable(thread, MotorDepot.instance().drivers));
		menu.addMenuItem("Show all cars", new ShowModelTable(thread, MotorDepot.instance().cars));
		menu.addMenuItem("Add car", new AddCarScenario(thread));
		menu.addMenuItem("Show all trips", new ShowModelTable(thread, MotorDepot.instance().trips));
		menu.addMenuItem("Show all requests for repair", new ShowModelTable(thread, MotorDepot.instance().requestsForRepair));
		menu.addMenuItem("New request for repair", new NewRequestForRepairScenario(thread));
		menu.addMenuItem("shutdown server and exit", new ShutdownServerScenario(thread));
		menu.addExitItem("exit");
		menu.run();
	}

}
