package com.epam.training.logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Singleton.
 * This class describe server parameters.
 * @author EXUMLOKE
 *
 */
public class Server {
	
	private static Server INSTANCE = null; // exemplar of class
	private ServerSocket serverSocket; // socket of server
	private ArrayList<Socket> clientSockets = new ArrayList<>(); // list of connected clients
	
	/**
	 * Constructor.
	 * @param port port of server.
	 */
	private Server() {
		try {
			// Set default server socket as 1234. 
			setServerSocket(new ServerSocket(1234));
		} catch (IOException e) {
			// logger
			e.printStackTrace();
		}
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public ArrayList<Socket> getClientSockets() {
		return clientSockets;
	}

	public void setClientSockets(ArrayList<Socket> clientSockets) {
		this.clientSockets = clientSockets;
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
