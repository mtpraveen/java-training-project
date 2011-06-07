package voting.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;

public class GuestTest {

	private static DefaultSelenium selenium;

	@BeforeClass
	public static void prepare() {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox3",
				"http://localhost:8080/voting/");
		selenium.start();
		// log out and change language to english
		selenium.open("resources/j_spring_security_logout");
		selenium.open("?lang=en");
	}

	@Test
	public void testCategories() {

		selenium.open("categories");
		assertTrue(selenium
				.isTextPresent("You have tried to access a protected area of this application."));
	}

	@Test
	public void testQuestions() {
		selenium.open("questions");
		assertTrue(selenium
				.isTextPresent("You have tried to access a protected area of this application."));
	}

	@Test
	public void testAccounts() {
		selenium.open("accounts");
		assertTrue(selenium
				.isTextPresent("You have tried to access a protected area of this application."));
	}

	@Test
	public void testCategoryContent() {
		// watch category content
		selenium.open("");
		selenium.click("link=selenium test category (1)");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("selenium test question"));
		assertFalse(selenium.isElementPresent("link=vote"));
		assertFalse(selenium.isElementPresent("link=results"));
	}

	@AfterClass
	public static void tearDown() {
		selenium.stop();
	}
}
