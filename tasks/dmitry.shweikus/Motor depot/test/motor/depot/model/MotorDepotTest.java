package motor.depot.model;
/**
 * 
 */


import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import motor.depot.listclasses.ListWithIds;
import motor.depot.model.Car;
import motor.depot.model.Driver;
import motor.depot.model.MotorDepot;
import motor.depot.model.RequestForRepair;
import motor.depot.model.Trip;
import motor.depot.model.enums.TripState;
import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractStorage;
import motor.depot.storages.interfaces.ICanBeSaved;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dima
 *
 */
public class MotorDepotTest
{

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#getDispatcher()}.
	 */
	@Test
	public void testGetDispatcher()
	{
		assertNotNull(getDefaultMotorDepot().getDispatcher());
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#MotorDepot(motor.depot.storages.interfaces.AbstractStorage)}.
	 */
	@Test
	public void testMotorDepot()
	{
		assertNotNull(getDefaultMotorDepot());
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#save(motor.depot.storages.interfaces.AbstractStorage)}.
	 */
	@Test
	public void testSaveAndLoad()
	{
		MotorDepot depot = getDefaultMotorDepot();
		Driver driver  = depot.addUser("ivan", "555");
		Driver driver2 = depot.addUser("fedor", "777");
		Car car  = depot.addCar("bmw", "1111", "good", "-");
		Car car2 = depot.addCar("vw", "2222", "good", "-");
		AbstractStorage storage = new AbstractStorage() {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = null;
			ByteArrayInputStream byteArrayInputStream = null;
			ObjectInputStream objectInputStream = null;
			@Override
			public void save()
			{
				
			}
			
			@Override
			public boolean load()
			{
				byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				return true;
			}
			
			@Override
			public void initSave()
			{
				
			}
			
			@Override
			public ObjectOutputStream getOutputStream()
			{
				if (objectOutputStream == null)
				{
					try
					{
						objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				return objectOutputStream;
			}
			
			@Override
			public ObjectInputStream getInputStream()
			{
				if(objectInputStream == null)
				{
					try
					{
						objectInputStream = new ObjectInputStream(byteArrayInputStream);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				return objectInputStream;
			}
		};
		depot.save(storage);
		MotorDepot depot2 = new MotorDepot(storage);
		assertNotNull(depot2);
		assertEquals(depot.drivers.size(), depot2.drivers.size());
		assertEquals(depot.cars.size(), depot2.cars.size());
		assertEquals(depot.trips.size(), depot2.trips.size());
		assertEquals(depot.requestsForRepair.size(), depot2.requestsForRepair.size());
		
		assertEquals(depot.drivers, depot2.drivers);
		assertEquals(depot.cars, depot2.cars);
		assertEquals(depot.trips, depot2.trips);
		assertEquals(depot.requestsForRepair, depot2.requestsForRepair);
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#findUser(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testFindUser_invalidUser()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertNull(depot.findUser("ivan ivan",""));
	}
	@Test
	public void testFindUser_invalidUser2()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertNull(depot.findUser("ivan","5"));
	}

	@Test
	public void testFindUser_validUser()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertNotNull(depot.findUser("admin","123"));
	}

	@Test
	public void testFindUser_validUser2()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertNotNull(depot.findUser("admin","123").getLogin().equals("admin"));
	}

	@Test
	public void testFindUser_invalidUser3()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertNull(depot.findUser("IVAN","555"));
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#findUserIgnoreCase(java.lang.String)}.
	 */
	@Test
	public void testFindUserIgnoreCase()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertNotNull(depot.findUserIgnoreCase("IVAN"));
	}
	@Test
	public void testFindUserIgnoreCase2()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertEquals(depot.findUserIgnoreCase("IVAN").getLogin(),"ivan");
	}
	@Test
	public void testFindUserIgnoreCase3()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("ivan", "555");
		depot.addUser("fedor", "777");
		assertNull(depot.findUserIgnoreCase("petr"));
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#addUser(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddUser()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("fedor", "777");
		Driver driver = depot.addUser("ivan", "555");
		assertNotNull(driver);
	}
	@Test
	public void testAddUser2()
	{
		MotorDepot depot = getDefaultMotorDepot();
		depot.addUser("fedor", "777");
		Driver driver = depot.addUser("ivan", "555");
		assertEquals("ivan", driver.getLogin());
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#addRequestForRepair(motor.depot.model.Driver, motor.depot.model.Car, java.lang.String)}.
	 */
	@Test
	public void testAddRequestForRepair()
	{
		MotorDepot depot = getDefaultMotorDepot();
		Driver driver = depot.addUser("ivan", "555");
		Car car  = depot.addCar("bmw", "1111", "good", "-");
		Car car2 = depot.addCar("vw", "2222", "good", "-");
		assertNotNull(depot.addRequestForRepair(driver, car, "bad state"));
		RequestForRepair requestForRepair = depot.addRequestForRepair(driver, car2, "very bad state");
		assertNotNull(requestForRepair);
		assertEquals(driver, requestForRepair.getDriver());
		assertEquals(car2, requestForRepair.getCar());
		assertEquals("very bad state", requestForRepair.getDescription());
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#addCar(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddCar()
	{
		MotorDepot depot = getDefaultMotorDepot();
		assertNotNull(depot.addCar("bmw", "1111", "good", "-"));
		Car car2 = depot.addCar("vw", "2222", "good", "-");
		assertNotNull(car2);
		assertEquals("vw", car2.getModel());
		assertEquals("2222", car2.getNumber());
		assertEquals("good", car2.getState());
		assertEquals("-", car2.getDescription());
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#getTripsByState(motor.depot.model.enums.TripState)}.
	 */
	@Test
	public void testGetTripsByState_started()
	{
		MotorDepot depot = getDefaultMotorDepot();
		Driver driver = depot.addUser("ivan", "111");
		Driver driver2 = depot.addUser("petr", "111");
		Driver driver3 = depot.addUser("fedor", "111");
		Car car  = depot.addCar("bmw", "1111", "good", "-");
		Car car2 = depot.addCar("vw", "2222", "good", "-");
		Car car3 = depot.addCar("vw", "3333", "good", "-");
		Trip trip = depot.addTrip(driver, car, "berlin", "moscow", "--");
		Trip trip2 = depot.addTrip(driver2, car2, "berlin", "moscow", "--");
		Trip trip3 = depot.addTrip(driver3, car3, "berlin", "moscow", "--");
		trip.setState(TripState.FINISHED);
		assertNotSame(trip,  trip2);
		assertNotSame(trip,  trip3);
		assertNotSame(trip2, trip3);
		//
		ListWithIds<Trip> list = depot.getTripsByState(TripState.STARTED);
		assertEquals(2, list.size());
		assertNotSame(-1, list.indexOf(trip2));
		assertNotSame(-1, list.indexOf(trip3));
	}

	@Test
	public void testGetTripsByState_finished()
	{
		MotorDepot depot = getDefaultMotorDepot();
		Driver driver = depot.addUser("ivan", "111");
		Driver driver2 = depot.addUser("petr", "111");
		Driver driver3 = depot.addUser("fedor", "111");
		Car car  = depot.addCar("bmw", "1111", "good", "-");
		Car car2 = depot.addCar("vw", "2222", "good", "-");
		Car car3 = depot.addCar("vw", "3333", "good", "-");
		Trip trip = depot.addTrip(driver, car, "berlin", "moscow", "--");
		Trip trip2 = depot.addTrip(driver2, car2, "berlin", "moscow", "--");
		Trip trip3 = depot.addTrip(driver3, car3, "berlin", "moscow", "--");
		trip.setState(TripState.FINISHED);
		assertNotSame(trip,  trip2);
		assertNotSame(trip,  trip3);
		assertNotSame(trip2, trip3);
		//
		ListWithIds<Trip> list = depot.getTripsByState(TripState.FINISHED);
		assertEquals(1, list.size());
		assertNotSame(-1, list.indexOf(trip));
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#setNewDriverActiveState(motor.depot.model.Driver, boolean)}.
	 */
	@Test
	public void testSetNewDriverActiveState()
	{
		MotorDepot depot = getDefaultMotorDepot();
		Driver driver = depot.addUser("ivan", "111");
		assertTrue(depot.setNewDriverActiveState(driver, false));
		assertFalse(driver.isActive());
		assertTrue(depot.setNewDriverActiveState(driver, true));
		assertTrue(driver.isActive());
		assertTrue(depot.setNewDriverActiveState(driver, false));
		assertFalse(driver.isActive());
		assertTrue(depot.setNewDriverActiveState(driver, true));
		assertTrue(driver.isActive());
	}

	@Test
	public void testSetNewDriverActiveState_forDriverAtTrip()
	{
		MotorDepot depot = getDefaultMotorDepot();
		Driver driver = depot.addUser("ivan", "111");
		Car car = depot.addCar("bmw", "1111", "good", "-");
		depot.addTrip(driver, car, "1", "2", "---");
		assertFalse(depot.setNewDriverActiveState(driver, false));
		assertTrue(driver.isActive());
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#addTrip(motor.depot.model.Driver, motor.depot.model.Car, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddTrip()
	{
		MotorDepot depot = getDefaultMotorDepot();
		Car car = depot.addCar("vw", "2222", "good", "-");
		Driver driver = depot.addUser("ivan", "111");
		Trip trip = depot.addTrip(driver, car, "berlin", "moscow", "---");
		assertNotNull(trip);
		assertEquals(car, trip.getCar());
		assertEquals(driver, trip.getDriver());
		assertEquals("---", trip.getDescription());
		assertEquals("berlin", trip.getStart());
		assertEquals("moscow", trip.getDestination());
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#getAllListsWithIds()}.
	 */
	@Test
	public void testGetAllListsWithIds()
	{
		MotorDepot depot = getDefaultMotorDepot();
		ArrayList<ListWithIds<? extends ICanBeSaved>> list = depot.getAllListsWithIds();
		assertNotNull(list);
		assertEquals(4, list.size());
		for (ListWithIds<? extends ICanBeSaved> listWithIds : list)
		{
			assertNotNull(listWithIds);
		}
	}

	/**
	 * Test method for {@link motor.depot.model.MotorDepot#getLoadableFromCsvList(java.lang.String)}.
	 */
	@Test
	public void testGetLoadableFromCsvList_car()
	{
		MotorDepot depot = getDefaultMotorDepot();
		ListWithIds<? extends ICanBeSaved> list = depot.getLoadableFromCsvList("car");
		assertNotNull(list);
		assertNotNull(list.getPrototype());
		assertEquals("car", list.getPrototype().getClassId());
	}

	@Test
	public void testGetLoadableFromCsvList_driver()
	{
		MotorDepot depot = getDefaultMotorDepot();
		ListWithIds<? extends ICanBeSaved> list = depot.getLoadableFromCsvList("driver");
		assertNotNull(list);
		assertNotNull(list.getPrototype());
		assertEquals("driver", list.getPrototype().getClassId());
	}

	@Test
	public void testGetLoadableFromCsvList_invalid()
	{
		MotorDepot depot = getDefaultMotorDepot();
		ListWithIds<? extends ICanBeSaved> list = depot.getLoadableFromCsvList("invalid");
		assertNull(list);
	}
	
	private MotorDepot getDefaultMotorDepot()
	{
		return new MotorDepot(new AbstractStorage() {
			
			@Override
			public void save()
			{
				
			}
			
		
			@Override
			public boolean load()
			{
				return false;
			}
			
			@Override
			public void initSave()
			{
				
			}
			
			@Override
			public ObjectOutputStream getOutputStream()
			{
				return null;
			}
			
			@Override
			public ObjectInputStream getInputStream()
			{
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
