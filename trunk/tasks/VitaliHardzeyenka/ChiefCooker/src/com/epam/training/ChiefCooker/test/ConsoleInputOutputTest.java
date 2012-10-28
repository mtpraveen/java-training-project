package com.epam.training.ChiefCooker.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.epam.training.ChiefCooker.InputOutput.ConsoleInputOutput;

public class ConsoleInputOutputTest {
	
	@Test
	public void testReadString() {
		ConsoleInputOutput consoleInputOutput = ConsoleInputOutput.getInstanse();
		
		// test string input
		System.out.println("Type any string: ");
		Object inputString = consoleInputOutput.readString();
		assertFalse(inputString == null);
		assertFalse(((String) inputString).length() == 0);
		
		// test double input
		System.out.println("Type any double: ");
		inputString = consoleInputOutput.readDouble();
		assertFalse(inputString == null);
		assertFalse(((String) inputString).length() == 0);
		assertTrue(inputString instanceof Double);
		
		// test char input
		System.out.println("Type any char: ");
		inputString = consoleInputOutput.readChar();
		assertFalse(inputString == null);
		assertFalse(((String) inputString).length() == 0);
	}
}
