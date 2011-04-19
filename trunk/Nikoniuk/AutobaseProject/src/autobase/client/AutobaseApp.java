/**
 * 
 */
package autobase.client;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

import autobase.exceptions.AddCarException;
import autobase.exceptions.AddUserException;
import autobase.exceptions.AuthorizationException;
import autobase.model.*;

/**
 * <p>
 * The class <code>AutobaseApp</code> implements UI functionality for different types of users:
 * <ul>
 * <li>admins({@link AdminUi} class)
 * <li>dispatchers({@link DispatcherUi} class)
 * <li>drivers({@link DriverUi} class)
 * </ul>
 * 
 * @author Alexander Nikoniuk
 * @see autobase.server.Autobase
 * 
 */
public class AutobaseApp {
	
    private static ResourceBundle messages;

	/**
	 * <p>Implements UI functionality for {@link autobase.model.Admin}
	 * 
	 * @author Alexander Nikoniuk
	 */
	private static class AdminUi {
		private static Admin admin = new Admin();

		public static void addUser(int sid, UserType userType) {
			try {
				getUserList(sid);
				System.out.print(messages.getString("username"));
				String username = input.next();
				System.out.print(messages.getString("password"));
				String password = input.next();
				admin.addUser(sid, username, password, userType);
				getUserList(sid);
				System.out.println(messages.getString("done"));
			} catch (AddUserException e) {
				System.out.println(e.getMessage());
			}
		}

