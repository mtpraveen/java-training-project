/**
 * 
 */
package motor.depot.model;

import java.util.ArrayList;

import motor.depot.listclasses.ListWithIds;
import motor.depot.storages.csv.CsvStorage;
import motor.depot.storages.interfaces.AbstractStorage;
import motor.depot.storages.interfaces.ICanBeSaved;

/**
 * @author dima
 *
 */
public class MotorDepot
{
	Dispatcher dispatcher;
	/**
	 * @return the dispatcher
	 */
	public Dispatcher getDispatcher()
	{
		return dispatcher;
	}
	public ListWithIds<Driver> drivers = new ListWithIds<Driver>(new Driver());
	public ListWithIds<Car> cars = new ListWithIds<Car>(new Car());
	public ListWithIds<Trip> trips = new ListWithIds<Trip>(new Trip());
	public ListWithIds<RequestForRepair> requestsForRepair = new ListWithIds<RequestForRepair>(new RequestForRepair());
	private static MotorDepot _instance;
	public Car findCarById(int id)
	{
		return cars.findById(id);
	}
	public Trip findTripById(int id)
	{
		return trips.findById(id);
	}	
	public RequestForRepair findRequestForReairById(int id)
	{
		return requestsForRepair.findById(id);
	}
	public Driver findDriverById(int id)
	{
		return drivers.findById(id);
	}
	
	public MotorDepot(AbstractStorage storage)
	{
		ListWithIds<Dispatcher> dispatchers = new ListWithIds<Dispatcher>(new Dispatcher());
		ArrayList<ListWithIds<? extends ICanBeSaved>> arrays = new ArrayList<ListWithIds<? extends ICanBeSaved>>();
		arrays.add(dispatchers);
		arrays.add(drivers);
		arrays.add(cars);
		arrays.add(trips);
		arrays.add(requestsForRepair);
		//
		storage.load();
		for (ListWithIds<? extends ICanBeSaved> list : arrays)
		{
			list.loadPrimitives(storage);
		}
		for (ListWithIds<? extends ICanBeSaved> list : arrays)
		{
			list.loadObjects(storage, this);
		}
		//
		if (dispatchers.size() == 0)
		{
			dispatcher = new Dispatcher();
			dispatcher.setLogin("admin");
			dispatcher.setPassword("123");
		}
		else
			dispatcher = dispatchers.get(0);
	}
	public void save(AbstractStorage storage)
	{
		ListWithIds<Dispatcher> dispatchers = new ListWithIds<Dispatcher>(new Dispatcher());
		dispatchers.add(dispatcher);
		//
		dispatchers.save(storage);
		drivers.save(storage);
		cars.save(storage);
		trips.save(storage);
		requestsForRepair.save(storage);
		storage.save();
	}
	public static MotorDepot instance()
	{
		if (_instance == null)
		{
			_instance = new MotorDepot(new CsvStorage());
		}
		return _instance;
	}
	public User findUser(String login, String password)
	{
		if (dispatcher.mathLoginData(login, password))
			return dispatcher;
		for (Driver driver  : drivers) {
			if (driver.mathLoginData(login, password))
				return driver;
		}
		return null;
	}
	public User findUserIgnoreCase(String login)
	{
		if (dispatcher.getLogin().equalsIgnoreCase(login))
			return dispatcher;
		for (Driver driver  : drivers) {
			if (driver.getLogin().equalsIgnoreCase(login))
				return driver;
		}
		return null;
	}
	public int generateNewId()
	{
		return dispatcher.generateNewId();
	}
	/**
	 * @param userName
	 * @param password
	 */
	public Driver addUser(String userName, String password)
	{
		Driver driver = new Driver();
		driver.setLogin(userName);
		driver.setPassword(password);
		driver.setId(generateNewId());
		drivers.add(driver);
		return driver;
	}
	public RequestForRepair addRequestForRepair(Driver driver, Car car, String description)
	{
		if (!driver.isActive())
			return null;
		RequestForRepair request = new RequestForRepair();
		request.setId(dispatcher.generateNewId());
		request.setCar(car);
		request.setDescription(description);
		request.setDriver(driver);
		requestsForRepair.add(request);
		return request;
	}
	
	public Car addCar(String model,String number,String state, String description)
	{
		Car car = new Car();
		car.setId(generateNewId());
		car.setNumber(number);
		car.setDescription(description);
		car.setModel(model);
		car.setState(state);
		cars.add(car);
		return car;
	}
}
