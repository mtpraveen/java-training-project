/**
 * 
 */
package autobase.test;


import static junit.framework.Assert.*;

import java.io.File;

import org.junit.AfterClass;
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
public class TestDispatcher {
	
	/* TODO Change username and password to the admin account values
	 *  if you have some problems with permissions while testing*/
	private static final String adminName = "root";
	private static final String adminPass = "root";
	private static Admin admin;
	private static int admin_sid;
	
	private static Driver driver;

	private static Dispatcher dispatcher;
	private static int dispatch_sid;
	
	private static Car car;
	
	@BeforeClass
	public static void setUp() {
		final String dispName = "disp1234234";
		final String drivName = "driv53257234";
		
		admin = new Admin();

		try {
			admin_sid = admin.logIn(adminName, adminPass);
			try {
				dispatcher = (Dispatcher)admin.addUser(admin_sid, dispName, "pass", UserType.DISPATCHER);
			} catch (AddUserException e) { 
				fail(e.toString() + " .Change dispName variable in setUp method or delete user with such name");
			}
			try {
				driver = (Driver)admin.addUser(admin_sid, drivName, "keyword", UserType.DRIVER);
			} catch (AddUserException e) {
				fail(e.toString() + " .Change drivName variable in setUp method or delete user with such name");
			}		
			
			dispatch_sid = dispatcher.logIn(dispName, "pass");
			
		} catch (AuthorizationException e) {
			fail(e.toString());
		}
	}
	
	
	@Test
	public void operationsWithCars() {
		final String carNumber = "14354525445";
		final String carBrand = "nissan ...";
		Car car1 = null;
		Car car2 = null;
		try { //test adding cars
			car = dispatcher.addCar(dispatch_sid, carNumber, carBrand);
			car1 = dispatcher.addCar(dispatch_sid, "car80979687", "audi");
			car2 = dispatcher.addCar(dispatch_sid, carNumber, carBrand);
			fail("AddCarException must occur");
		} catch (AddCarException e) { }
		finally {
			
			if (car1 != null) {
				try { //test deleting cars 
					dispatcher.getCarById(dispatch_sid, car1.getId());
				} catch (IndexOutOfBoundsException e) { 
					fail("IndexOutOfBoundsException mustn't occur");
				}
				
				dispatcher.deleteCar(dispatch_sid, car1);
				
				try {
					dispatcher.getCarById(dispatch_sid, car1.getId());
					fail("IndexOutOfBoundsException must occur");
				} catch (IndexOutOfBoundsException e) { }
			}
			if (car2 != null)
				fail("car2 mustn't be created: car and car2 can't have equal numbers");
		}
	}
	
	@Test
	public void createRaceRequest() {
		int racesOldCount = dispatcher.getRaceRequests(dispatch_sid).size();
		int availableDriversOldCount = dispatcher.getAvailableDrivers(dispatch_sid).size();
		int availableCarsOldCount = dispatcher.getAvailableCars(dispatch_sid).size();
		Request race = dispatcher.createRaceRequest(dispatch_sid, driver, car);
		assertTrue(car == race.getCar() && 
				driver == race.getDriver() && 
				race.getType() == RequestType.RACE);
		assertEquals("new size must be bigger by 1", racesOldCount + 1, dispatcher.getRaceRequests(dispatch_sid).size());
		assertEquals("1 car must be not available now", availableDriversOldCount - 1, dispatcher.getAvailableDrivers(dispatch_sid).size());
		assertEquals("1 driver must be not available now", availableCarsOldCount - 1, dispatcher.getAvailableCars(dispatch_sid).size());
	}
	
	@Test
	public void setRequestStatus() {
		for(Request request: dispatcher.getRepairRequests(dispatch_sid)) {
			dispatcher.setRequestStatus(dispatch_sid, request, RequestStatus.COMPLETED);
		}
		for(Request request: dispatcher.getRepairRequests(dispatch_sid)) {
			assertTrue(request.getStatus() == RequestStatus.COMPLETED);
			dispatcher.setRequestStatus(dispatch_sid, request, RequestStatus.ACTIVE);
		}
		for(Request request: dispatcher.getRepairRequests(dispatch_sid)) {
			assertTrue(request.getStatus() == RequestStatus.ACTIVE);
		}
	}
	
	@Test
	public void setDriverSuspended() {
		dispatcher.setDriverSuspended(dispatch_sid, driver, true);
		assertTrue(driver.isSuspended());
		dispatcher.setDriverSuspended(dispatch_sid, driver, false);
		assertFalse(driver.isSuspended());
	}
	
	@Test
	public void createReport() {
		File report = new File(dispatch_sid + ".csv");
		if (report.exists())
			report.delete();
		dispatcher.createReport(dispatch_sid, report.getName(), RequestStatus.ACTIVE);
		assertTrue(report.exists());
		report.delete();
	}
	
	@AfterClass
	public static void tearDown() {
		if (dispatcher != null)
			admin.deleteUser(admin_sid, dispatcher);
		if (driver != null)
			admin.deleteUser(admin_sid, driver);
	}
	
}
