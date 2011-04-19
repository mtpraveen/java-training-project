package autobase.test;

import static junit.framework.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import autobase.exceptions.AddCarException;
import autobase.exceptions.AddUserException;
import autobase.exceptions.AuthorizationException;
import autobase.exceptions.PermissionsException;
import autobase.model.Admin;
import autobase.model.Car;
import autobase.model.Dispatcher;
import autobase.model.Driver;
import autobase.model.UserType;


public class TestPermissions {

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
	
	@BeforeClass
	public static void setUp() {
		final String dispName = "disp1234234";
		final String drivName = "driv53257234";
		
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
			
		} catch (AuthorizationException e) {
			fail(e.toString());
		}
		
	}
	
	@Test
	public void testPermissions() {
		Car car = null;
		try { //allowed operation
			car = dispatcher.addCar(dispatch_sid, "1234gsdfgsawr", "ziguli");
		} catch (PermissionsException e) { 
			fail("Permission exception mustn't occur");
		} catch (AddCarException e) {}
		
		try {//not allowed operation
			admin.getUsersList(driver_sid);
			fail("PermissionsException must occur");
		} catch (PermissionsException e) { }
		
		try {//not allowed operation
			driver.createRepairRequest(dispatch_sid, car, "problems with brakes");
			fail("PermissionsException must occur");
		} catch (PermissionsException e) { }
		
		try {//not allowed operation
			dispatcher.addCar(driver_sid, "ag234234jsdfl", "mazda");
			fail("PermissionsException must occur");
		} catch (PermissionsException e) { 
		} catch (AddCarException e) {}
		
		try {//allowed operation
			admin.getUsersList(admin_sid);
		} catch (PermissionsException e) { 
			fail("PermissionsException mustn't occur");
		}
		
		try {//allowed operation
			driver.createRepairRequest(driver_sid, car, "problems with brakes");
		} catch (PermissionsException e) { 
			fail("PermissionsException mustn't occur");
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
