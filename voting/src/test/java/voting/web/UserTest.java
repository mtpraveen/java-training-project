package voting.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;

public class UserTest {

	// user account's data
	private final static String userName = "user";
	private final static String password = "password";

	private static DefaultSelenium selenium;

	@BeforeClass
	public static void prepare() {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox3",
				"http://localhost:8080/voting/");
		selenium.start();
		// log out
		selenium.open("resources/j_spring_security_logout");
		// go to log in page and set language to english
		selenium.open("login?lang=en");
		// log in
		assertTrue(selenium
				.isTextPresent("You have tried to access a protected area of this application."));
		selenium.type("j_username", userName);
		selenium.type("j_password", password);
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent(String.format("Hello, %s", userName)));
	}

	@Test
	public void testCategories() {
		selenium.open("categories");
		assertTrue(selenium.isTextPresent("Access is denied"));
	}

	@Test
	public void testQuestions() {
		selenium.open("questions");
		assertTrue(selenium.isTextPresent("Access is denied"));
	}

	@Test
	public void testAccounts() {
		selenium.open("accounts");
		assertTrue(selenium.isTextPresent("Access is denied"));
	}

	@Test
	public void testCategoryContent() {
		// watch category content
		selenium.open("");
		selenium.click("link=selenium test category (1)");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("selenium test question"));
		// Before voting we can't see results
		assertTrue(selenium.isElementPresent("link=vote"));
		assertFalse(selenium.isElementPresent("link=results"));
		// After voting we can see results, but can't vote
		selenium.click("link=vote");
		selenium.waitForPageToLoad("30000");
		selenium.click("name=answers");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		// let's see it
		selenium.open("");
		selenium.click("link=selenium test category (1)");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("link=vote"));
		assertTrue(selenium.isElementPresent("link=results"));
	}

	@AfterClass
	public static void tearDown() {
		selenium.stop();
	}

}
