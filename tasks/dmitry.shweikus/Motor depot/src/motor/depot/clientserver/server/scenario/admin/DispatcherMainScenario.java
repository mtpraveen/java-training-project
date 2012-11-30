package motor.depot.clientserver.server.scenario.admin;

import java.io.IOException;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.MenuScenario;
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
	public void run() throws IOException {
		MenuScenario menu = new MenuScenario(thread);
		menu.setRepeatable(true);

		MenuScenario subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Add_user"), new AddUserScenario(thread)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Show_connected_users"), new ShowConnectedUsersScenario(thread)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Disconnect_user"), new DisconnectUserScenario(thread)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Change_password"), new ChangePasswordScenario(thread)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Create_report"), new CreateReportScenario(thread)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Load_data_from_zipped_CSV_files"), new LoadDataFromCsvScenario(thread)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Shutdown_server_and_exit"), new ShutdownServerScenario(thread)); //$NON-NLS-1$
		subMenu.addExitItem(thread.getString("DispatcherMainScenario..._go_to_main_menu")); //$NON-NLS-1$
		menu.addMenuItem(thread.getString("DispatcherMainScenario.Server_utilites"), subMenu); //$NON-NLS-1$
		
		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Show_all_drivers"), new ShowModelTable(thread, MotorDepot.getInstance().drivers)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Recruit_driver"), new RecruitDriverScenario(thread)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Remove_driver_from_job"), new RemoveDriverFromJob(thread)); //$NON-NLS-1$
		subMenu.addExitItem(thread.getString("DispatcherMainScenario..._go_to_main_menu")); //$NON-NLS-1$
		menu.addMenuItem(thread.getString("DispatcherMainScenario.Drivers"), subMenu); //$NON-NLS-1$
		
		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Show_all_cars"), new ShowModelTable(thread, MotorDepot.getInstance().cars)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Add_car"), new AddCarScenario(thread)); //$NON-NLS-1$
		subMenu.addExitItem(thread.getString("DispatcherMainScenario..._go_to_main_menu")); //$NON-NLS-1$
		menu.addMenuItem(thread.getString("DispatcherMainScenario.Cars"), subMenu); //$NON-NLS-1$

		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Show_all_trips"), new ShowModelTable(thread, MotorDepot.getInstance().trips)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.New_trip"), new AddTripScenarion(thread)); //$NON-NLS-1$
		subMenu.addExitItem(thread.getString("DispatcherMainScenario..._go_to_main_menu")); //$NON-NLS-1$
		menu.addMenuItem(thread.getString("DispatcherMainScenario.Trips"), subMenu); //$NON-NLS-1$
		
		subMenu = new MenuScenario(thread);
		subMenu.setRepeatable(true);
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.Show_all_requests_for_repair"), new ShowModelTable(thread, MotorDepot.getInstance().requestsForRepair)); //$NON-NLS-1$
		subMenu.addMenuItem(thread.getString("DispatcherMainScenario.New_request_for_repair"), new NewRequestForRepairScenario(thread)); //$NON-NLS-1$
		subMenu.addExitItem(thread.getString("DispatcherMainScenario..._go_to_main_menu")); //$NON-NLS-1$
		menu.addMenuItem(thread.getString("DispatcherMainScenario.Requests_for_repair"), subMenu); //$NON-NLS-1$
		
		menu.addExitItem(thread.getString("DispatcherMainScenario.exit")); //$NON-NLS-1$
		menu.run();
	}

}
