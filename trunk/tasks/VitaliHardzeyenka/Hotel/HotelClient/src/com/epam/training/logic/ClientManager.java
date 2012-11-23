package com.epam.training.logic;

import java.io.IOException;
import java.net.Socket;

/**
 * This class describe all candidate actions by client part.
 * @author EXUMLOKE
 */
public class ClientManager {

	private ServerConnection connection;
	
	public ClientManager(ServerConnection connection) {
		this.connection = connection;
	}
	
	/**
	 * Close connection with server.
	 * @param socket
	 * @param logger
	 */
	public void closeClient(Socket socket, Logger logger) {
		try {
			socket.close();
		} catch(IOException exception) {
			logger.getExeptionsLogger().error(exception);
		}
	}
	
	/**
	 * Send message to the server.
	 * @param message message that will be sent.
	 * @param messageType type of sending message.
	 * @param exeptionsLogger text file logger for errors.
	 */
	public void sendMessageToServer(String message, MessageTypes messageType, org.apache.log4j.Logger exeptionsLogger) {
		System.out.println(String.format("%s;%s", message, messageType.toString().toUpperCase()));
		this.connection.getPrintStream().println(String.format("%s;%s", message, messageType.toString().toUpperCase())); // send message
	}
	
	/**
	 * Receive message from server input stream.
	 * @param serverSocket socket of server for receiving message.
	 * @param exeptionsLogger text file logger for errors.
	 * @return message from server.
	 */
	public String receiveMessageFromServer(org.apache.log4j.Logger exeptionsLogger) {
		try {
			return this.connection.getBufferedReader().readLine();
		} catch (IOException exception) {
			exeptionsLogger.error(exception);
			exception.printStackTrace();
			return "error with receiving";
		}
	}
	
	//
	// Actions that can do administrator and client.
	//
	
	/**
	 * Send message for creating new account. Need data are login and password.
	 * @param login login of new account
	 * @param password password of new account
	 * @param exeptionsLogger text file logger for errors.
	 */
	public void createAccout(Socket serverSocket, String login, String password, org.apache.log4j.Logger exeptionsLogger) {
		String message = String.format("%s,%s", login, password);		
		sendMessageToServer(message, MessageTypes.CREATE_ACCOUNT, exeptionsLogger);
	}
	
	/**
	 * Send message for logging on. Need data are login and password.
	 * @param login login of existing account.
	 * @param password password of existing account.
	 * @param exeptionsLogger text file logger for errors.
	 */
	public void logOn(Socket serverSocket, String login, String password, org.apache.log4j.Logger exeptionsLogger) {
		String message = String.format("%s,%s", login, password);		
		sendMessageToServer(message, MessageTypes.LOG_ON, exeptionsLogger);		
	}
	
	/**
	 * Change password of current user.
	 * @param newPassword new password of current user.
	 * @param exeptionsLogger text file logger for errors.
	 */
	public void changePassword(Socket serverSocket, String newPassword, org.apache.log4j.Logger exeptionsLogger) {
		sendMessageToServer(newPassword, MessageTypes.CHANGE_PASSWORD, exeptionsLogger);
	}
}
