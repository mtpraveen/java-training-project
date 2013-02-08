package com.epam.webtests;

//import static org.junit.Assert.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class LogOnTest {
	
	@Before
	public void prepare() {
        setBaseUrl("http://localhost:8080/JobTrackingSystem");
    }

	@Ignore
	@Test
    public void testRegistrationLink() {
    	// Switch ru locale
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("ruLocale"); // switch on ru locale
    	assertTitleEquals("Вход в систему");
    	clickLink("registration");
        assertTitleEquals("Регистрация");
    	
        // Test en_US locale
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on en locale
    	assertTitleEquals("Log on");    	
        clickLink("registration");
        assertTitleEquals("Registration");
    }
    
	@Ignore
    @Test
    public void testLogginOn() {
    	
    	// Test ru_Ru locale
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("ruLocale"); // switch on ru locale
    	assertTitleEquals("Вход в систему");
    	
    	// Try to submit without typing login and password
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	submit(); 
    	assertTitleEquals("Ошибка");
    	
    	// Try to type correct login, incorrect password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	setTextField("login", "user6");
    	setTextField("password", "incorrect password");
    	submit(); 
    	assertTitleEquals("Ошибка");
    	
    	// Try to type incorrect login, correct password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	setTextField("login", "incorrect login");
    	setTextField("password", "12341234");
    	submit(); 
    	assertTitleEquals("Ошибка");
    	
    	// Try to type incorrect login and password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	setTextField("login", "incorrect login");
    	setTextField("password", "incorrect password");
    	submit(); 
    	assertTitleEquals("Ошибка");
    	
    	// try to type correct login and password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	setTextField("login", "user6");
    	setTextField("password", "12341234");
    	submit(); 
    	assertTitleEquals("Домашная страница менеджера");
    	
    	// Test en_US locale
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on ru locale
    	assertTitleEquals("Log on");
    	
    	// Try to submit without typing login and password
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on ru locale
    	submit(); 
    	assertTitleEquals("Error");
    	
    	// Try to type correct login, incorrect password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on ru locale
    	setTextField("login", "user6");
    	setTextField("password", "incorrect password");
    	submit(); 
    	assertTitleEquals("Error");
    	
    	// Try to type incorrect login, correct password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on ru locale
    	setTextField("login", "incorrect login");
    	setTextField("password", "12341234");
    	submit();
    	assertTitleEquals("Error");
    	
    	// Try to type incorrect login and password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on ru locale
    	setTextField("login", "incorrect login");
    	setTextField("password", "incorrect password");
    	submit(); 
    	assertTitleEquals("Error");
    	
    	// try to type correct login and password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on ru locale
    	setTextField("login", "user6");
    	setTextField("password", "12341234");
    	submit(); 
    	assertTitleEquals("Manager home page");
    }

}
