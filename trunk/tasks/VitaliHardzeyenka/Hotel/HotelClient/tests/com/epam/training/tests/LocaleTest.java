package com.epam.training.tests;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class LocaleTest {

	@Test
	public void test() {
//		ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("user.dir") + "\\resources\\clientMessages");
		Locale locale = new Locale("ru_RU");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("com.epam.training.logic.resources.clientMessages", locale);
		System.out.println(resourceBundle.getString("greeting"));
		
	}

}
