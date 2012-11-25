/**
 * 
 */
package motor.depot.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import motor.depot.listclasses.ListWithIds;
import motor.depot.model.enums.TripState;
import motor.depot.storages.csv.Storage;
import motor.depot.storages.interfaces.AbstractStorage;
import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ILoadableFromCsv;

/**
 * @author dima
 *
 */
public class MotorDepot
{
	Dispatcher dispatcher;
	private static final Logger LOGGER = Logger.getLogger(MotorDepot.class);
	private static final long serialVersionUID = 565645645688l;
	public ListWithIds<Driver> drivers = new ListWithIds<Driver>(new Driver());
	public ListWithIds<Car> cars = new ListWithIds<Car>(new Car());
	public ListWithIds<Trip> trips = new ListWithIds<Trip>(new Trip());
	public ListWithIds<RequestForRepair> requestsForRepair = new ListWithIds<RequestForRepair>(new RequestForRepair());
	private static MotorDepot _instance;
	/**
	 * @return the dispatcher
	 */
	public Dispatcher getDispatcher()
	{
		return dispatcher;
	}
	/*public Car findCarById(int id)
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
	}*/
	
	public MotorDepot(AbstractStorage storage)  
	{
		ListWithIds<Dispatcher> dispatchers = new ListWithIds<Dispatcher>(new Dispatcher());
		//
		if (storage.load())
		{
			ObjectInputStream stream = storage.getInputStream();
			Object obj;
			try {
				obj = stream.readObject();
				while(obj!=null)
				{
					dispatchers.addObjectIfMatchType(obj);
					drivers.addObjectIfMatchType(obj);
					cars.addObjectIfMatchType(obj);
					trips.addObjectIfMatchType(obj);
					requestsForRepair.addObjectIfMatchType(obj);
					obj = stream.readObject();
				}
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		/*
		 * 		ArrayList<ListWithIds<? extends ICanBeSaved>> arrays = new ArrayList<ListWithIds<? extends ICanBeSaved>>();
		arrays.add(dispatchers);
		arrays.add(drivers);
		arrays.add(cars);
		arrays.add(trips);
		arrays.add(requestsForRepair);

		 * for (ListWithIds<? extends ICanBeSaved> list : arrays)
		{
			list.loadPrimitives(storage);
		}
		for (ListWithIds<? extends ICanBeSaved> list : arrays)
		{
			list.loadObjects(storage, this);
		}*/
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
		storage.initSave();
		ListWithIds<Dispatcher> dispatchers = new ListWithIds<Dispatcher>(new Dispatcher());
		dispatchers.add(dispatcher);
		//
		try {
			dispatchers.saveToStream(storage.getOutputStream());
			drivers.saveToStream(storage.getOutputStream());
			cars.saveToStream(storage.getOutputStream());
			trips.saveToStream(storage.getOutputStream());
			requestsForRepair.saveToStream(storage.getOutputStream());
		} catch (IOException e) {
			LOGGER.error("IOException by saving database", e);
		}
		storage.save();
	}
	public static MotorDepot getInstance()
	{
		if (_instance == null)
		{
			_instance = new MotorDepot(new Storage());
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
	@Deprecated
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
		//driver.setId(generateNewId());
		drivers.add(driver);
		return driver;
	}
	public RequestForRepair addRequestForRepair(Driver driver, Car car, String description)
	{
		if (!driver.isActive())
			return null;
		RequestForRepair request = new RequestForRepair();
		//request.setId(dispatcher.generateNewId());
		request.setCar(car);
		request.setDescription(description);
		request.setDriver(driver);
		requestsForRepair.add(request);
		return request;
	}
	
	public Car addCar(String model,String number,String state, String description)
	{
		Car car = new Car();
		//car.setId(generateNewId());
		car.setNumber(number);
		car.setDescription(description);
		car.setModel(model);
		car.setState(state);
		cars.add(car);
		return car;
	}
	
	public ListWithIds<Trip> getTripsByState(TripState state)
	{
		ListWithIds<Trip> res = new ListWithIds<Trip>(new Trip());
		for (Trip trip : trips) {
			if(trip.state == state)
				res.add(trip);
		}
		return res;
	}
	
	public boolean setNewDriverActiveState(Driver driver, boolean isActive)
	{
		if (!isActive)
		{
			for (Trip trip : getTripsByState(TripState.STARTED))
			{
				if(trip.driver==driver)
					return false;
			}
		}
		driver.setActive(isActive);
		return true;
	}
	
	public Trip addTrip(Driver driver, Car car, String start, String destination, String description)
	{
		if(!driver.active)
			return null;
		for (Trip trip : getTripsByState(TripState.STARTED))
		{
			if(trip.car==car)
				return null;
		}
		Trip trip = new Trip();
		//trip.setId(generateNewId());
		trip.setDriver(driver);
		trip.setCar(car);
		trip.setStart(start);
		trip.setDestination(destination);
		trip.setDescription(description);
		trips.add(trip);
		return trip;
	}
	public ArrayList<ListWithIds<? extends ICanBeSaved>> getAllListsWithIds()
	{
		ArrayList<ListWithIds<? extends ICanBeSaved>> res = new ArrayList<ListWithIds<? extends ICanBeSaved>>();
		res.add(drivers);
		res.add(requestsForRepair);
		res.add(cars);
		res.add(trips);
		return res;
	}
	public ListWithIds<? extends ICanBeSaved> getLoadableFromCsvList(String name)
	{
		for (ListWithIds<? extends ICanBeSaved> list : getAllListsWithIds())
		{
			if(list.getPrototype() instanceof ILoadableFromCsv)
			{
				if(list.getPrototype().getClassId().equalsIgnoreCase(name))
					return list;
			}
		}
		return null;
	}
	
}
