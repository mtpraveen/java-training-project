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
	
	public Socket acceptClientConnection(ServerSocket serverSocket, org.apache.log4j.Logger exceptionLogger) {
		try {
			return serverSocket.accept();
		} catch (IOException exception) {
			exceptionLogger.error(exception);
			return null;
		}
	}
	
	public String receiveMessageFromClient(Socket client, org.apache.log4j.Logger exceptionLogger) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			return bufferedReader.readLine();
		} catch (IOException exception) {
			exceptionLogger.error(exception);
			return null;
		}
	}
	
	public void sendMessageToClient(Socket client, String message, org.apache.log4j.Logger exceptionLogger) {
		try {
			PrintStream printStream = new PrintStream(client.getOutputStream());
			printStream.print(message);
		} catch (IOException exception) {
			exceptionLogger.error(exception);
		}		
	}
	
	
}
