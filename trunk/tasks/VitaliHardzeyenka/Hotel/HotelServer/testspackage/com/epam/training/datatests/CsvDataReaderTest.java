package com.epam.training.datatests;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import com.epam.training.data.CsvDataReader;
import com.epam.training.data.DataAnalyser;
import com.epam.training.data.DataStorage;
import com.epam.training.data.FileFinder;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserStatus;

public class CsvDataReaderTest {

	@Test
	public void testRead() {
		String path = FileFinder.findFilePath("users.txt");
		
		assertFalse(path == null);
		
		CsvDataReader csvDataReader = new CsvDataReader(path);
		
		String data = csvDataReader.read(1);		
		System.out.println(data);
		
		ArrayList<String> stringList = new ArrayList<>();
		
		stringList = csvDataReader.read();
		
		for (String string : stringList) {
			ArrayList<String> elementList = DataAnalyser.split(string, ",");
			DataStorage.USERS_LIST.add(new User(elementList.get(0), elementList.get(1), UserStatus.valueOf(elementList.get(2).toUpperCase())));
		}
		
		assertFalse(DataStorage.USERS_LIST.isEmpty());
	}
	
}
