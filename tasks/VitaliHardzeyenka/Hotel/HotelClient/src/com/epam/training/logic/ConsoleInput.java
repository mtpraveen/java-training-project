package com.epam.training.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author EXUMLOKE
 *
 */
public class ConsoleInput {
	private BufferedReader bufferedReader;

    public ConsoleInput() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Return string that was typed from the console.
     * @return
     */
    public String readString() {
        try {
            return bufferedReader.readLine();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return ""; // return null;
        }
    }
}
