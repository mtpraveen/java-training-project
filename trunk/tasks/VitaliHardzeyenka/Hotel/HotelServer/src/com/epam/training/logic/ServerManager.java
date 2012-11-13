package com.epam.training.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class describe all candidate actions of working server with client.
 * @author EXUMLOKE
 *
 */
public class ServerManager {	
	
	/**
	 * Accept client connection.
	 * @param serverSocket socket of server that wait for the connection.
	 * @param exceptionLogger
	 * @return socket of the client that has connection.
	 */
	public Socket acceptClientConnection(ServerSocket serverSocket, org.apache.log4j.Logger exceptionLogger) {
		try {
			return serverSocket.accept();
		} catch (IOException exception) {
			exceptionLogger.error(exception);
			return null;
		}
	}
	
	/**
	 * 
	 * @param client
	 * @param exceptionLogger
	 * @return
	 */
	public String receiveMessageFromClient(Socket client, org.apache.log4j.Logger exceptionLogger) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			return bufferedReader.readLine();
		} catch (IOException exception) {
			exceptionLogger.error(exception);
			return null;
		}
	}
	
	/**
	 * 
	 * @param client
	 * @param message
	 * @param exceptionLogger
	 */
	public void sendMessageToClient(Socket client, String message, org.apache.log4j.Logger exceptionLogger) {
		try {
			PrintStream printStream = new PrintStream(client.getOutputStream());
			printStream.print(message);
		} catch (IOException exception) {
			exceptionLogger.error(exception);
		}
	}
	
	public void disconnectClient(Socket clientSocket) {
		
	}
	
	
}
