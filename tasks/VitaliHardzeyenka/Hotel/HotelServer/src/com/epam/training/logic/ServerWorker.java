package com.epam.training.logic;

import java.io.IOException;
import java.net.Socket;

import com.epam.training.data.DataStorage;
import com.epam.training.threads.ServerThread;

/**
 * Working with a Server class, waiting for the new clients, working with they,
 * describe sequence of task processing.
 * @author Gordeenko
 */
public class ServerWorker {
	private Server server = Server.getInstance(); // server object
	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ServerWorker.class.getName())); // logger	
	
	public void run() {
		
		DataStorage.loadData(System.getProperty("user.dir") + "\\data\\data.zip"); // load data from zip archive

		/*
		 * Cycle for every new client requests processing:
		 * accept new client by server;
		 * add new client in client list,
		 * create new thread for new client,
		 * process all requests received from client,
		 * get thread result.
		 */
		while (!server.getServerSocket().isClosed()) { // do while server will not be shutdown
			// Wait new client and connect with it.
			Socket clientSocket = null;
			try {
				clientSocket = server.getServerSocket().accept();
			} catch (IOException exception) {
				logger.getExeptionsLogger().error(exception);
			}
			
			System.out.println("Client connected: " + clientSocket.getInetAddress());
			
			if (clientSocket != null) { // if client was connected
				server.getClientSockets().add(clientSocket); // add new connected client to the list
				
				// Start work with new client.
				ServerThread serverThread = new ServerThread(server, clientSocket);
				serverThread.start();
			}
		}
	}	
}
