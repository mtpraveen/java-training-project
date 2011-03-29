/**
 * 
 */
package net.epam.java.autobase;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alexander Nikoniuk
 *
 */
public class AutobaseTest {
	
	static Scanner input = new Scanner(System.in);    
	private static User guest = new User();
	
	public static int getAuthorization() {
		int sid = 0;
		boolean logined = false;
		
		while (!logined)
		{
			try {
				System.out.println("LogIn to work in the system \n");
				System.out.println("User: ");
				System.in.skip(100);
				String username = input.nextLine();
				System.out.println("Password: ");	
				System.in.skip(100);
				String password = input.nextLine();
				sid = guest.logIn(username, password);
				logined = true;
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return sid;
	}
	
	private static <T> void printArray(ArrayList<T> list) {
		for(int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i));
		}
		
	}
	
	private static void showAdminMenu(int sid, Admin admin) {
		while (true) {
			try {
				System.out.println("Ussage: 1 - addDriver; 2 - addDispatcher; 3 - deleteUser; 4 - getUsersList; 5 - setUserPassword; \n" +
						"6 - setUserSuspended q - logout");
				String username;
				String password;
				User user;
				System.in.skip(100);
				char choice = (char)System.in.read();
				switch (choice) {
				case '1':
					System.out.println("users: ");
					printArray(admin.getUsersList(sid));
					System.out.print("username: ");
					System.in.skip(100);
					username = input.nextLine();
					System.out.print("password: ");
					System.in.skip(100);
					password = input.nextLine();
					admin.addUser(sid, username, password, UserType.DRIVER);
					System.out.println("done!");
					break;
				case '2':
					System.out.println("users: ");
					printArray(admin.getUsersList(sid));
					System.out.print("username: ");
					System.in.skip(100);
					username = input.nextLine();
					System.out.print("password: ");
					System.in.skip(100);
					password = input.nextLine();
					admin.addUser(sid, username, password, UserType.DISPATCHER);
					System.out.println("done!");
					break;
				case '3':
					System.out.println("users: ");
					printArray(admin.getUsersList(sid));
					System.out.print("Input user id: ");
					System.in.skip(100);
					user = admin.getUserById(sid, Integer.parseInt(input.nextLine()));
					admin.deleteUser(sid, user);
					System.out.println("users: ");
					printArray(admin.getUsersList(sid));
					System.out.println("done!");
					break;
				case '4':
					System.out.print("users: ");
					printArray(admin.getUsersList(sid));
					break;
				case '5':
					System.out.print("Not done yet. Can be used by syscalls");
					break;
				case '6':
					System.out.println("users: ");
					printArray(admin.getUsersList(sid));
					System.out.print("Input user id: ");
					System.in.skip(100);
					user = admin.getUserById(sid, Integer.parseInt(input.nextLine()));
					System.out.println("1 - suspended = true; 2 - suspended = false");
					System.out.print("Your choice: ");
					System.in.skip(100);
					choice = (char)System.in.read();
					switch (choice) {
					case '1':
						admin.setUserSuspended(sid, user, true);
						break;
					case '2':
						admin.setUserSuspended(sid, user, false);
						break;
					}
					System.out.println(user);
					System.out.println("done");
					break;
				case 'q':
					admin.logOut(sid);
					System.out.println("bye");
					return ;
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	
	}
	
	private static void showDriverMenu(int sid, Driver driver) {
		while (true) {
			try {
				System.out.println("Ussage: 1 - getAvailableCars; 2 - getRaceRequests; 3 - markRaceCompleted; 4 - createRepairRequests; \n" +
						" q - logout");
				Request request;
				Car car;
				String carState;
				System.in.skip(100);
				char choice = (char)System.in.read();
				switch (choice) {
				case '1':
					System.out.println("cars: ");
					printArray(driver.getAvailableCars(sid));
					break;
				case '2':
					System.out.println("race requests: ");
					printArray(driver.getRaceRequests(sid));
					break;
				case '3':
					System.out.println("race requests: ");
					printArray(driver.getRaceRequests(sid));
					System.out.print("Input race request id: ");
					System.in.skip(100);
					request = driver.getRaceRequestById(sid, Integer.parseInt(input.nextLine()));
					System.out.println(request);
					System.out.print("Car state: ");
					System.in.skip(100);
					carState = input.nextLine();
					driver.markRaceCompleted(sid, request, carState);
					System.out.println(request);
					break;
				case '4':
					System.out.println("cars: ");
					printArray(driver.getAvailableCars(sid));
					System.out.print("Input car id: ");
					System.in.skip(100);
					car = driver.getCarById(sid, Integer.parseInt(input.nextLine()));
					System.out.print("Car state: ");
					System.in.skip(100);
					carState = input.nextLine();
					request = driver.createRepairRequest(sid, car, carState);
					System.out.println(request);
					System.out.println("done!");
					break;
				case 'q':
					driver.logOut(sid);
					System.out.println("bye");
					return ;
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
	}
	
	private static void showDispatcherMenu(int sid, Dispatcher dispatcher) {
		while (true) {
			try {
				System.out.println("Ussage: 1 - getAvailableCars; 2 - getAvailableDrivers; 3 - getRaceRequests; 4 - getRepairRequests; \n" +
						" 5 - setDriverSuspended; 6 - setRequestStatus; 7 - addCar; 8 - createRaceRequest; q - logout");
				Driver driver;
				Request request;
				Car car;
				System.in.skip(100);
				char choice = (char)System.in.read();
				switch (choice) {
				case '1':
					System.out.println("cars: ");
					printArray(dispatcher.getAvailableCars(sid));
					break;
				case '2':
					System.out.println("drivers: ");
					printArray(dispatcher.getAvailableDrivers(sid));
					break;
				case '3':
					System.out.println("race requests: ");
					printArray(dispatcher.getRaceRequests(sid));
					break;
				case '4':
					System.out.println("repair requests: ");
					printArray(dispatcher.getRepairRequests(sid));
					break;
				case '5':
					System.out.println("users: ");
					printArray(dispatcher.getAvailableDrivers(sid));
					System.in.skip(100);
					System.out.print("Input driver id: ");
					System.in.skip(100);
					driver = dispatcher.getDriverById(sid, Integer.parseInt(input.nextLine()));
					System.out.println(driver);
					System.out.println("1 - suspended = true; 2 - suspended = false");
					System.out.print("Your choice: ");
					System.in.skip(100);
					choice = (char)System.in.read();
					switch (choice) {
					case '1':
						dispatcher.setDriverSuspended(sid, driver, true);
						break;
					case '2':
						dispatcher.setDriverSuspended(sid, driver, false);
						break;
					}
					System.out.println(driver);
					System.out.println("done!");
					break;
				case '6':
					System.out.println("requests: ");
					printArray(dispatcher.getRaceRequests(sid));
					printArray(dispatcher.getRepairRequests(sid));
					System.out.print("Input request id: ");
					System.in.skip(100);
					request = dispatcher.getRequestById(sid, Integer.parseInt(input.nextLine()));
					System.out.println(request);
					System.out.println("1 - COMPLETED; 2 - ACTIVE; 3 - CANCELED");
					System.out.print("Your choice: ");
					System.in.skip(100);
					choice = (char)System.in.read();
					switch (choice) {
					case '1':
						dispatcher.setRequestStatus(sid, request, RequestStatus.COMPLETED);
						break;
					case '2':
						dispatcher.setRequestStatus(sid, request, RequestStatus.ACTIVE);
						break;
					case '3':
						dispatcher.setRequestStatus(sid, request, RequestStatus.CANCELED);
						break;
					}
					System.out.println(request);
					System.out.println("done!");
					break;
				case '7':
					System.out.print("Input car brand: ");
					System.in.skip(100);
					String brand = input.nextLine();
					System.out.print("Input car number: ");
					System.in.skip(100);
					String number = input.nextLine();
					car = dispatcher.addCar(number, brand);
					System.out.println(car);
					System.out.println("done!");
					break;
				case '8':
					System.out.println("drivers: ");
					printArray(dispatcher.getAvailableDrivers(sid));
					System.out.println("cars: ");
					printArray(dispatcher.getAvailableCars(sid));
					System.in.skip(100);
					System.out.print("Input driver id: ");
					System.in.skip(100);
					driver = dispatcher.getDriverById(sid, Integer.parseInt(input.nextLine()));
					System.out.print("Input car id: ");
					System.in.skip(100);
					car = dispatcher.getCarById(sid, Integer.parseInt(input.nextLine()));
					request = dispatcher.createRaceRequest(sid, driver, car);
					System.out.println(request);
					System.out.println("done!");
					break;
				case 'q':
					dispatcher.logOut(sid);
					System.out.println("bye");
					return ;
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Autobase tools");
		while (true) {
			int sid = getAuthorization();
			switch (guest.checkPermissions(sid)) {
			case ADMIN:
				System.out.println("Admin mode");
				showAdminMenu(sid, new Admin());
				break;
			case DRIVER:
				System.out.println("Driver mode");
				showDriverMenu(sid, new Driver());
				break;
			case DISPATCHER:
				System.out.println("Dispatcher mode");
				showDispatcherMenu(sid, new Dispatcher());
				break;
			}
		}

	}
}
