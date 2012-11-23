package com.epam.training.logic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connecter {

	private int serverPort = 1234;
	private Socket serverSocket;

	public void run() {
		try {
			serverSocket = new Socket(InetAddress.getLocalHost(), serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ServerConnection connection = new ServerConnection(serverSocket);
		
		// Give control to user.
		ConsoleManager consoleManager = new ConsoleManager(connection);
		consoleManager.runSession();
	}
}