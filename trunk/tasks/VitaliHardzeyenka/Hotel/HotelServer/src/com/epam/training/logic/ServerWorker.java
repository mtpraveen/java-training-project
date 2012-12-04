package com.epam.training.logic;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.epam.training.data.CsvDataWriter;
import com.epam.training.data.DataStorage;
import com.epam.training.data.ZipWorker;
import com.epam.training.model.application.Application;
import com.epam.training.model.room.Room;
import com.epam.training.model.user.User;
import com.epam.training.threads.ServerThread;

/**
 * Working with a Server class, waiting for the new clients, working with they,
 * describe sequence of task processing.
 * @author Gordeenko
 */
public class ServerWorker {
	private Server server = Server.getInstance(); // server object
	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ServerWorker.class.getName())); // logger	
	
	public void run() {
		
		// Read data from files in zip archive and create appropriate
		// objects in DataStorage lists.
		DataStorage.loadData(System.getProperty("user.dir") + "\\data\\data.zip");

		/*
		 * Cycle for every new client requests processing:
		 * accept new client by server;
		 * add new client in client list;
		 * create new thread for new client;
		 * process all requests received from client;
		 * get thread result.
		 */
		while (!server.getServerSocket().isClosed()) { // do while server will not be shutdown
			// Wait new client and connect with it.
			Socket clientSocket = null;
			try {
				clientSocket = server.getServerSocket().accept();
			} catch (IOException exception) {
				logger.getExeptionsLogger().error(exception);
			}
			
			System.out.println("Client connected: " + clientSocket.getInetAddress());
			
			if (clientSocket != null) { // if client was connected
				server.getClientSockets().add(clientSocket); // add new connected client to the list
				
				// Start work with new client.
				ServerThread serverThread = new ServerThread(server, clientSocket);
				serverThread.start();
			}
		}
		
		/**
		 * Upload information from DataStorage lists, 
		 * write it to text files and add it to archive, 
		 * then delete all text files, leave zip only.
		 */
		writeDataToCsvFiles();
	}
	
	/**
	 * First delete all files in \data\ folder.
	 * Then based on lists of DataStorage create files for 
	 * users, rooms and applications objects. Write data from 
	 * list in that files. Create zip from that files,
	 * Then delete text files, leave zip only.
	 */
	public void writeDataToCsvFiles() {
		String folderDirectory = System.getProperty("user.dir") + "\\data\\";
		String usersFile = "users.txt";
		String roomsFile = "rooms.txt";
		String applicationsFile = "applications.txt";
		String data = null;
		ArrayList<String> dataList = new ArrayList<>();
		CsvDataWriter csvDataWriter = null;
		
		// Delete files from folder.
		ZipWorker.deleteAllFilesInFolder(folderDirectory);
		
		// Write users data into user.txt.
		csvDataWriter = new CsvDataWriter(String.format("%s%s", folderDirectory, usersFile));
		for (User user : DataStorage.USERS_LIST) {
			data = String.format("%s,%s,%s", user.getLogin(), user.getPassword(), user.getStatus().toString());
			System.out.println(data);
			dataList.add(data);
		}
		csvDataWriter.write(dataList);
		dataList.clear();
		
		// Write rooms data into rooms.txt.
		csvDataWriter = new CsvDataWriter(String.format("%s%s", folderDirectory, roomsFile));
		for (Room room : DataStorage.ROOMS_LIST) {
			data = String.format("%s,%s,%s,%s", String.valueOf(room.getNumberRoom()), 
												String.valueOf(room.getNumberSeats()), 
												room.getClassApartments().toString(), 
												String.valueOf(room.getDayPrice()));
			System.out.println(data);
			dataList.add(data);
		}
		csvDataWriter.write(dataList);
		dataList.clear();
		
		// Write applications data into applications.txt.
		csvDataWriter = new CsvDataWriter(String.format("%s%s", folderDirectory, applicationsFile));
		for (Application application : DataStorage.APPLICATIONS_LIST) {
			data = String.format("%s,%s,%s,%s", String.valueOf(application.getNumberSeats()), 
												application.getClassApartments().toString(),
												new SimpleDateFormat("dd.MM.yyyy").format(application.getArrivalDate()),
												new SimpleDateFormat("dd.MM.yyyy").format(application.getEvictionDate()));
			System.out.println(data);
			dataList.add(data);
		}
		csvDataWriter.write(dataList);
		dataList.clear();
		
		// Add files to archive.
		File[] files = new File[] {
			new File(folderDirectory + usersFile),
			new File(folderDirectory + applicationsFile),
			new File(folderDirectory + roomsFile)
		};		
		ZipWorker.addFilesToZip(files, folderDirectory + "data.zip");
		
		// Delete all text files in current folder.
		for (File file : files) {
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
