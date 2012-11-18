package com.epam.training.logic;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.epam.training.data.CsvDataReader;
import com.epam.training.data.DataStorage;
import com.epam.training.data.FileFinder;
import com.epam.training.data.ZipWorker;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserStatus;
import com.epam.training.threads.ServerThread;

/**
 * Working with a Server class, waiting for the new clients, working with they,
 * describe sequence of task processing.
 * @author Gordeenko
 */
public class ServerWorker {
	private Server server = Server.getInstance(); // server object
	private ServerManager serverManager = new ServerManager(); // object with all possible server actions
	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ServerWorker.class)); // logger
	CsvDataReader csvDataReader;
	
	/**
	 * 
	 */
	public void run() {
		
		loadData(System.getProperty("user.dir") + "\\data\\data.zip"); // load data from zip archive
		
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
	
	private void loadData(String zipAbsolutePath) {
		File zipFile = new File(zipAbsolutePath);
		File[] files = new File[] {
				new File("users.txt"),
				new File("applications.txt"),
				new File("rooms.txt")
		};
		String parentFolder = null;
		ArrayList<String> users = new ArrayList<>();
		String login = null;
		String password = null;
		ArrayList<String> applications = new ArrayList<>();
		ArrayList<String> rooms = new ArrayList<>();
		
		
		
		if (ZipWorker.checkFiles(zipFile, files)) {
			ZipWorker.unzipFile(zipFile);
			
			parentFolder = FileFinder.findFileParentFolder(zipFile.getAbsolutePath());
			csvDataReader = new CsvDataReader(parentFolder + "users.txt");
			users = csvDataReader.read(); // read all users from data base
			
			for (String user : users) {
				String[] data = user.split(",");
				// Add new user in user list: login, password, userStatus
				DataStorage.USERS_LIST.add(new User(data[0], data[1], UserStatus.valueOf(data[2].toUpperCase())));
			}
			
			
			// TODO read applicatons and rooms
			
		}
		
	}
}
