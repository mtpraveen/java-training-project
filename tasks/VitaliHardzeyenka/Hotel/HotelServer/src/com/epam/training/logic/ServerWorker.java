package com.epam.training.logic;

import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.epam.training.data.DataStorage;
import com.epam.training.threads.ServerThread;

/**
 * Working with a Server class, waiting for the new clients, working with they,
 * describe sequence of task processing.
 * @author Gordeenko
 */
public class ServerWorker {
	private Server server = Server.getInstance(); // server object
	private ServerManager serverManager = new ServerManager(); // object with all possible server actions
	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ServerWorker.class.getName())); // logger	
	
	/**
	 * 
	 */
	public void run() {
		
		DataStorage.loadData(System.getProperty("user.dir") + "\\data\\data.zip"); // load data from zip archive
		
		/*
		 * Cycle for every new client requests processing:
		 * accept new client by server;
		 * add new client in client list,
		 * create new thread for new client,
		 * process all requests received from client,
		 * get thread result.
		 */
		while (!server.getServerSocket().isClosed()) { // do while server will not be shutdown
			
			// Wait new client and connect with it. 
			Socket clientSocket = serverManager.acceptClientConnection(server.getServerSocket(), logger.getExeptionsLogger());
			
			if (clientSocket != null) { // if client was connected
				server.getClientSockets().add(clientSocket); // add new connected client to the list
				
				// Start work with new client.
				Callable<Void> serverThread = new ServerThread(server, clientSocket); // create new thread for new client
				ExecutorService executorService = Executors.newCachedThreadPool(); // create executor for thread running
				Future<Void> future = executorService.submit((Callable<Void>) serverThread); // add task in the run queue and start thread
				
				// Wait while task will done.
				while(!future.isDone()) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException ex) {
						// logger
					}
				}
				
				executorService.shutdown(); // close executor
				
//				// Try to get result from future object.
//				try {
//					return future.get(); // get task resultS
//				} catch (InterruptedException ex) {
//					ex.printStackTrace();
//					return null;
//				} catch (ExecutionException ex) {
//					// exception
//					return false;
//				}
			}
		}
	}	
	
}
