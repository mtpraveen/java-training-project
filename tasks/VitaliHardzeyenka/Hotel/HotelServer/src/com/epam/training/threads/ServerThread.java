package com.epam.training.threads;

import java.net.Socket;
import java.util.concurrent.Callable;

import com.epam.training.data.CsvDataReader;
import com.epam.training.data.CsvDataWriter;
import com.epam.training.data.DataAnalyser;
import com.epam.training.data.DataStorage;
import com.epam.training.logic.Logger;
import com.epam.training.logic.MessageTypes;
import com.epam.training.logic.Server;
import com.epam.training.logic.ServerManager;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserStatus;

/**
 * This class used for creating new thread for every new 
 * connected client and manage all these threads.
 * @author EXUMLOKE
 *
 */
public class ServerThread implements Callable<Void> {
	private Server server; // server exemplar
	private Socket clientSocket; // client exemplar
	CsvDataReader csvDataReader;
	CsvDataWriter csvDataWriter;
	ServerManager serverManager = new ServerManager();
	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ServerThread.class)); // logger
	
	/**
	 * Constructor.
	 * @param serverSocket
	 * @param clientSocket
	 */
	public ServerThread(Server server, Socket clientSocket) {
		this.server = server;
		this.clientSocket = clientSocket;
	}


	@Override
	public Void call() throws Exception {
		MessageTypes messageType = null;
		String messageFromClient = null;
		
		/*
		 * Cycle for every new client requests processing:
		 * add new client to data base;
		 * blocked client;
		 * read info from data base;
		 * add info to data base;
		 * 
		 */		
		do {
			// receive message from client
			messageFromClient = serverManager.receiveMessageFromClient(clientSocket, logger.getExeptionsLogger());
			messageType = getMessageTypeFromClientMessage(messageFromClient); // get type of message
			messageFromClient = getMessagePartFromClientMessage(messageFromClient); // get message part without MessageType
			
			
			switch(String.valueOf(messageType)) {
				case "MESSAGE" :  // simple message, only text string
					// TODO when receive simple message from client, server send it message back.
					messageAction(serverManager, clientSocket, messageFromClient, logger.getExeptionsLogger());
					break;
				case "LOG_ON" : 
					// TODO server must search login and password that has been received
					// in data base and if its true send acception.
					logOnAction(clientSocket, messageFromClient, logger.getExeptionsLogger());
					break;
				case "CREATE_ACCOUNT" :
					// TODO if new login is exist in data base, sent to client message
					// about it, if not - save information in data base.
					createAccountAction(clientSocket, messageFromClient, logger.getExeptionsLogger());
					break;
				case "CHANGE_PASSWORD" :
					changePasswordAction(clientSocket, messageFromClient, logger.getExeptionsLogger());
					break;
				case "DISCONNECT_CLIENT" :
					disconnectClientAction(clientSocket);
					break;
				case "DELETE_ACCOUNT" :
					deleteAccountAction(clientSocket, messageFromClient, logger.getExeptionsLogger());
					break;
				case "STOP_SERVER" :
					break;
				default:		
					break;
			}
			
		} while(messageType != MessageTypes.DISCONNECT_CLIENT);
		
		
		return null;
	}
	
	private MessageTypes getMessageTypeFromClientMessage(String message) {
		return MessageTypes.valueOf(DataAnalyser.split(message, ";").get(1).toUpperCase());
	}
	
	private String getMessagePartFromClientMessage(String message) {
		return DataAnalyser.split(message, ";").get(0).toString();
	}
	
	private void messageAction(ServerManager serverManager, Socket clientSocket, String message, org.apache.log4j.Logger exceptionLogger) {
		serverManager.sendMessageToClient(clientSocket, message, exceptionLogger);
	}
	
	/**
	 * 
	 * @param clientSocket
	 * @param message
	 * @param exceptionLogger
	 */
	private void logOnAction(Socket clientSocket, String message, org.apache.log4j.Logger exceptionLogger) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedPassword = message.split(",")[1]; // pass
		boolean isFound = false;
		
		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin) && (user.getPassword().equals(receivedPassword))) {
				isFound = true;
				break; // exit from cycle
			}
		}
		
		// Send message to client
		if (isFound) { // if login and password has been found in data base
			serverManager.sendMessageToClient(clientSocket, "true", exceptionLogger);
		} else { // if login hasnt been found in data base or password is incorrect
			serverManager.sendMessageToClient(clientSocket, "false", exceptionLogger);
		}		
	}
	
	/**
	 * 
	 * @param clientSocket
	 * @param message
	 * @param exceptionLogger
	 */
	private void createAccountAction(Socket clientSocket, String message, org.apache.log4j.Logger exceptionLogger) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedPassword = message.split(",")[1]; // pass
		
		boolean isFound = false;
		
		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin)) {
				isFound = true;
				break; // exit from cycle
			}
		}
		
		if (isFound) { // if login exist in data base yet
			serverManager.sendMessageToClient(clientSocket, "Login that u typed used by another user. Please try again.", exceptionLogger);
		} else { // if all ok and it can create new account
			DataStorage.USERS_LIST.add(new User(receivedLogin, receivedPassword, UserStatus.CLIENT));
			serverManager.sendMessageToClient(clientSocket, "Thats good, account has been created. Please log on.", exceptionLogger);
		}
	}
	
	/**
	 * 
	 * @param clientSocket
	 * @param message
	 * @param exceptionLogger
	 */
	private void changePasswordAction(Socket clientSocket, String message, org.apache.log4j.Logger exceptionLogger) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedOldPassword = message.split(",")[1]; // old password
		String receivedNewPassword = message.split(",")[2]; // new password
		
		boolean isFound = false;
		UserStatus userStatus = null;

		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin) && (user.getPassword().equals(receivedOldPassword))) {
				isFound = true;
				userStatus = user.getStatus();
				DataStorage.USERS_LIST.remove(user); // delete current user
				break; // exit from cycle
			}
		}
		
		if (isFound) { // create new user with new password and add it in the end of user list
			DataStorage.USERS_LIST.add(new User(receivedLogin, receivedNewPassword, userStatus));
			serverManager.sendMessageToClient(clientSocket, "Password was changed.", exceptionLogger);
		} else {
			serverManager.sendMessageToClient(clientSocket, "Login or password that u typed incorrect. Please try again.", exceptionLogger);
		}
	}
	
	/**
	 * 
	 * @param clientSocket
	 */
	private void disconnectClientAction(Socket clientSocket) {
		serverManager.disconnectClient(clientSocket);
	}
	
	/**
	 * 
	 * @param clientSocket
	 * @param message
	 * @param exceptionLogger
	 */
	private void deleteAccountAction(Socket clientSocket, String message, org.apache.log4j.Logger exceptionLogger) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedPassword = message.split(",")[1]; // pass
		
		boolean isFound = false;
		
		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin)) {
				isFound = true;
				DataStorage.USERS_LIST.remove(user);
				serverManager.sendMessageToClient(clientSocket, "Account has been deleted.", exceptionLogger);
				break; // exit from cycle
			}
		}		
	}
	
	/**
	 * 
	 */
	private void stopSeverAction() {
		// TODO server can stop administrator only.
	}
	
}
