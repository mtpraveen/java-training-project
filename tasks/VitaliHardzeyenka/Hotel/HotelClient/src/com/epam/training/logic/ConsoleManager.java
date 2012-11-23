package com.epam.training.logic;

import java.io.IOException;
import java.net.Socket;
import java.util.ResourceBundle;

/**
 * @author EXUMLOKE
 * This class used for giving control to user throw
 * console. Its read commands from the console and 
 * output messages that has been received from server.
 * Used localization: en-ru.
 */
public class ConsoleManager {
	
	private ServerConnection connection; // server connection and input output streams
	private ClientManager clientManager;
	private Socket socket; // socket of server
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ConsoleManager.class.getName())); 
	private final ConsoleInput consoleInput = new ConsoleInput();
//	private Locale locale = new Locale("en_US");
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("com.epam.training.logic.resources.clientMessages");
	
	// clients data
	private String login; // login for creating or logging on account
	private String password; // password for creating or logging on account
	private String oldPassword; // old password for changing password
	private String newPassword; // new password for changing password
	private String answer; // answer from server
	private String message; // message that will send to server
	private char command; // console command from user
	
	/**
	 * Constructor
	 * @param connection server connection with
	 * 					 input output streams.
	 */
	public ConsoleManager(ServerConnection connection) {
		this.connection = connection;
		this.clientManager = new ClientManager(connection);
	}

	/**
	 * Start client session with users control.
	 * Log on or create new account.
	 * All other might actions: changing password, deleting account,
	 * logging off, creating application, getting report.
	 */
	public void runSession() {
		socket = connection.getSocket();
		
		System.out.println(resourceBundle.getString("you.connect")+ socket.getPort());
		
		// Read user choice: log on or create account.
		do {
			System.out.println(resourceBundle.getString("log.on") +
							   resourceBundle.getString("create.account"));
			command = consoleInput.readString().charAt(0);			
		} while (!((command != '1') ^ (command != '2')));
		
		// Reading login and password.
		System.out.println(resourceBundle.getString("type.login"));
		login = consoleInput.readString();
		System.out.println(resourceBundle.getString("type.password"));
		password = consoleInput.readString();
		
		// Give control to user.
		// This describe client session.
		if (enterClient(login, password, command)) { // try to create account or log on
		
			// Cycle for client manage from console while client is open. 
			do {
				System.out.println(resourceBundle.getString("change.password") +
								   resourceBundle.getString("delete.account") +
								   resourceBundle.getString("log.off") +
								   resourceBundle.getString("create.application") +
								   resourceBundle.getString("get.report"));
				command = consoleInput.readString().charAt(0);
				
				switch(command) {
					case '1' : // change password
						changePasswordAction();
						break;
					case '2' : // delete account
						deleteAccountAction();
						break;
					case '3' : // log off
						logoffAction();
						break;
					case '4' : // create application
						createApplicationAction();
						break;
					case '5' : // get report
						getReportAction();
						break;
					default:
						break;
				}
			} while (!socket.isClosed());
		}
	}
	
	/**
	 * Log on or create new account. 
	 * Send login, password and command, that must
	 * be checked by server.
	 * @param login user login
	 * @param password user password
	 * @param command command for logging on or creating new account
	 * @return true if log on or created account, else false.
	 */
	private boolean enterClient(String login, String password, char command) {
		message = String.format("%s,%s", login, password);
		boolean isEntered = false;
		String commandType = null;

		commandType = (command == '1') ? "LOG_ON" : "CREATE_ACCOUNT";
		this.clientManager.sendMessageToServer(message, MessageTypes.valueOf(commandType.toUpperCase()), logger.getExeptionsLogger());
		answer = this.clientManager.receiveMessageFromServer(logger.getExeptionsLogger());
		
		switch (answer) {
			case "true" :
				isEntered = true;
				System.out.println(resourceBundle.getString("in.system"));
				break;
			case "false" :
				isEntered = false;
				System.out.println(resourceBundle.getString("incorrect.login.password"));
				break;
			case "used" :
				isEntered = false;
				System.out.println(resourceBundle.getString("login.use"));
				break;
			default:
				break;
		}
		
		return isEntered;
	}
	
	/**
	 * Changing password in current account.
	 * Read login, oldPass, newPass from console.	 * 
	 */
	private void changePasswordAction() {
		// Reading login and password.
		System.out.println(resourceBundle.getString("type.login"));
		login = consoleInput.readString();
		System.out.println(resourceBundle.getString("type.old.password"));
		oldPassword = consoleInput.readString();
		System.out.println(resourceBundle.getString("type.new.password"));
		newPassword = consoleInput.readString();
		
		message = String.format("%s,%s,%s", login, oldPassword, newPassword);
		
		this.clientManager.sendMessageToServer(message, MessageTypes.CHANGE_PASSWORD, logger.getExeptionsLogger());
		System.out.println(this.clientManager.receiveMessageFromServer(logger.getExeptionsLogger()));		
	}
	
	/**
	 * Delete account by login.
	 * Request login and password for deleting.
	 */
	private void deleteAccountAction() {
		// Reading login and password.
		System.out.println(resourceBundle.getString("type.login"));
		login = consoleInput.readString();
		System.out.println(resourceBundle.getString("type.password"));
		
		message = String.format("%s,%s", login, password);
		
		this.clientManager.sendMessageToServer(message, MessageTypes.DELETE_ACCOUNT, logger.getExeptionsLogger());
		System.out.println(this.clientManager.receiveMessageFromServer(logger.getExeptionsLogger()));
	}
	
	/**
	 * Logging client off.
	 * First disconnect client on server,
	 * then disconnect from server on client side. 
	 */
	private void logoffAction() {
		this.clientManager.sendMessageToServer("logoff", MessageTypes.DISCONNECT_CLIENT, logger.getExeptionsLogger());
		
		// Wait timeout while server disconnect this client.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException exception) {
			logger.getExeptionsLogger().error(exception);
		}
		
		// Disconnect server.
		try {
			this.socket.close();
			this.connection.getSocket().close();
		} catch (IOException exception) {
			logger.getExeptionsLogger().error(exception);
		}
		
		System.out.println(resourceBundle.getString("client.disconnect"));
	}

	/**
	 * Create new application and save it on server data base.
	 * Request number seats, class apartment, arrival date,
	 * eviction data for creating.
	 */
	private void createApplicationAction() {
		String numberSeats = null;
		String classApartments = null;
		String arrivalDate = null;
		String evictionDate = null;
		 
		// Read application data.
		System.out.println(resourceBundle.getString("type.seats"));
		numberSeats = consoleInput.readString();
		System.out.println(resourceBundle.getString("type.class.apartments"));
		classApartments = consoleInput.readString();
		System.out.println(resourceBundle.getString("type.arrival.date"));
		arrivalDate = consoleInput.readString();
		System.out.println(resourceBundle.getString("type.eviction.date"));
		evictionDate = consoleInput.readString();
		
		message = String.format("%s,%s,%s,%s", numberSeats, classApartments, arrivalDate, evictionDate);
		
		this.clientManager.sendMessageToServer(message,MessageTypes.CREATE_APPLICATION, logger.getExeptionsLogger());
		
		System.out.println(this.clientManager.receiveMessageFromServer(logger.getExeptionsLogger()));
	}
	
	/**
	 * 
	 */
	private void getReportAction() {
//		this.clientManager.sendMessageToServer("rooms", messageType, exeptionsLogger)
	}
	
}
