package com.epam.traning.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Roman
 * 
 */
public class Reader {

	String inputStr;
	public BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(System.in));

	public String reader(String returnString) throws IOException {

		System.out.println("");
		System.out.println("Enter knight cash:");
		inputStr = bufferedReader.readLine();
		return inputStr;
	}
}
