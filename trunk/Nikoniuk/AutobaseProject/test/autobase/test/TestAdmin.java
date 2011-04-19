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

import autobase.exceptions.AddUserException;
import autobase.exceptions.AuthorizationException;
import autobase.model.Admin;
import autobase.model.Car;
import autobase.model.Dispatcher;
import autobase.model.Driver;
import autobase.model.UserType;

/**
 * @author Alexander Nikoniuk
 * 
 */
public class TestAdmin {

	/*
	 * TODO Change username and password to the admin account values if you have
	 * some problems with permissions while testing
	 */
	private static final String adminName = "root";
	private static final String adminPass = "root";
	private static Admin admin;
	private static int admin_sid;

	private static Driver driver;
	private static Dispatcher dispatcher;

	@BeforeClass
	public static void setUp() {
		admin = new Admin();
		try {
			admin_sid = admin.logIn(adminName, adminPass);
		} catch (AuthorizationException e) {
			fail(e.toString());
		}
	}

	@Test
	public void addUser() {
		final String dispName = "disp1234234";
		final String drivName = "driv53257234";
		final String adminName = "driv53257234";

		try {
			dispatcher = (Dispatcher) admin.addUser(admin_sid, dispName,
					"pass", UserType.DISPATCHER);
		} catch (AddUserException e) {
			fail(e.toString()
					+ " .Change dispName variable in setUp method or delete user with such name");
		}
		try {
			driver = (Driver) admin.addUser(admin_sid, drivName, "keyword",
					UserType.DRIVER);
		} catch (AddUserException e) {
			fail(e.toString()
					+ " .Change drivName variable in setUp method or delete user with such name");
		}
	}

	@Test
	public void deleteUser() {
		int usersOldCount = admin.getUsersList(admin_sid).size();
		admin.deleteUser(admin_sid, dispatcher);
		admin.deleteUser(admin_sid, driver);
		try {
			admin.deleteUser(admin_sid, admin);
			fail("admin can't be deleted");
		} catch (IllegalArgumentException e) { }
		assertEquals("new size must be less by 2", usersOldCount - 2, admin.getUsersList(admin_sid).size());
	}

	@Test
	public void setUserPassword() {
		addUser();
		admin.setUserPassword(admin_sid, driver, "newPass");
		try {
			driver.logIn(driver.getUsername(), "newPass");
		} catch (AuthorizationException e) {
			fail(e.toString());
		}
	}

	@Test
	public void setUserSuspended() {
		admin.setUserSuspended(admin_sid, dispatcher, true);
		assertTrue(dispatcher.isSuspended());
		admin.setUserSuspended(admin_sid, dispatcher, false);
		assertFalse(dispatcher.isSuspended());
	}
	
	@AfterClass
	public static void tearDown() {
		if (dispatcher != null)
			admin.deleteUser(admin_sid, dispatcher);
		if (driver != null)
			admin.deleteUser(admin_sid, driver);
	}


}
