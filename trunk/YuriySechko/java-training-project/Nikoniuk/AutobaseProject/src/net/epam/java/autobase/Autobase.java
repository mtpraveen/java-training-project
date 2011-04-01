package net.epam.java.autobase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Autobase implements IDispatcher, IAdmin, IDriver, IUser {
	
    private static Autobase instance;
    private static int requestsCount;
    private static int carsCount;
    private static int usersCount;
	private Map<Integer, User> sessions = new HashMap<Integer, User>();
	static private Random rnd = new Random();
	private Map<Integer, Request> requests = new HashMap<Integer, Request>();
	private Map<Integer, User> users = new HashMap<Integer, User>();	
	private Map<Integer, Car> cars = new HashMap<Integer, Car>();	

	/**
	 * 
	 */
	private Autobase() {
		int id = ++usersCount;
		users.put(id, new Admin(++usersCount, "root", "pass"));
	}

	public static Autobase getInstance() {
		if (instance == null) {
			instance = new Autobase();
		}
		return instance;
	}	
	
    public int logIn(String username, String password) throws AuthorizationException {
    	User foundUser = null;

    	for(User user: users.values()) {
    		if (user.getUsername().equals(username) 
    				&& user.getPassword().equals(password)) {
    			foundUser = user;
    			break;
    		}
    	}
    	
    	if (foundUser == null) {
    		throw new AuthorizationException("Authorization error: wrong authorization data");
    	}

    	if (sessions.size() == Integer.MAX_VALUE) {
    		throw new RuntimeException("Authorization error: server overload");
    	}
    	int sid;
    	do 
    	{
    		sid = rnd.nextInt();    		
    	} 
    	while (sessions.containsKey(sid));
    	
    	sessions.put(sid, foundUser);
    	
        return sid;
    }

    public UserType checkPermissions(int sid){
    	User user = sessions.get(sid);
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
    	for (UserType userType: userTypes) {
    		if (checkPermissions(sid) == userType) {
    			found = true;
    			if (sessions.get(sid).isSuspended()) {
    				throw new PermissionsException("Permission exception: user is suspended");
    			}
    			break;
    		}
    	}
    	if (!found) {
    		throw new PermissionsException("Permission exception: user can't do this operation");
    	}
    }

    public void logOut(int sid){
    	sessions.remove(sid);
    }

	@Override
	public Request createRepairRequest(int sid, Car car, String carState) {
		checkPermissions(sid, UserType.DRIVER);
		Driver driver = (Driver)sessions.get(sid);
		int id = ++requestsCount;
		Request request = new Request(id, RequestType.REPAIR, driver, car, carState);
		requests.put(id, request);
		return request;
	}

	@Override
	public void markRaceCompleted(int sid, Request race, String carState) {
		checkPermissions(sid, UserType.DRIVER);
		race.setStatus(RequestStatus.COMPLETED);
		race.setCarState(carState);
	}

	@Override
	public void setUserSuspended(int sid, User user, boolean value) {
		checkPermissions(sid, UserType.ADMIN);
		if (user instanceof Admin)
			throw new IllegalArgumentException("Admin can't be suspended");
		user.setSuspended(value);
	}

	@Override
	public void deleteUser(int sid, User userForDeleting) {
		checkPermissions(sid, UserType.ADMIN);
		if (userForDeleting instanceof Admin)
			throw new IllegalArgumentException("Admin can't be deleted");
		users.remove(userForDeleting.getId());
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
		checkPermissions(sid, UserType.ADMIN);
		user.changePassword(password);	
	}

	@Override
	public Car getCarById(int sid, int id) {
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER);
		Car car =  cars.get(id);
		if (car != null) {
			return car;
		} else {
			throw new IndexOutOfBoundsException("No car with such id");
		}
	}

	@Override
	public void addUser(int sid, String username, String password, UserType userType) throws addUserException {
		checkPermissions(sid, UserType.ADMIN);
		for(User user: users.values()) {
    		if (user.getUsername().equals(username)) {
    			throw new addUserException("Add user exception: this username already exists");
    		}
		}
		int id;
		switch(userType) {
		case DRIVER:
			id = ++usersCount;
			users.put(id, new Driver(id, username, password));
			break;
		case DISPATCHER:
			id = ++usersCount;
			users.put(id, new Dispatcher(id, username, password));
			break;
		}
	}

	@Override
	public User getUserById(int sid, int id) {
		checkPermissions(sid, UserType.ADMIN);
		return users.get(id);
	}

	@Override
	public Request createRaceRequest(int sid, Driver driver, Car car) {
		checkPermissions(sid, UserType.DISPATCHER);
		if (driver.isSuspended()) {
			throw new IllegalArgumentException("driver N" + driver.getId() + ":is suspended");
		}
		int id = ++requestsCount;
		Request request = new Request(id, RequestType.RACE, driver, car, "empty");
		requests.put(id, request);
		return request;
	}

	@Override
	public void setDriverSuspended(int sid, Driver driver, boolean value) {
		checkPermissions(sid, UserType.DISPATCHER);
		driver.setSuspended(value);
	}

	@Override
	public ArrayList<Driver> getAvailableDrivers(int sid) {
		checkPermissions(sid, UserType.DISPATCHER);
		ArrayList<Driver> result = new ArrayList<Driver>();
		for(User user: users.values()) {
			if (user instanceof Driver) {
				result.add((Driver)user);
			}
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
		checkPermissions(sid, UserType.DISPATCHER);
		request.setStatus(status);
	}

	@Override
	public Request getRequestById(int sid, int id) {
		checkPermissions(sid, UserType.DISPATCHER);
		Request request = requests.get(id);
		if (request != null) {
			return request;
		} else {
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
			throw new IndexOutOfBoundsException("No driver with such id");
		}
	}

	@Override
	public ArrayList<Car> getAvailableCars(int sid) {
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER);
		ArrayList<Car> result = new ArrayList<Car>();

		for(Car car: cars.values()) {
			result.add(car);
		}	
		return result;
	}

	@Override
	public ArrayList<Request> getRaceRequests(int sid) {
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER);
		ArrayList<Request> result = new ArrayList<Request>();
		User user = sessions.get(sid);
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
		checkPermissions(sid, UserType.DRIVER, UserType.DISPATCHER, UserType.ADMIN);	
		User user = sessions.get(sid);
		if (user != null) {
			user.changePassword(password);		
		}
	}

	@Override
	public void setSuspended(int sid, boolean value) {
		checkPermissions(sid, UserType.ADMIN);
		User user = sessions.get(sid);
		if (user != null) {
			user.setSuspended(value);		
		}
	}

	@Override
	public Car addCar(String number, String brand) {
		int id = ++carsCount;
		Car car = new Car(id, number, brand);
		cars.put(id, car);	
		return car;
	}

	@Override
	public Request getRaceRequestById(int sid, int id) {
		checkPermissions(sid, UserType.DRIVER);
		Request request = requests.get(id);
		Driver driver = (Driver)sessions.get(sid);
		if (request != null && request.getType() == RequestType.RACE && request.getDriver() == driver) {
			return request;
		} else {
			throw new IndexOutOfBoundsException("No race request with such id for driver");
		}
	}

}
