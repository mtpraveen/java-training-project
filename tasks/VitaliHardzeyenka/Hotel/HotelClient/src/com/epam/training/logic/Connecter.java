package com.epam.training.logic;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connecter {

	private ClientManager clientManager = new ClientManager();
	private Logger logger = new Logger(org.apache.log4j.Logger.getLogger(Runner.class.getName()));
	private String serverIp = "127.0.0.1";
	private int serverPort = 1234;
	private Socket serverSocket;

	public void run() {
		try {
			InetAddress address = InetAddress.getByName(serverIp); // get server address
			serverSocket = clientManager.setServerSonnection(address, serverPort, logger.getExeptionsLogger()); // try to connect to server

			// Give control to user.
			ConsoleManager consoleManager = new ConsoleManager(serverSocket);
			consoleManager.run();
		} catch (UnknownHostException exception) {
			logger.getExeptionsLogger().error(exception);
		}
	}
}