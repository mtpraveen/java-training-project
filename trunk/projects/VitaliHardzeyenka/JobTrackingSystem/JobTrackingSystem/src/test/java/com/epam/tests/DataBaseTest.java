package com.epam.tests;

import static org.junit.Assert.assertFalse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.epam.logic.database.DataBaseReader;
import com.epam.logic.database.DataBaseWriter;

public class DataBaseTest {
	
	@Ignore
	@Test
	public void testSelect() {
		DataBaseReader dataBaseReader = new DataBaseReader();
        java.sql.ResultSet resultSet = null;
        
        try {
        	// select user by some parameters (login, password)
        	resultSet = null;
        	resultSet = dataBaseReader.select("users", "login", "password", "new login", "new password");
        	assertFalse(resultSet == null);
        	assertFalse(resultSet.first() == false);

        	// select all users by one parameter (user_id)
        	for (int i = 1; i < 5; i++) {
        		resultSet = null;
        		resultSet = dataBaseReader.select("users", "user_id", i);
        		assertFalse(resultSet == null);
        		assertFalse(resultSet.first() == false);
        	}
        } catch (SQLException exception) {
        	exception.printStackTrace();
        }
	}
	@Ignore
	@Test
	public void testInsert() {
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
        
        try {
        	// Insert into 'users'
			try {
				dataBaseWriter.insert("users", "new login", "new name", "new surname", "new password", "email@email.com", 2);
				Date beginDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse("2013-01-20");
				Date endDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse("2013-01-22");
				// Insert into 'jobs'
				dataBaseWriter.insert("jobs", "new test job", "no description", 55, beginDate, endDate, 2, 2, 2, 1);			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
        
	}
	@Test
	public void testUpdate() {
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("name", "Natalia");
		
		Map<String, Object> whereCon = new HashMap<>();
		whereCon.put("surname", "Petrov");
        dataBaseWriter.update("users", conditions, whereCon);
	}
	
	@Ignore
	@Test
	public void testPool() throws SQLException, ClassNotFoundException {
		
		DataBaseReader reader = new DataBaseReader();
		
		ResultSet resultSet1 = reader.select("select * from statuses");
		while (resultSet1.next()) {
			System.out.println(resultSet1.getObject("status_id") + " " + resultSet1.getObject("status"));
		}
		
		ResultSet resultSet2 = reader.select("statuses", "status_id", 1); 
		while (resultSet2.next()) {
			System.out.println(resultSet2.getObject("status_id") + " " + resultSet2.getObject("status"));
		}
		
		ResultSet resultSet3 = reader.select("select * from statuses");
		while (resultSet3.next()) {
			System.out.println(resultSet3.getObject("status_id") + " " + resultSet3.getObject("status"));
		}
		
		ResultSet resultSet4 = reader.select("statuses", "status_id", 1); 
		while (resultSet4.next()) {
			System.out.println(resultSet4.getObject("status_id") + " " + resultSet4.getObject("status"));
		}
	}
	
}
