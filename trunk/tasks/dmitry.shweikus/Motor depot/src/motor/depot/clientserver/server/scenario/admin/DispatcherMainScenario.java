package motor.depot.clientserver.server.scenario.admin;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.MenuScenario;
import motor.depot.clientserver.server.scenario.tables.ShowModelTable;
import motor.depot.clientserver.server.scenario.users.ChangePasswordScenario;
import motor.depot.clientserver.server.scenario.users.NewRequestForRepairScenario;
import motor.depot.model.MotorDepot;
import motor.depot.reports.CreateReportScenario;

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

		MenuScenario subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem("Add user", new AddUserScenario(thread));
		subMenu.addMenuItem("Show connected users", new ShowConnectedUsersScenario(thread));
		subMenu.addMenuItem("Disconnect user", new DisconnectUserScenario(thread));
		subMenu.addMenuItem("Change password", new ChangePasswordScenario(thread));
		subMenu.addMenuItem("Create report", new CreateReportScenario(thread));
		subMenu.addMenuItem("Load data from zipped CSV files", new LoadDataFromCsvScenario(thread));
		subMenu.addMenuItem("Shutdown server and exit", new ShutdownServerScenario(thread));
		subMenu.addExitItem(".. go to main menu");
		menu.addMenuItem("Server utilites", subMenu);
		
		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem("Show all drivers", new ShowModelTable(thread, MotorDepot.getInstance().drivers));
		subMenu.addMenuItem("Recruit driver", new RecruitDriverScenario(thread));
		subMenu.addMenuItem("Remove driver from job", new RemoveDriverFromJob(thread));
		subMenu.addExitItem(".. go to main menu");
		menu.addMenuItem("Drivers", subMenu);
		
		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem("Show all cars", new ShowModelTable(thread, MotorDepot.getInstance().cars));
		subMenu.addMenuItem("Add car", new AddCarScenario(thread));
		subMenu.addExitItem(".. go to main menu");
		menu.addMenuItem("Cars", subMenu);

		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem("Show all trips", new ShowModelTable(thread, MotorDepot.getInstance().trips));
		subMenu.addMenuItem("New trip", new AddTripScenarion(thread));
		subMenu.addExitItem(".. go to main menu");
		menu.addMenuItem("Trips", subMenu);
		
		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem("Show all requests for repair", new ShowModelTable(thread, MotorDepot.getInstance().requestsForRepair));
		subMenu.addMenuItem("New request for repair", new NewRequestForRepairScenario(thread));
		subMenu.addExitItem(".. go to main menu");
		menu.addMenuItem("Requests for repair", subMenu);
		
		menu.addExitItem("exit");
		menu.run();
	}

}
