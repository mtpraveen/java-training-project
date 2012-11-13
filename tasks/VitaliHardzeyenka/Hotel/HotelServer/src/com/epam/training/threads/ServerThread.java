package com.epam.training.threads;

import java.net.Socket;
import java.util.concurrent.Callable;

import com.epam.training.data.CsvDataReader;
import com.epam.training.data.CsvDataWriter;
import com.epam.training.data.DataAnalyser;
import com.epam.training.logic.Logger;
import com.epam.training.logic.MessageTypes;
import com.epam.training.logic.Server;
import com.epam.training.logic.ServerManager;

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
					messageAction(serverManager, clientSocket, messageFromClient, logger.getExeptionsLogger());
					break;
				case "LOG_ON" : 
					break;
				case "CREATE_ACCOUNT" :
					break;
				case "CHANGE_PASSWORD" :
					break;
				case "DISCONNECT_CLIENT" :
					break;
				case "ADD_ACCOUNT" :
					break;
				case "DELETE_ACCOUNT" :
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
	
	private void logOnAction(ServerManager serverManager, Socket clientSocket, String message, org.apache.log4j.Logger exceptionLogger) {
		
	}
	
}
