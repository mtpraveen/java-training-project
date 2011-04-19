/**
 * 
 */
package autobase.test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import autobase.exceptions.AddCarException;
import autobase.exceptions.AddUserException;
import autobase.exceptions.AuthorizationException;
import autobase.model.Admin;
import autobase.model.Car;
import autobase.model.Dispatcher;
import autobase.model.Driver;
import autobase.model.Request;
import autobase.model.RequestStatus;
import autobase.model.RequestType;
import autobase.model.UserType;

/**
 * @author Alexander Nikoniuk
 *
 */
public class TestDriver {

	/* TODO Change username and password to the admin account values
	 *  if you have some problems with permissions while testing*/
	private static final String adminName = "root";
	private static final String adminPass = "root";
	private static Admin admin;
	private static int admin_sid;
	
	private static Driver driver;
	private static int driver_sid;
	
	private static Dispatcher dispatcher;
	private static int dispatch_sid;

	private static Car car;
	
	@BeforeClass
	public static void setUp() {
		final String dispName = "disp1234234";
		final String drivName = "driv53257234";
		final String carNumber = "2343jojoijoi";
		
		admin = new Admin();

		try {
			admin_sid = admin.logIn(adminName, adminPass);

			try {
				driver = (Driver)admin.addUser(admin_sid, drivName, "pass", UserType.DRIVER);
			} catch (AddUserException e) {
				fail(e.toString() + " .Change drivName variable in setUp method or delete user with such name");
			}		
			
			try {
				dispatcher = (Dispatcher)admin.addUser(admin_sid, dispName, "keyword", UserType.DISPATCHER);
			} catch (AddUserException e) { 
				fail(e.toString() + " .Change dispName variable in setUp method or delete user with such name");
			}
			
			driver_sid = driver.logIn(drivName, "pass");
			dispatch_sid = dispatcher.logIn(dispName, "keyword");
			try {
				car = dispatcher.addCar(dispatch_sid, carNumber, "mercedes");
			} catch (AddCarException e) {
				fail(e.toString() + " change carNumber variable in setUp method or delete user with such name");
			}
			
		} catch (AuthorizationException e) {
			fail(e.toString());
		}
		
	}
	
	@Test
	public void createRepairRequest() {
		int repairsOldCount = 0;
		for (Request request: dispatcher.getRepairRequests(dispatch_sid)) {
			if (request.getDriver().equals(driver))
				repairsOldCount++;
		}

		Request repair = driver.createRepairRequest(driver_sid, car, "broken wheel");
		assertTrue(car == repair.getCar() && 
				driver == repair.getDriver() && 
				repair.getType() == RequestType.REPAIR &&
				repair.getCarState().equals("broken wheel"));
		
		int repairsNewCount = 0;
		for (Request request: dispatcher.getRepairRequests(dispatch_sid)) {
			if (request.getDriver().equals(driver))
				repairsNewCount++;
		}
		
		assertEquals("new size must be bigger by 1", repairsOldCount + 1, repairsNewCount);
	}
	
	@Test
	public void markRaceCompleted() {
		for(Request race: driver.getRaceRequests(driver_sid)) {
			driver.markRaceCompleted(driver_sid, race, "good");
		}
		for(Request race: driver.getRaceRequests(driver_sid)) {
			assertTrue(race.getStatus() == RequestStatus.COMPLETED);
		}
	}

	@AfterClass
	public static void tearDown() {
		if (dispatcher != null)
			admin.deleteUser(admin_sid, dispatcher);
		if (driver != null)
			admin.deleteUser(admin_sid, driver);
	}

}
