package voting.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;

public class TearDown {
	
	//admin account's data
	private final static String userName = "admin";
	private final static String password = "admin";
	
	private static DefaultSelenium selenium;
	
	@BeforeClass
	public static void setUp() {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/voting/");
		selenium.start();
		// log out and set language to english
		selenium.open("login?lang=en");
		// log in
		assertTrue(selenium.isTextPresent("You have tried to access a protected area of this application."));
		selenium.type("j_username", userName);
		selenium.type("j_password", password);
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
	}

	@Test
	public void testDeleteQuestion() {
		selenium.open("questions");
		selenium.waitForPageToLoad("10000");
		assertTrue(selenium.isTextPresent("selenium test question"));
		selenium.click("//tr[td[text()='selenium test question']]/td[6]/form/input[2]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.getConfirmation().matches(
				"^Are you sure want to delete this item[\\s\\S]$"));
		assertFalse(selenium.isTextPresent("selenium test question"));
	}

	@AfterClass
	public static void tearDown() {
		// delete created category
		selenium.open("categories");
		selenium.waitForPageToLoad("10000");
		assertTrue(selenium.isTextPresent("selenium test category"));
		selenium.click("//tr[td[text()='selenium test category']]/td[4]/form/input[2]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.getConfirmation().matches(
				"^Are you sure want to delete this item[\\s\\S]$"));
		assertFalse(selenium.isTextPresent("selenium test category"));
		selenium.stop();
	}
}
