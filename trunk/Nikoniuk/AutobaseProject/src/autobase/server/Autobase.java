package autobase.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


import autobase.server.db.ConfigurationManager;
import autobase.server.db.DBConnector;
import autobase.server.db.DatasourceType;
import autobase.server.db.IDataSet;

import autobase.exceptions.*;
import autobase.model.*;

/**
 * <p>
 * The class <code>Autobase</code> implements all the system functionality for different types of users:
 * <ul>
 * <li>admins({@link autobase.model.Admin} class)
 * <li>dispatchers({@link autobase.model.Dispatcher} class)
 * <li>drivers({@link autobase.model.Driver} class)
 * </ul>
 * 
 * @author Alexander Nikoniuk
 * 
 */
public class Autobase implements IDispatcher, IAdmin, IDriver, IUser {
	
	private static final Logger LOGGER = Logger.getLogger(Autobase.class);
	
    //
	private static IDataSet db;
	private static Autobase instance;
	private Map<Integer, User> sessions = new HashMap<Integer, User>();
	static private Random rnd = new Random();
	private static Map<Integer, Request> requests = new HashMap<Integer, Request>();
	private static Map<Integer, User> users = new HashMap<Integer, User>();	
	private static Map<Integer, Car> cars = new HashMap<Integer, Car>();	

	/**
	 * 
	 */
	private Autobase() {
		PropertyConfigurator.configure("log4j.properties");
		
		LOGGER.info("STARTING SERVER");
		try {
			ConfigurationManager.getInstance();
			DatasourceType dsType = ConfigurationManager.getInstance().getDsType();
			db = DbEngine.getInstance(dsType);
			LOGGER.info("Loading data from database");
			db.loadData();
			LOGGER.info("Data successfully loaded");
		} catch (IOException e) {
			LOGGER.error("Error while loading data from database");
			LOGGER.info("Adding default admin account");
			int id = 1;
			users.put(id, new Admin(id, "root", "root"));
			try {
				db.createSchema();
			} catch (IOException e1) {
				LOGGER.error("Error while creating schema");
			}
		}
	}
	
