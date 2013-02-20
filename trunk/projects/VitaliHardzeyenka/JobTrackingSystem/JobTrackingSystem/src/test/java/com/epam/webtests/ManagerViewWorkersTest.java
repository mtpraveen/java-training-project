package com.epam.webtests;

import static org.junit.Assert.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;


public class ManagerViewWorkersTest {

	@Ignore
	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8080/JobTrackingSystem");
	}
	
	@Ignore
	@Test
	public void test() {
		// try to type correct login and password.
    	beginAt("logOn.jsp"); // start on logOn.jsp
    	clickLink("enLocale"); // switch on ru locale
    	setTextField("login", "user6");
    	setTextField("password", "12341234");
    	submit(); 
    	assertTitleEquals("Manager home page");
    	
    	clickLink("showWorkers");
    	assertTitleEquals("Workers list");
    	
	}

}
