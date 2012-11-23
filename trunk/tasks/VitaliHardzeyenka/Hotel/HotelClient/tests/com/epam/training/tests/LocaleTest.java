package com.epam.training.tests;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class LocaleTest {

	@Test
	public void test() {
//		Locale locale = new Locale("en_US");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("com.epam.training.logic.resources.clientMessages");
		System.out.println(resourceBundle.getString("you.connect"));
		
	}

}