	@Override
	public void createReport(int sid, String filename, RequestStatus status) {
		LOGGER.info("Creating races report");
		checkPermissions(sid, UserType.DISPATCHER);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(
					new BufferedWriter(
							new FileWriter(filename)));
			
			writer.printf(";;" + status + "  RACE REQUESTS\n");
			writer.printf("id;start date;end date;driver;car;car state\n");
			
			for(Request request: requests.values())
				if (request.getType() == RequestType.RACE && request.getStatus() == status) 
					writer.printf("%d;%s;%s;%s;%s\n", request.getId(), request.getStartDate(), request.getEndDate(), request.getDriver(), request.getCar(), request.getCarState());
		} catch (IOException e) {
			LOGGER.fatal("Can't create report " + filename);
		} finally {
			writer.close();
		}
	}
	
	private static class DbEngine {
		public static IDataSet getInstance(DatasourceType dsType) {
			switch (dsType) {
			case FILE: default: 
				return new IDataSet() {
					
					final String filename = "database.zip";
					
					@Override
					public void saveData() throws IOException {
						XStream xstream = new XStream(new DomDriver());
						xstream.setMode(XStream.ID_REFERENCES);
						xstream.alias("driver", Driver.class);
						xstream.alias("admin", Admin.class);
						xstream.alias("dispatcher", Dispatcher.class);
						xstream.alias("car", Car.class);
						xstream.alias("request", Request.class);
						
						ZipOutputStream zout = new ZipOutputStream(
													new BufferedOutputStream(
														new FileOutputStream(filename)));
						
						LOGGER.info("Saving users");
						String xmlString = xstream.toXML(users);
						stringToZipEntry(xmlString, zout, "users.txt");
						
						LOGGER.info("Saving requests");
						xmlString = xstream.toXML(requests);
						stringToZipEntry(xmlString, zout, "requests.txt");
						
						LOGGER.info("Saving cars");
						xmlString = xstream.toXML(cars);
						stringToZipEntry(xmlString, zout, "cars.txt");
						
						zout.close();						
					}
					
					@SuppressWarnings("unchecked")
					@Override
					public void loadData() throws IOException {						
						XStream xstream = new XStream(new DomDriver());
						xstream.setMode(XStream.ID_REFERENCES);
						xstream.alias("driver", Driver.class);
						xstream.alias("admin", Admin.class);
						xstream.alias("dispatcher", Dispatcher.class);
						xstream.alias("car", Car.class);
						xstream.alias("request", Request.class);

						ZipInputStream zin = new ZipInputStream(
												new BufferedInputStream(
													new FileInputStream(filename)));
						
						LOGGER.info("Loading users");
						users = (Map<Integer, User>)xstream.fromXML(zipEntryToString(zin));
						LOGGER.info("Loading requests");
						requests = (Map<Integer, Request>)xstream.fromXML(zipEntryToString(zin));
						LOGGER.info("Loading cars");
						cars = (Map<Integer, Car>)xstream.fromXML(zipEntryToString(zin));

						zin.close();
					}
					
					private String zipEntryToString(ZipInputStream zin) throws IOException {
						ZipEntry z = zin.getNextEntry();
						StringBuilder result = new StringBuilder();
						int numRead = 0;
						byte[] buf = new byte[1024];
						while((numRead = zin.read(buf)) != -1) {
							String readData = new String(buf, 0, numRead);
							result.append(readData);
						}
						zin.closeEntry();
						return result.toString();
					}
					
					private void stringToZipEntry(String data, ZipOutputStream zout, String entryName) throws IOException {
						zout.putNextEntry(new ZipEntry(entryName));
						zout.write(data.getBytes());
						zout.closeEntry();
					}

					@Override
					public void createSchema() { }	
				};
				
			case DB:
				return new IDataSet() {
					
					@Override
					public void saveData() throws IOException {
						String deleteCarsSQL = "DELETE FROM Cars";
						String deleteUsersSQL = "DELETE FROM Users";
						String deleteRequestsSQL = "DELETE FROM Requests";
						String insertUsersSQL = "INSERT INTO Users VALUES (?, ?, ?, ?, ?)";
						String insertRequesSQL = "INSERT INTO Requests VALUES (?, ?, ?, ?, ?, ?, ?)";
						String insertCarsSQL = "INSERT INTO Cars VALUES(?, ?, ?)";
						Statement st = null;
						ResultSet rs = null;
						try {
							Connection connection = DBConnector.getConnection();
							LOGGER.info("Clearing old data");
							st = connection.createStatement();	
							st.executeUpdate(deleteRequestsSQL);
							st = connection.createStatement();	
							st.executeUpdate(deleteUsersSQL);
							st = connection.createStatement();	
							st.executeUpdate(deleteCarsSQL);
							
							LOGGER.info("Saving users");
							PreparedStatement ps = connection.prepareStatement(insertUsersSQL);	
							for (User user: users.values()) {
								ps.setInt(1, user.getId());
								if (user instanceof Admin)
									ps.setInt(2, UserType.ADMIN.ordinal() + 1);
								else if (user instanceof Driver)
									ps.setInt(2, UserType.DRIVER.ordinal() + 1);
								else if (user instanceof Dispatcher)
									ps.setInt(2, UserType.DISPATCHER.ordinal() + 1);
								ps.setString(3, user.getUsername());
								ps.setString(4, user.getPassword());
								ps.setInt(5, user.isSuspended()? 1: 0);
								ps.executeUpdate();
							}	
							
							LOGGER.info("Saving cars");
							ps = connection.prepareStatement(insertCarsSQL);	
							for (Car car: cars.values()) {
								ps.setInt(1, car.getId());
								ps.setString(2, car.getBrand());
								ps.setString(3, car.getNumber());
								ps.executeUpdate();
							}	
							
							LOGGER.info("Saving requests");
							ps = connection.prepareStatement(insertRequesSQL);	
							for (Request request: requests.values()) {
								ps.setInt(1, request.getId());
								ps.setInt(2, request.getType().ordinal() + 1);
								ps.setInt(3, request.getStatus().ordinal() + 1);
								ps.setString(4, request.getStartDate());
								ps.setString(5, request.getEndDate());
								ps.setInt(6, request.getCar().getId());
								ps.setInt(7, request.getDriver().getId());
								ps.setString(8, request.getCarState());
								ps.executeUpdate();
							}	
							
						} catch (SQLException e) { 
							throw new IOException(e);
						} finally {
							try {
								if (rs != null)
									rs.close();
								if (st != null)
									st.close();
							} catch (SQLException e) {

							}
						}
					}
					
					@Override
					public void loadData() throws IOException {
						String selectCarsSQL = "select * from cars";
						String selectUsersSQL = "select * from users";
						String selectRequestsSQL = "select * from requests";
						Statement st = null;
						ResultSet rs = null;
						try {
							Connection connection = DBConnector.getConnection();
							
							LOGGER.info("Loading users");
							st = connection.createStatement();	
							rs = st.executeQuery(selectUsersSQL);
							while (rs.next()) {
								//read data
								int id = rs.getInt("user_id");
								UserType type = UserType.typeById(rs.getInt("user_type_id") - 1);
								String username = rs.getString("name");
								String password = rs.getString("password");
								boolean suspended = rs.getInt("suspended") == 0? false: true;
								//put data
								User user = null;
								switch(type) {
								case ADMIN:
									user = new Admin(id, username, password);
									break;
								case DRIVER:
									user = new Driver(id, username, password);
									break;
								case DISPATCHER:
									user = new Dispatcher(id, username, password);
									break;
								}

								user.setSuspended(suspended);
								users.put(id, user);
							}	
							
							LOGGER.info("Loading cars");
							st = connection.createStatement();
							rs = st.executeQuery(selectCarsSQL);
							while (rs.next()) {
								//read data
								int id = rs.getInt("car_id");
								String model = rs.getString("model");
								String number = rs.getString("number");
								//put data
								cars.put(id, new Car(id, number, model));
							}	
							
							LOGGER.info("Loading requests");
							st = connection.createStatement();
							rs = st.executeQuery(selectRequestsSQL);
							while (rs.next()) {
								//read data
								int id = rs.getInt("request_id");
								RequestType type = RequestType.typeById(rs.getInt("request_type_id") - 1);
								RequestStatus status = RequestStatus.typeById(rs.getInt("request_status_id") - 1);
								int carId = rs.getInt("car_id");
								int driverId = rs.getInt("driver_id");
								String startDate = rs.getString("start_time");
								String endDate = rs.getString("end_time");
								String carState = rs.getString("carState");
								//put data
								Request request = new Request(id, type, (Driver)users.get(driverId), cars.get(carId));
								request.setStatus(status);
								request.setStartDate(startDate);
								request.setEndDate(endDate);
								request.setCarState(carState);
								requests.put(id, request);
							}
							
						} catch (SQLException e) { 
							throw new IOException(e);
						} finally {
							try {
								if (rs != null)
									rs.close();
								if (st != null)
									st.close();
							} catch (SQLException e) {

							}
						}
					}

					@Override
					public void createSchema() throws IOException {
						Statement st=null;
						try {
							Connection connection = DBConnector.getConnection();
							st = connection.createStatement();
							st.addBatch("drop table if exists Requests");
							st.addBatch("drop table if exists Users");
							st.addBatch("drop table if exists Cars");
							st.addBatch("drop table if exists Request_Types");
							st.addBatch("drop table if exists User_Types");
							st.addBatch("drop table if exists Request_Statuses");
							LOGGER.info("Creating database structure");
							st.addBatch("create table Cars (car_id INT PRIMARY KEY, model VARCHAR(30) NOT NULL, number VARCHAR(10) UNIQUE NOT NULL)");
							st.addBatch("create table User_Types (user_type_id INT PRIMARY KEY auto_increment, type_name VARCHAR(20) UNIQUE NOT NULL)");
							st.addBatch("create table Request_Types (request_type_id INT PRIMARY KEY auto_increment, type_name VARCHAR(20) UNIQUE NOT NULL)");
							st.addBatch("create table Request_Statuses (request_status_id INT PRIMARY KEY auto_increment, status_name VARCHAR(20) UNIQUE NOT NULL)");
							st.addBatch("create table Users (user_id INT PRIMARY KEY, user_type_id INT NOT NULL, name VARCHAR(30) UNIQUE NOT NULL, password VARCHAR(30), suspended BOOL default 0, FOREIGN KEY(`user_type_id`) REFERENCES User_Types(user_type_id))");
							st.addBatch("create table Requests (request_id INT PRIMARY KEY, request_type_id INT NOT NULL, request_status_id INT NOT NULL, start_time timestamp, end_time timestamp, car_id INT NOT NULL, driver_id INT NOT NULL, car_state VARCHAR(50) NOT NULL, FOREIGN KEY(`driver_id`) REFERENCES Users(user_id), FOREIGN KEY(`car_id`) REFERENCES Cars(car_id), FOREIGN KEY(`driver_id`) REFERENCES Request_Statuses(request_status_id), FOREIGN KEY(`request_type_id`) REFERENCES Request_Types(request_type_id))");
							LOGGER.info("Inserting initial necessary data");
							st.addBatch("INSERT INTO user_types VALUES(null, 'driver')");
							st.addBatch("INSERT INTO user_types VALUES(null, 'dispatcher')");
							st.addBatch("INSERT INTO user_types VALUES(null, 'admin')");
							st.addBatch("INSERT INTO Request_Types VALUES(null, 'race')");
							st.addBatch("INSERT INTO Request_Types VALUES(null, 'repair')");
							st.addBatch("INSERT INTO Request_Statuses VALUES(null, 'COMPLETED')");
							st.addBatch("INSERT INTO Request_Statuses VALUES(null, 'ACTIVE')");
							st.addBatch("INSERT INTO Request_Statuses VALUES(null, 'CANCELED')");
							
							st.addBatch("INSERT INTO Users VALUES (1, 3, 'root', 'root', 0)");
							st.executeBatch();
						} catch (SQLException e) { 
							throw new IOException();
						} finally{
							try {
								if (st!=null)
									st.close();
							} catch (SQLException e) {
							}
						}
					}
				};
				
			}
		}
	}


	public static Autobase getInstance() {
		if (instance == null) {
			instance = new Autobase();
		}
		return instance;
	}	
	
    public int logIn(String username, String password) throws AuthorizationException {
    	LOGGER.info(username + " tries to log in with password " + password);
    	
    	User foundUser = null;

    	for(User user: users.values()) {
    		if (user.getUsername().equals(username) 
    				&& user.getPassword().equals(password)) {
    			foundUser = user;
    			break;
    		}
    	}
    	
    	if (foundUser == null) {
    		LOGGER.error("Authorization error: wrong authorization data");
    		throw new AuthorizationException("Authorization error: wrong authorization data");
    	}

    	if (sessions.size() == Integer.MAX_VALUE) {
    		LOGGER.fatal("Authorization error: server overload");
    		throw new RuntimeException("Authorization error: server overload");
    	}
    	int sid;
    	do 
    	{
    		sid = rnd.nextInt();    		
    	} 
    	while (sessions.containsKey(sid));
    	
    	sessions.put(sid, foundUser);
    	
    	LOGGER.info(username + " successfully authorized in system as " + checkPermissions(sid).toString().toLowerCase());
    	
        return sid;
    }

    public UserType checkPermissions(int sid){
    	User user = getUserBySid(sid);
		if (user instanceof Driver) {
			return UserType.DRIVER;
		} else if (user instanceof Dispatcher) {
			return UserType.DISPATCHER;
		} else if (user instanceof Admin) {
			return UserType.ADMIN;
		} else {
			return UserType.GUEST;
		}

    }
    
    public void checkPermissions(int sid, UserType ... userTypes) { 		
    	boolean found = false;
		User user = getUserBySid(sid);
	
    	for (UserType userType: userTypes) {
    		if (checkPermissions(sid) == userType) {
    			found = true;
				if (user.isSuspended()) {
    				LOGGER.error(user.getUsername() + " is suspended and can't perform this operation");
    				throw new PermissionsException("Permission exception: user is suspended");
    			}
    			break;
    		}
    	}
    	if (!found) {
    		LOGGER.error(user + " has no permissions for performing operation");
    		throw new PermissionsException("Permission exception: user can't do this operation");
    	}
    }
    
    private void checkArgs(Object ... args) { 		
    	for (Object arg: args) {
    		if (arg == null) {
    				LOGGER.error("Illegal null pointer argument");
    				throw new NullPointerException("Illegal null pointer argument");
    		}
    	}
    }

	private User getUserBySid(int sid) {
		return sessions.get(sid);
	}

    public void logOut(int sid) {
    	User user = getUserBySid(sid);
    	if (user != null)
    		LOGGER.info(user.getUsername() + " left the system");
    	else
    		 //user already logged out
    		return ;
    	
    	sessions.remove(sid);
    	try {
    		LOGGER.info("Saving data to database");
    		db.saveData();
		} catch (IOException e) {
			LOGGER.fatal("Error while saving data to database");
		}
    }

	@Override
	public Request createRepairRequest(int sid, Car car, String carState) {
		LOGGER.info("creating repair request...");
		checkPermissions(sid, UserType.DRIVER);
		checkArgs(car);
		Driver driver = (Driver)getUserBySid(sid);
		int id = requests.size();
		Request request = new Request(id, RequestType.REPAIR, driver, car, carState);
		requests.put(id, request);
		LOGGER.info(driver.getUsername() + ": repair request ¹ " + id + " successfully created");
		return request;
	}

	@Override
	public void markRaceCompleted(int sid, Request race, String carState) {
		LOGGER.info("marking race request ¹" + race.getId() + " as completed");
		checkPermissions(sid, UserType.DRIVER);
		checkArgs(race);
		race.setStatus(RequestStatus.COMPLETED);
		race.setCarState(carState);
		LOGGER.info(getUserBySid(sid).getUsername() + ": repair request successfully marked");
	}

	@Override
	public void setUserSuspended(int sid, User user, boolean value) {
		LOGGER.info("setting " + user.getUsername() + " suspended=" + value);
		checkPermissions(sid, UserType.ADMIN);
		checkArgs(user);
		if (user instanceof Admin) {
			LOGGER.error("Admin can't be suspended");
			throw new IllegalArgumentException("Admin can't be suspended");
		}
		user.setSuspended(value);
		LOGGER.info(getUserBySid(sid).getUsername() + ": " + user.getUsername() + " successfully suspended");
	}

	@Override
	public void deleteUser(int sid, User userForDeleting) {
		LOGGER.info("deleting " + userForDeleting.getUsername());
		checkPermissions(sid, UserType.ADMIN);
		checkArgs(userForDeleting);
		if (userForDeleting instanceof Admin) {
			LOGGER.error("Admin can't be deleted");
			throw new IllegalArgumentException("Admin can't be deleted");
		}
		users.remove(userForDeleting.getId());
		LOGGER.info(getUserBySid(sid).getUsername() + ": " + userForDeleting.getUsername() + " successfully deleted");
	}

	@Override
	public ArrayList<User> getUsersList(int sid) {
		checkPermissions(sid, UserType.ADMIN);
		ArrayList<User> result = new ArrayList<User>();
		for (User user: users.values()) {
			result.add(user);
		}
		return result;
	}

	@Override
	public void setUserPassword(int sid, User user, String password) {
		LOGGER.info("setting new password for " + user.getUsername());
		checkPermissions(sid, UserType.ADMIN);
		checkArgs(user);
		user.changePassword(password);	
		LOGGER.info(getUserBySid(sid).getUsername() + ": password for " + user.getUsername() + " successfully setted");
	}

	@Override
	public Car getCarById(int sid, int id) {
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER);
		Car car =  cars.get(id);
		if (car != null) {
			return car;
		} else {
			LOGGER.warn(getUserBySid(sid).getUsername() + ": using illegal car id");
			throw new IndexOutOfBoundsException("No car with such id");
		}
	}

	@Override
	public User addUser(int sid, String username, String password, UserType userType) throws AddUserException {
		LOGGER.info("adding new " + userType.toString().toLowerCase() + " with username=" + username);
		checkPermissions(sid, UserType.ADMIN);
		for(User user: users.values()) {
    		if (user.getUsername().equals(username)) {
    			LOGGER.error("user with username " + username + " already exists");
    			throw new AddUserException("Add user error: user with such  username already exists");
    		}
		}
		int id = users.size() + 1;;
		switch(userType) {
		case DRIVER:
			users.put(id, new Driver(id, username, password));
			break;
		case DISPATCHER:
			users.put(id, new Dispatcher(id, username, password));
			break;
		}
		LOGGER.info(getUserBySid(sid).getUsername() + ": " + userType.toString().toLowerCase() + " with username=" + username + " successfully added");
		return users.get(id);
	}

	@Override
	public User getUserById(int sid, int id) {
		checkPermissions(sid, UserType.ADMIN);
		User user = users.get(id);
		if (user != null) {
			return user;
		} else {
			LOGGER.warn(getUserBySid(sid).getUsername() + ": using illegal user id");
			throw new IndexOutOfBoundsException("No user with such id");
		}
	}

	@Override
	public Request createRaceRequest(int sid, Driver driver, Car car) {
		LOGGER.info("creating race request...");
		checkPermissions(sid, UserType.DISPATCHER);
		checkArgs(driver, car);
		if (driver.isSuspended()) {
			LOGGER.error("driver " + driver.getUsername() + " is suspended");
			throw new IllegalArgumentException("driver ¹" + driver.getId() + " is suspended");
		}
		int id = requests.size() + 1;
		Request request = new Request(id, RequestType.RACE, driver, car, "empty");
		requests.put(id, request);
		LOGGER.info(getUserBySid(sid).getUsername() + ": race request ¹ " + id + " successfully created");
		return request;
	}

	@Override
	public void setDriverSuspended(int sid, Driver driver, boolean value) {
		LOGGER.info("setting driver with username " + driver.getUsername() + " suspended");
		checkPermissions(sid, UserType.DISPATCHER);
		checkArgs(driver);
		driver.setSuspended(value);
		LOGGER.info(getUserBySid(sid).getUsername() + ": driver " + driver.getUsername() + " suspend status successfully changed to " + value);
	}

	@Override
	public ArrayList<Driver> getAvailableDrivers(int sid) {
		checkPermissions(sid, UserType.DISPATCHER);
		ArrayList<Driver> result = new ArrayList<Driver>();
		
		next_driver:
		for(User user: users.values())
			if (user instanceof Driver) {
				Driver driver = (Driver)user;
				for(Request request: getRaceRequests(sid))
					if (request.getStatus() == RequestStatus.ACTIVE && request.getDriver().equals(driver))
						continue next_driver;	
				result.add((Driver)user);
			}
		return result;
	}

	@Override
	public ArrayList<Request> getRepairRequests(int sid) {
		checkPermissions(sid, UserType.DISPATCHER);
		ArrayList<Request> result = new ArrayList<Request>();
		for(Request request: requests.values()) {
			if (request.getType() == RequestType.REPAIR) {
				result.add(request);
			}
		}
		return result;
	}

	@Override
	public void setRequestStatus(int sid, Request request, RequestStatus status) {
		LOGGER.info("setting request status");
		checkPermissions(sid, UserType.DISPATCHER);
		checkArgs(request);
		request.setStatus(status);
		LOGGER.info(getUserBySid(sid).getUsername() + ": status of request ¹" + request.getId() + " successfully changed to " + status);
	}

	@Override
	public Request getRequestById(int sid, int id) {
		checkPermissions(sid, UserType.DISPATCHER);
		Request request = requests.get(id);
		if (request != null) {
			return request;
		} else {
			LOGGER.warn(getUserBySid(sid).getUsername() + ": using illegal request id");
			throw new IndexOutOfBoundsException("No request with such id");
		}
	}

	@Override
	public Driver getDriverById(int sid, int id) {
		checkPermissions(sid, UserType.DISPATCHER);
		Driver driver = (Driver)users.get(id);
		if (driver != null) {
			return driver;
		} else {
			LOGGER.warn(getUserBySid(sid).getUsername() + ": using illegal driver id");
			throw new IndexOutOfBoundsException("No driver with such id");
		}
	}

	@Override
	public ArrayList<Car> getAvailableCars(int sid) {
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER);
		ArrayList<Car> result = new ArrayList<Car>();

		next_car:
		for(Car car: cars.values()) {
			for(Request request: getRaceRequests(sid))
				if (request.getStatus() == RequestStatus.ACTIVE && request.getCar().equals(car))
					continue next_car;
			result.add(car);
		}	
		return result;
	}

	@Override
	public ArrayList<Request> getRaceRequests(int sid) {
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER);
		ArrayList<Request> result = new ArrayList<Request>();
		User user = getUserBySid(sid);
		if (user instanceof Driver) {
			Driver driver = (Driver)user;
			for(Request request: requests.values()) {
				if (request.getType() == RequestType.RACE && request.getDriver().equals(driver)) 
					result.add(request);
			}
		} else if (user instanceof Dispatcher) {
			for(Request request: requests.values()) {
				if (request.getType() == RequestType.RACE) 
					result.add(request);
			}
		}
		return result;
	}

	@Override
	public void changePassword(int sid, String password) {
		LOGGER.info("setting new password");
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER, UserType.ADMIN);	
		User user = getUserBySid(sid);
		user.changePassword(password);
		LOGGER.info(user.getUsername() + ": password was changed");
	}

	@Override
	public void setSuspended(int sid, boolean value) {
		LOGGER.info("setting suspend status");
		checkPermissions(sid, UserType.ADMIN);
		User user = getUserBySid(sid);
		user.setSuspended(value);	
		LOGGER.info(user.getUsername() + ": suspend status was changed to " + value);
	}

	@Override
	public Car addCar(int sid, String number, String brand) throws AddCarException {
		LOGGER.info("adding new car " + brand + " ¹" + number);
		checkPermissions(sid, UserType.DISPATCHER);
		for(Car car: cars.values()) {
    		if (car.getNumber().equals(number)) {
    			LOGGER.error("car with number " + number + " already exists");
    			throw new AddCarException("Add car error: car with such  number already exists");
    		}
		}
		int id = cars.size() + 1;
		Car car = new Car(id, number, brand);
		cars.put(id, car);	
		LOGGER.info(getUserBySid(sid).getUsername() + ": new car " + brand + " ¹" + number + " added successfully");
		return car;
	}

	@Override
	public Request getRaceRequestById(int sid, int id) {
		checkPermissions(sid, UserType.DRIVER);
		Request request = requests.get(id);
		Driver driver = (Driver)getUserBySid(sid);
		if (request != null && request.getType() == RequestType.RACE && request.getDriver() == driver) {
			return request;
		} else {
			LOGGER.warn(getUserBySid(sid).getUsername() + ": using illegal race request id");
			throw new IndexOutOfBoundsException("No race request with such id for driver");
		}
	}

	@Override
	public void deleteCar(int sid, Car carForDeleting) {
		LOGGER.info("deleting " + carForDeleting.getBrand() + " ¹ " + carForDeleting.getNumber());
		checkPermissions(sid, UserType.DISPATCHER);
		checkArgs(carForDeleting);
		
		cars.remove(carForDeleting.getId());
		LOGGER.info(getUserBySid(sid).getUsername() + ": " + carForDeleting.getBrand() + " ¹ " + carForDeleting.getNumber() + "successfully deleted");		
	}
}