		public static void deleteUser(int sid) {
			try {
				getUserList(sid);
				System.out.print(messages.getString("input_user_id"));
				User user = admin.getUserById(sid, input.nextInt());
				admin.deleteUser(sid, user);
				getUserList(sid);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}

		public static void getUserList(int sid) {
			System.out.println(messages.getString("users"));
			printArray(admin.getUsersList(sid));
		}

		public static void setUserPassword(int sid) {
			try {
				getUserList(sid);
				System.out.print(messages.getString("input_user_id"));
				User user = admin.getUserById(sid, input.nextInt());
				System.out.print(messages.getString("input_new_password"));
				String password = input.next();
				admin.setUserPassword(sid, user, password);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}

		public static void setUserSuspended(int sid) {
			try {
				getUserList(sid);
				System.out.print(messages.getString("input_user_id"));
				User user = admin.getUserById(sid, input.nextInt());
				System.out.println(messages.getString("input_user_suspended_mode"));
				byte choice = input.nextByte();
				switch (choice) {
				case 1:
					admin.setUserSuspended(sid, user, true);
					break;
				case 2:
					admin.setUserSuspended(sid, user, false);
					break;
				}
				System.out.println(user);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}

		public static void logOut(int sid) {
			admin.logOut(sid);
			System.out.println(messages.getString("bye"));
		}
	}

	/**
	 * <p>Implements UI functionality for {@link autobase.model.Driver}
	 * 
	 * @author Alexander Nikoniuk
	 */
	private static class DriverUi {

		private static Driver driver = new Driver();

		public static void getAvailableCars(int sid) {
			System.out.println(messages.getString("cars"));
			printArray(driver.getAvailableCars(sid));
		}

		public static void getRaceRequests(int sid) {
			System.out.println(messages.getString("race_requests"));
			printArray(driver.getRaceRequests(sid));
		}

		public static void markRaceCompleted(int sid) {
			try {
				getRaceRequests(sid);
				System.out.print(messages.getString("input_race_request_id"));
				Request request = driver.getRaceRequestById(sid, input.nextInt());
				System.out.println(request);
				System.out.print(messages.getString("input_car_state"));
				String carState = input.next();
				driver.markRaceCompleted(sid, request, carState);
				System.out.println(request);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}

		public static void createRepairRequests(int sid) {
			try {
				getAvailableCars(sid);
				System.out.print(messages.getString("input_car_id"));
				Car car = driver.getCarById(sid, input.nextInt());
				System.out.print(messages.getString("input_car_state"));
				String carState = input.next();
				Request request = driver
						.createRepairRequest(sid, car, carState);
				System.out.println(request);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}

		public static void logOut(int sid) {
			driver.logOut(sid);
			System.out.println(messages.getString("bye"));
		}
	}

	/**
	 * <p>Implements UI functionality for {@link autobase.model.Dispatcher}
	 * 
	 * @author Alexander Nikoniuk
	 */
	private static class DispatcherUi {

		private static Dispatcher dispatcher = new Dispatcher();
		
		public static void createReport(int sid) {
			final String name = "report.csv";
			try {
				System.out.println(messages.getString("input_request_status"));
				byte choice = input.nextByte();
				switch (choice) {
				case 1:
					dispatcher.createReport(sid, name, RequestStatus.COMPLETED);
					break;
				case 2:
					dispatcher.createReport(sid, name, RequestStatus.ACTIVE);
					break;
				case 3:
					dispatcher.createReport(sid, name, RequestStatus.CANCELED);
					break;
				}
				
				try {
					Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", name});
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public static void getAvailableCars(int sid) {
			System.out.println(messages.getString("cars"));
			printArray(dispatcher.getAvailableCars(sid));
		}
		
		public static void getAvailableDrivers(int sid) {
			System.out.println(messages.getString("dirvers"));
			printArray(dispatcher.getAvailableDrivers(sid));
		}
		
		public static void getRaceRequests(int sid) {
			System.out.println(messages.getString("race_requests"));
			printArray(dispatcher.getRaceRequests(sid));
		}
		
		public static void getRepairRequests(int sid) {
			System.out.println(messages.getString("repair_requests"));
			printArray(dispatcher.getRepairRequests(sid));
		}
		
		public static void setDriverSuspended(int sid) {
			try {
				getAvailableDrivers(sid);
				System.out.print(messages.getString("input_driver_id"));
				Driver driver = dispatcher.getDriverById(sid, input.nextInt());
				System.out.println(driver);
				System.out.println(messages.getString("input_user_suspended_mode"));
				byte choice = input.nextByte();
				switch (choice) {
				case 1:
					dispatcher.setDriverSuspended(sid, driver, true);
					break;
				case 2:
					dispatcher.setDriverSuspended(sid, driver, false);
					break;
				}
				System.out.println(driver);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public static void setRequestStatus(int sid) {
			try {
				getRaceRequests(sid);
				getRepairRequests(sid);
				System.out.print(messages.getString("input_request_id"));
				Request request = dispatcher.getRequestById(sid, input.nextInt());
				System.out.println(request);
				System.out.println(messages.getString("input_request_status"));
				byte choice = input.nextByte();
				switch (choice) {
				case 1:
					dispatcher.setRequestStatus(sid, request, RequestStatus.COMPLETED);
					break;
				case 2:
					dispatcher.setRequestStatus(sid, request, RequestStatus.ACTIVE);
					break;
				case 3:
					dispatcher.setRequestStatus(sid, request, RequestStatus.CANCELED);
					break;
				}
				System.out.println(request);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public static void addCar(int sid) {
			try {
				getAvailableCars(sid);
				System.out.print(messages.getString("input_car_brand"));
				String brand = input.next();
				System.out.print(messages.getString("input_car_number"));
				String number = input.next();
				Car car;
				car = dispatcher.addCar(sid, number, brand);
				System.out.println(car);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			catch (AddCarException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public static void createRaceRequest(int sid) {
			try {
				getAvailableDrivers(sid);
				System.out.print(messages.getString("input_driver_id"));
				Driver driver = dispatcher.getDriverById(sid, input.nextInt());
				getAvailableCars(sid);
				System.out.print(messages.getString("input_car_id"));
				Car car = dispatcher.getCarById(sid, input.nextInt());
				Request request = dispatcher.createRaceRequest(sid, driver, car);
				System.out.println(request);
				System.out.println(messages.getString("done"));
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public static void logOut(int sid) {
			dispatcher.logOut(sid);
			System.out.println(messages.getString("bye"));
		}
		
	}

	static Scanner input = new Scanner(System.in);
	
	private static UserType checkPermissions(int sid) {
		return User.checkPermissions(sid);
	}

	public static int getAuthorization() {
		int sid = 0;
		boolean logined = false;

		while (!logined) {
			try {
				System.out.println();
				System.out.println(messages.getString("invitation_to_authorize"));
				System.out.println(messages.getString("username"));
				String username = input.next();
				System.out.println(messages.getString("password"));
				String password = input.next();
				User guest = new User();
				sid = guest.logIn(username, password);
				logined = true;
			} catch (AuthorizationException e) {
				System.out.println(e.getMessage());
			}
		}
		return sid;
	}

	private static <T> void printArray(ArrayList<T> list) {
		for (int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i));
		}

	}

	public static void showAdminClient(int sid) {
		while (true) {
			System.out.println(messages.getString("admin_help"));

			byte choice = input.nextByte();
			switch (choice) {
			case 1:
				AdminUi.addUser(sid, UserType.DRIVER);
				break;
			case 2:
				AdminUi.addUser(sid, UserType.DISPATCHER);
				break;
			case 3:
				AdminUi.deleteUser(sid);
				break;
			case 4:
				AdminUi.getUserList(sid);
				break;
			case 5:
				AdminUi.setUserPassword(sid);
				break;
			case 6:
				AdminUi.setUserSuspended(sid);
				break;
			case 0:
				AdminUi.logOut(sid);
				return;
			}
		}
	}

	public static void showDriverClient(int sid) {
		while (true) {
			System.out.println(messages.getString("driver_help"));
			
			byte choice = input.nextByte();
			switch (choice) {
			case 1:
				DriverUi.getAvailableCars(sid);
				break;
			case 2:
				DriverUi.getRaceRequests(sid);
				break;
			case 3:
				DriverUi.markRaceCompleted(sid);
				break;
			case 4:
				DriverUi.createRepairRequests(sid);
				break;
			case 0:
				DriverUi.logOut(sid);
				return;
			}
		}
	}

	public static void showDispatcherClient(int sid) {
		while (true) {
			System.out.println(messages.getString("dispatcher_help"));
			
			byte choice = input.nextByte();
			switch (choice) {
			case 1:
				DispatcherUi.getAvailableCars(sid);
				break;
			case 2:
				DispatcherUi.getAvailableDrivers(sid);
				break;
			case 3:
				DispatcherUi.getRaceRequests(sid);
				break;
			case 4:
				DispatcherUi.getRepairRequests(sid);
				break;
			case 5:
				DispatcherUi.setDriverSuspended(sid);
				break;
			case 6:
				DispatcherUi.setRequestStatus(sid);
				break;
			case 7:
				DispatcherUi.addCar(sid);
				break;
			case 8:
				DispatcherUi.createRaceRequest(sid);
				break;
			case 9:
				DispatcherUi.createReport(sid);
				break;
			case 0:
				DispatcherUi.logOut(sid);
				return;
			}
		}
	}
	
	private static void loadProperties() {
		Properties props = new Properties();
		try {
            props.load(new FileInputStream("client.properties"));
            String language = props.getProperty("language");
            Locale currentLocale = new Locale(language);
    	    messages = ResourceBundle.getBundle("lang.MessagesBundle", currentLocale);
        } catch (IOException e) {
        	System.out.println(e.getClass().getName());
        	messages = ResourceBundle.getBundle("lang.MessagesBundle", new Locale("en"));
        	System.out.println("Using english locale(by default). Change language in autobase.properties if it is necessary");
        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		loadProperties();
		
		System.out.println(messages.getString("program_name"));
		while (true) {
			int sid = getAuthorization();
			switch (checkPermissions(sid)) {
			case ADMIN:
				System.out.println(messages.getString("admin_mode"));
				showAdminClient(sid);
				break;
			case DRIVER:
				System.out.println(messages.getString("driver_mode"));
				showDriverClient(sid);
				break;
			case DISPATCHER:
				System.out.println(messages.getString("dispatcher_mode"));
				showDispatcherClient(sid);
				break;
			}
		}
	}
}
