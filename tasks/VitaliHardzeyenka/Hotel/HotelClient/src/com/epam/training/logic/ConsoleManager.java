package com.epam.training.logic;

import java.net.Socket;
import java.util.ResourceBundle;

/**
 * @author EXUMLOKE
 * This class used for giving control to user throw
 * console. Its read commands from the console and 
 * output messages that has been received from server.
 */
public class ConsoleManager {
	
	private ClientManager clientManager = new ClientManager();
	private Socket socket; // server connection
	private final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("user.dir") + "\\resources\\clientMessages");
	
	/**
	 * Constructor
	 * @param socket
	 */
	public ConsoleManager(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// Cycle for client manage from console while client is open. 
		while (true) {
			
		}
	}
}
