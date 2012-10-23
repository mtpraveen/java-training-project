package com.epam.training.ChiefCooker.InputOutput;

import java.io.*;

/**
 * @author EXUMLOKE
 * Singleton.
 * This class read input information from console.
 */
public class ConsoleInputOutput {
	
	private static ConsoleInputOutput INSTANCE = null;
    private BufferedReader bufferedReader;

    private ConsoleInputOutput() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static ConsoleInputOutput getInstanse() {
		if (INSTANCE == null) {
			INSTANCE = new ConsoleInputOutput();
		}

		return INSTANCE; // return exemplar of classS
	}

    /**
     * Return string that was typed from the console.
     * @return
     */
    public String readString() {
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Character readChar() {
        try {
            return bufferedReader.readLine().charAt(0);
    	} catch (IOException exception) {
    		return null;
    	}
    }

    public double readDouble() {
    	try {
            return Double.parseDouble(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        	return 0;
        }
    }
}
