package com.epam.training.logic;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Singleton.
 * @author EXUMLOKE
 *
 */
public class Server {
	
	private static Server INSTANCE = null; // exemplar of class
	private ServerSocket serverSocket; // socket of server
	
	/**
	 * Constructor.
	 * @param port port of server.
	 */
	private Server() {
		try {
			setServerSocket(new ServerSocket(1234));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/**
	 * Get exemplar of this class.
	 * @param port port of current server. 
	 * @return return instance to this class.
	 */
	public static Server getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Server();
		}
		
		return INSTANCE;
	}
}
