package com.epam.webtests;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickButton;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.selectOptionByValue;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.setWorkingForm;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RegistrationTest {
	
	@Before
	public void prepare() {
        setBaseUrl("http://localhost:8080/JobTrackingSystem");
    }

	@Ignore
	@Test
	public void testLocalization() {
		beginAt("registration.jsp");
		clickLink("ruLocale"); // switch on ru locale
    	assertTitleEquals("Регистрация");
    	
    	beginAt("registration.jsp");
		clickLink("enLocale"); // switch on en locale
    	assertTitleEquals("Registration");
	}
	
	@Ignore
	@Test
	public void testRegistration() {
		beginAt("registration.jsp");
		clickLink("enLocale"); // switch on en locale
		assertTitleEquals("Registration");
		
		setWorkingForm("registrationForm");
		
		setTextField("login", "user32");
		setTextField("name", "newname");
		setTextField("surname", "newsurname");
		setTextField("password", "12341234");
		setTextField("repeatPassword", "12341234");
		setTextField("email", "email@gmail.com");
		selectOptionByValue("position", "manager");
		
		clickButton("submit");
		assertTitleEquals("Log on");
		
	}
	
	

}
