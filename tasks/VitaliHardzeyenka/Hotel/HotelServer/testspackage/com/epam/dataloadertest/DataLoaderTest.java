package com.epam.dataloadertest;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.epam.training.data.DataStorage;
import com.epam.training.model.application.Application;
import com.epam.training.model.room.Room;
import com.epam.training.model.user.User;


public class DataLoaderTest {

	@Test
	public void testLoadData() {
		String parentFolder = System.getProperty("user.dir") + "\\data\\";		
		DataStorage.loadData(parentFolder + "data.zip");
		
		assertFalse(DataStorage.APPLICATIONS_LIST.isEmpty());
		assertFalse(DataStorage.USERS_LIST.isEmpty());
		assertFalse(DataStorage.ROOMS_LIST.isEmpty());
		
		System.out.println("\tusers");
		for (User user : DataStorage.USERS_LIST) {
			System.out.println(user.getLogin() + " " + user.getPassword() + " " + user.getStatus());
		}
		System.out.println("\trooms");
		for (Room room : DataStorage.ROOMS_LIST) {
			System.out.println(room.getNumberRoom() + " " + room.getNumberSeats() + " " + room.getClass().toString() + " " + room.getDayPrice());
		}
		System.out.println("\tapplications");
		for (Application application : DataStorage.APPLICATIONS_LIST) {
			System.out.println(application.getNumberSeats() + " " + application.getClassApartments() + " " + application.getArrivalDate() + " " + application.getEvictionDate());
		}
		
	}

}
