package com.epam.training.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.epam.training.data.DataAnalyser;
import com.epam.training.data.DataStorage;
import com.epam.training.logic.Logger;
import com.epam.training.logic.MessageTypes;
import com.epam.training.logic.Server;
import com.epam.training.model.application.Application;
import com.epam.training.model.room.ClassApartments;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserStatus;

/**
 * This class used for creating new thread for every new 
 * connected client and manage all these threads.
 * @author EXUMLOKE
 *
 */
public class ServerThread extends Thread {
	private enum ReportType {
		ALL_CLIENTS,
		CURRENT_CLIENTS,
		CURRENT_FREE_ROOMS,
		CURRENT_APPLICATONS
	}
	
	private Server server; // server exemplar
	private Socket clientSocket; // client exemplar
	private Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ServerThread.class)); // logger
	private BufferedReader bufferedReader;
	private PrintStream printStream;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("com.epam.training.threads.resources.serverMessages");
	
	/**
	 * Constructor.
	 * @param serverSocket
	 * @param clientSocket
	 */
	public ServerThread(Server server, Socket clientSocket) {
		this.server = server;
		this.clientSocket = clientSocket;
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			printStream = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
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
			messageFromClient = receiveMessageFromClient();
			messageType = getMessageTypeFromClientMessage(messageFromClient); // get type of message
			messageFromClient = getMessagePartFromClientMessage(messageFromClient); // get message part without MessageType
			System.out.println(messageFromClient + " " + messageType.toString());
			
			switch(String.valueOf(messageType)) {
				case "MESSAGE" :  // simple message, only text string
					messageAction(messageFromClient);
					break;
				case "LOG_ON" : 
					logOnAction(messageFromClient);
					break;
				case "CREATE_ACCOUNT" :
					createAccountAction(messageFromClient);
					break;
				case "CREATE_APPLICATION" :
					createApplicationAction(messageFromClient);
					break;
				case "GET_REPORT" :
					getReportAction(messageFromClient);
					break;
				case "CHANGE_PASSWORD" :
					changePasswordAction(messageFromClient);
					break;
				case "DISCONNECT_CLIENT" :
					disconnectClientAction(clientSocket);
					break;
				case "DELETE_ACCOUNT" :
					deleteAccountAction(messageFromClient);
					break;
				case "STOP_SERVER" :
					stopSeverAction(logger.getExeptionsLogger());
					messageType = MessageTypes.DISCONNECT_CLIENT;
					break;
				default:		
					break;
			}
			
		} while(messageType != MessageTypes.DISCONNECT_CLIENT);
		
	}
	
	/**
	 * Send message to client.
	 * @param message message will be send.
	 */
	private void sendMessageToClient(String message) {
		printStream.println(message);
	}
	
	/**
	 * Receive message from client.
	 * @return string message form client.
	 */
	private String receiveMessageFromClient() {
		try {
			return bufferedReader.readLine();
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * Return message type that will get from 
	 * string that received form client.
	 * @param message string from client.
	 * @return message type
	 */
	private MessageTypes getMessageTypeFromClientMessage(String message) {
		return MessageTypes.valueOf(DataAnalyser.split(message, ";").get(1).toUpperCase());
	}
	
	/**
	 * Return message without message type.
	 * @param message string from client.
	 * @return
	 */
	private String getMessagePartFromClientMessage(String message) {
		return DataAnalyser.split(message, ";").get(0).toString();
	}
	
	/**
	 * Send to client same message that has been received from it.
	 * @param message
	 */
	private void messageAction(String message) {
		sendMessageToClient(message);
	}
	
	/**
	 * Client logging on.
	 * Send to client result of logging on.
	 * @param message string with login, password.
	 */
	private void logOnAction(String message) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedPassword = message.split(",")[1]; // pass
		
		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin) && (user.getPassword().equals(receivedPassword))) {
				sendMessageToClient(user.getStatus().toString()); // if login and password has been found in data base
				return;
			}
		}
		
		sendMessageToClient("false");
	}
	
	/**
	 * If login is not busy create new account.
	 * @param message
	 */
	private void createAccountAction(String message) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedPassword = message.split(",")[1]; // pass
		
		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin)) {
				sendMessageToClient("used");
				return; // exit from cycle
			}
		}
		
		// if all OK and it can create new account
		DataStorage.USERS_LIST.add(new User(receivedLogin, receivedPassword, UserStatus.CLIENT));
		sendMessageToClient(UserStatus.CLIENT.toString());
	}
	
	/**
	 * Creating new application.
	 * @param message
	 */
	private void createApplicationAction(String message) {
		String[] messageParts = message.split(",");
		String numberSeats = messageParts[0];
		String classApartments = messageParts[1];
		String arrivalDate = messageParts[2];
		String evictionDate = messageParts[3];
		
		try {
			DataStorage.APPLICATIONS_LIST.add(new Application(Integer.valueOf(numberSeats), 
															  ClassApartments.valueOf(classApartments), 
															  (Date) (new SimpleDateFormat("dd.MM.yyyy")).parse(arrivalDate), 
															  (Date) (new SimpleDateFormat("dd.MM.yyyy")).parse(evictionDate)));
		} catch (NumberFormatException exception) {
			sendMessageToClient(resourceBundle.getString("error.application.creating"));
			logger.getExeptionsLogger().error(exception);
			return;
		} catch (ParseException exception) {
			sendMessageToClient(resourceBundle.getString("error.application.creating"));
			logger.getExeptionsLogger().error(exception);
			return;
		}
		
		sendMessageToClient(resourceBundle.getString("application.created"));		
	}
	
	/**
	 * Find account and change account password.
	 * @param message message with login, old password, new password.
	 */
	private void changePasswordAction(String message) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedOldPassword = message.split(",")[1]; // old password
		String receivedNewPassword = message.split(",")[2]; // new password
		
		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin) && (user.getPassword().equals(receivedOldPassword))) {
				user.setPassword(receivedNewPassword);
				sendMessageToClient(resourceBundle.getString("password.changed"));
				return;
			}
		}
		
		sendMessageToClient(resourceBundle.getString("login.password.incorrect"));
	}
	
	/**
	 * Disconnect client from server.
	 * @param clientSocket
	 */
	private void disconnectClientAction(Socket clientSocket) {
		try {
			clientSocket.close();
		} catch (IOException exception) {
			logger.getExeptionsLogger().error(exception);
		}
	}
	
	/**
	 * Delete account from users list.
	 * @param message
	 */
	private void deleteAccountAction(String message) {
		String receivedLogin = message.split(",")[0]; // login
		String receivedPassword = message.split(",")[1]; // pass
		
		for (User user : DataStorage.USERS_LIST) { // search in user list that has been loaded when program start
			if (user.getLogin().equals(receivedLogin) && (user.getPassword().equals(receivedPassword))) {
				DataStorage.USERS_LIST.remove(user);
				sendMessageToClient(resourceBundle.getString("account.deleted"));
				return; // exit from cycle
			}
		}
		
		sendMessageToClient(resourceBundle.getString("login.password.incorrect"));
	}
	
	/**
	 * Collect report and send it to client.
	 * @param messageFromClient
	 */
	private void getReportAction(String messageFromClient) {
		StringBuilder data = new StringBuilder();
		Date today = new Date(System.currentTimeMillis());
		
		switch (ReportType.valueOf(messageFromClient.trim().toUpperCase())) {
			case ALL_CLIENTS:
				for (User user : DataStorage.USERS_LIST) {
					data.append(String.format("%s,%s,%s\n", user.getLogin(), user.getPassword(), user.getStatus().toString()));
				}
				break;
			case CURRENT_CLIENTS:
				//TODO find all connected sockets and collect it
				break;
			case CURRENT_FREE_ROOMS:
				//TODO compare dates in each room with today date, choose needed and 
				// collect to string
				break;
			case CURRENT_APPLICATONS:
				for (Application application : DataStorage.APPLICATIONS_LIST) {
					data.append(String.format("%s,%s,%s,%s\n", String.valueOf(application.getNumberSeats()),
																application.getClassApartments().toString(),
																new SimpleDateFormat("dd.MM.yyyy").format(application.getArrivalDate()),
																new SimpleDateFormat("dd.MM.yyyy").format(application.getArrivalDate())));
				}
				break;
		}
		
		sendMessageToClient(data.toString());		
	}
	
	/**
	 * Close all clients connection and stop server.
	 */
	private void stopSeverAction(org.apache.log4j.Logger exceptionLogger) {
		for (Socket socket : server.getClientSockets()) {
			try {
				socket.close();
			} catch (IOException exception) {
				exceptionLogger.error(exception);
			}
		}
		
		try {
			server.getServerSocket().close();
		} catch (IOException exception) {
			exceptionLogger.error(exception);
		}
	}
	
}
