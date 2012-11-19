package com.epam.training.data;

import java.io.File;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.epam.training.logic.Logger;
import com.epam.training.model.application.Application;
import com.epam.training.model.room.ClassApartments;
import com.epam.training.model.room.Room;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserStatus;

/**
 * In this class upload data after reading it from text file. 
 * @author Gordeenko
 */
public class DataStorage {
	
	public static ArrayList<Room> ROOMS_LIST = new ArrayList<>(); // information about rooms all in hotel
	public static ArrayList<User> USERS_LIST = new ArrayList<>(); // info about user can connect to server 
	public static ArrayList<Application> APPLICATIONS_LIST = new ArrayList<>(); // info about applications submitted to server
	
	private static Logger logger = new Logger(org.apache.log4j.Logger.getLogger(DataStorage.class.getName()));
	
	/**
	 * Load data from csv files to DataStrogate when server is start.
	 * Check if zip file contain all need text files with data. 
	 * Load data from users.txt, applications.txt, rooms.txt.
	 * @param zipAbsolutePath
	 */
	public static void loadData(String zipAbsolutePath) {
		CsvDataReader csvDataReader;
		File zipFile = new File(zipAbsolutePath);
		File[] files = new File[] {
				new File("users.txt"),
				new File("applications.txt"),
				new File("rooms.txt")
		};
		String parentFolder = null;
		ArrayList<String> users = new ArrayList<>();
		ArrayList<String> applications = new ArrayList<>();
		ArrayList<String> rooms = new ArrayList<>();
		String[] data = null;
		
		if (ZipWorker.checkFiles(zipFile, files)) {
			ZipWorker.unzipFile(zipFile); // unzip zip file			
			
			// Read data from user.txt
			parentFolder = FileFinder.findFileParentFolder(zipFile.getAbsolutePath());
			csvDataReader = new CsvDataReader(parentFolder + "users.txt");
			users = csvDataReader.read(); // read all users from data base
			
			// Add data to userList in DataStorage.
			for (String user : users) {
				data = user.split(",");
				// Add new user in user list: login, password, userStatus
				DataStorage.USERS_LIST.add(new User(data[0], data[1], UserStatus.valueOf(data[2].toUpperCase())));
			}
			
			// Read data form application.txt
			csvDataReader = new CsvDataReader(parentFolder + "applications.txt");
			applications = csvDataReader.read();
			
			try {
				// Add data to applicationList in DataStorage.
				for (String application : applications) {
					data = application.split(",");
					DataStorage.APPLICATIONS_LIST.add(
							new Application(Integer.valueOf(data[0]), 
									ClassApartments.valueOf(data[1].toString().toUpperCase()), 
									(Date) (new SimpleDateFormat("dd.MM.yyyy")).parse(data[2]),
									(Date) (new SimpleDateFormat("dd.MM.yyyy")).parse(data[3])));
				}
			} catch(ParseException exception) {
				logger.getExeptionsLogger().error(exception);
			}
			// Read data from rooms.txt
			csvDataReader = new CsvDataReader(parentFolder + "rooms.txt");
			rooms = csvDataReader.read();
			
			// Add data to roomList in dataStorage
			for (String room : rooms) {
				data = room.split(",");
				DataStorage.ROOMS_LIST.add(new Room(Integer.valueOf(data[0]), 
													Integer.valueOf(data[1]),
													ClassApartments.valueOf(data[2].toUpperCase()),
													Integer.valueOf(data[3])));				
			}
		}
	}
}
