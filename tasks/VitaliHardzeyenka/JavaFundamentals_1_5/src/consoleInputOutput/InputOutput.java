package consoleInputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Singleton.
 * Input different values from keyboard.
 * @author Gordeenko
 *
 */
public class InputOutput {
	private static InputOutput instanse = null; // instanse of object 
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	
	// Constructor
	private InputOutput() {
		this.inputStreamReader = new InputStreamReader(System.in);
		this.bufferedReader = new BufferedReader(inputStreamReader);
	}
	
	private BufferedReader getBufferedReader() {
		return this.bufferedReader;
	}
	
	// Get or create the exemplare of class.
	public static InputOutput getInstanse() {
		if (instanse ==  null) {
			instanse = new InputOutput();
		} 
			
		return instanse;
	}
	
	// Read integer from keyboard.
	public int readInt() {
		try {			
			return Integer.parseInt(getBufferedReader().readLine());
		} catch (IOException e) {
			System.out.println("Parameter that was typed not an int!" + e);
			return 0;
		}
	}
	
	// Read string from keyboard.
	public String readString() {
		try {
			return getBufferedReader().readLine();
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
	}
}
