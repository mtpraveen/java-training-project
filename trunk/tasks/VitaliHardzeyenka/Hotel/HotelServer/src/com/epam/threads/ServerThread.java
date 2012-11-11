package com.epam.threads;

import java.net.Socket;
import java.util.concurrent.Callable;

import com.epam.training.logic.Logger;
import com.epam.training.logic.Server;
import com.epam.training.logic.ServerManager;

/**
 * This class used for creating new thread for every new 
 * connected client and manage all these threads.
 * @author EXUMLOKE
 *
 */
public class ServerThread implements Callable<Void> {
	
	private Socket clientSocket;
	private Server server = Server.getInstance();
	private ServerManager serverManager = new ServerManager();
	

	/**
	 * Constructor
	 * @param client socket of the client for which created this thread. 
	 */
	public ServerThread(Socket client) {
		this.clientSocket = client;
	}
	
	@Override
	public Void call() throws Exception {
		Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ServerThread.class));
		
		System.out.println(serverManager.receiveMessageFromClient(clientSocket, logger.getExeptionsLogger()));
		return null;
	}

	
}
