package voting.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;

public class AdminTest {
	
	//admin account's data
	private final static String userName = "admin";
	private final static String password = "admin";
	private final static String email = "admin@voting.ru";
	
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
		assertTrue(selenium.isTextPresent("Admin menu"));
	}

	@Test
	public void testCategory() {

		// test working with categories
		selenium.click("link=Create new Category");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Create new Category"));
		selenium.type("_name_id", "selenium test category");
		selenium.type("_text_id", "simple text");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		assertTrue("maybe category with such name existed before?",
				selenium.isTextPresent("simple text"));
	}

	@Test
	public void testWrongCategory() {
		// try to create category with duplicate name
		selenium.click("link=Create new Category");
		selenium.waitForPageToLoad("30000");
		selenium.type("_name_id", "selenium test category");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Category with such name already exists"));
	}

	@Test
	public void testCreateQuestion() {
		// test working with questions
		selenium.click("link=Create new Question");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Create new Question"));
		selenium.type("_category_id", "selenium test category");
		selenium.typeKeys("_category_id", "selenium test category");
		selenium.type("_category_id", "selenium test category");
		selenium.type("_name_id", "selenium test question");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
	}

	@Test
	public void testCreateAndDeleteAnswer() {
		// create answers
		assertTrue(selenium.isTextPresent("Create new Answer"));
		selenium.type("_text_id", "first answer");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		selenium.type("_text_id", "second answer");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("second answer"));
		// delete second answer
		selenium.click("//tr[td[text()='second answer']]/td[2]/form/input[2]");
		assertTrue(selenium.getConfirmation().matches(
				"^Are you sure want to delete this item[\\s\\S]$"));
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isTextPresent("second answer"));
	}

	@Test
	public void testAccountValidation() {
		// test account validation messages
		selenium.click("link=Create new Account");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Create new Account"));
		// Input wrong data
		selenium.type("_name_id", userName);
		selenium.type("_password_id", "password1");
		selenium.type("_confirmPassword_id", "password2");
		selenium.type("_email_id", email);
		selenium.type("_captchaText_id", "captcha");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("User with such name already exists"));
		assertTrue(selenium.isTextPresent("Passwords should be equal"));
		assertTrue(selenium
				.isTextPresent("User with such email already exists"));
		// Correction of the wrong data
		selenium.type("_name_id", "selenium_user");
		selenium.type("_password_id", "password");
		selenium.type("_confirmPassword_id", "password");
		selenium.type("_email_id", "selenium_user@vote.ru");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium
				.isTextPresent("User with such name already exists"));
		assertFalse(selenium.isTextPresent("Passwords should be equal"));
		assertFalse(selenium
				.isTextPresent("User with such email already exists"));
	}

	@Test
	public void testCategoryContent() {
		// watch category content
		selenium.click("link=selenium test category (1)");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("selenium test question"));
		// andmin can see both vote and results links
		assertTrue(selenium.isElementPresent("link=vote"));
		assertTrue(selenium.isElementPresent("link=results"));
	}

	@AfterClass
	public static void tearDown() {
		selenium.stop();
	}
}
