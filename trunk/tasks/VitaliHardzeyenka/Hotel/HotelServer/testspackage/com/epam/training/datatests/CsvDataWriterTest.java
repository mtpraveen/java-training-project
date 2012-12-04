package com.epam.training.datatests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.training.data.DataStorage;
import com.epam.training.data.ZipWorker;
import com.epam.training.logic.ServerWorker;

public class CsvDataWriterTest {

	@Test
	public void test() {
		ServerWorker serverWorker = new ServerWorker();
		
		DataStorage.loadData(System.getProperty("user.dir") + "\\data\\data.zip");
		
		System.out.println("users: " + DataStorage.USERS_LIST.size());
		System.out.println("rooms: " + DataStorage.ROOMS_LIST.size());
		System.out.println("applications: " + DataStorage.APPLICATIONS_LIST.size());
		
		serverWorker.writeDataToCsvFiles();
		
	}
}
