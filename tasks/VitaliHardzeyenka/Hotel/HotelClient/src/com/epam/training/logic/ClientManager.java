package com.epam.training.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class describe all candidate actions by client part.
 * @author EXUMLOKE
 */
public class ClientManager {

	/**
	 * Set connection with server.
	 * @param inetAddress Internet address of server. Use method getLocalHost() 
	 * 		  			  if server is on the local machine.
	 * @param port port of server.
	 * @param exeptionsLogger text file logger for errors. 
	 * @return true if connection has been established, false in another case.
	 */
	public Socket setServerSonnection(InetAddress inetAddress, int port, org.apache.log4j.Logger exeptionsLogger) {
		try {
			return new Socket(inetAddress, port);
		} catch (UnknownHostException exception) {
			exeptionsLogger.error(exception);
			return null;
		} catch (IOException exception) {
			exeptionsLogger.error(exception);
			return null;
		}
	}
	
	/**
	 * Send message to the server.
	 * @param message message that will be sent.
	 * @param messageType type of sending message.
	 * @param exeptionsLogger text file logger for errors.
	 */
	public void sendMessageToServer(Socket serverSocket, String message, MessageTypes messageType, org.apache.log4j.Logger exeptionsLogger) {
		try {
			PrintStream printStream = new PrintStream(serverSocket.getOutputStream());
			printStream.print(String.format("%s;%s", message, messageType.toString().toUpperCase())); // send message
			printStream.flush();
			
			printStream.close();
		} catch (IOException exception) {
			exeptionsLogger.error(exception);
		}
	}
	
	/**
	 * Receive message from server input stream.
	 * @param serverSocket socket of server for receiving message.
	 * @param exeptionsLogger text file logger for errors.
	 * @return message from server.
	 */
	public String receiveMessageFromServer(Socket serverSocket, org.apache.log4j.Logger exeptionsLogger) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			return bufferedReader.readLine();
		} catch (IOException exception) {
			exeptionsLogger.error(exception);
			return "";
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
		sendMessageToServer(serverSocket, message, MessageTypes.CREATE_ACCOUNT, exeptionsLogger);
	}
	
	/**
	 * Send message for logging on. Need data are login and password.
	 * @param login login of existing account.
	 * @param password password of existing account.
	 * @param exeptionsLogger text file logger for errors.
	 */
	public void logOn(Socket serverSocket, String login, String password, org.apache.log4j.Logger exeptionsLogger) {
		String message = String.format("%s,%s", login, password);		
		sendMessageToServer(serverSocket, message, MessageTypes.LOG_ON, exeptionsLogger);		
	}
	
	/**
	 * 
	 */
	public void logOff() {
		
	}
	
	/**
	 * Change password of current user.
	 * @param newPassword new password of current user.
	 * @param exeptionsLogger text file logger for errors.
	 */
	public void changePassword(Socket serverSocket, String newPassword, org.apache.log4j.Logger exeptionsLogger) {
		sendMessageToServer(serverSocket, newPassword, MessageTypes.CHANGE_PASSWORD, exeptionsLogger);
	}
	
	//
	// Actions that cad do administrator only.
	//
	
	/**
	 * Deleting account by its number in data base. 
	 * @param number number of account in data base
	 */
	public void deleteAccount(int number) {
		
	}
	
	/**
	 * Deleting account by login.
	 * @param login login of the deleting account
	 */
	public void deleteAccount(String login) {
		
	}	
}
