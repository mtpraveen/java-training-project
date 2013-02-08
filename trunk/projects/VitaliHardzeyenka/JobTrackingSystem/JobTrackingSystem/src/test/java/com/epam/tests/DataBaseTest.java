package com.epam.tests;

import static org.junit.Assert.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import org.junit.Ignore;
import org.junit.Test;

import com.epam.logic.database.DataBaseReader;
import com.epam.logic.database.DataBaseWriter;

public class DataBaseTest {
	
	@Test
	public void testConnection() {
		DataBaseReader dataBaseReader = new DataBaseReader();
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
        Connection connectionReader = (Connection) dataBaseReader.createConnection("localhost:3306", "job_tracking_system", "root", "root");
        Connection connectionWriter = (Connection) dataBaseWriter.createConnection("localhost:3306", "job_tracking_system", "root", "root");
        
        assertFalse(connectionReader == null);
        assertFalse(connectionWriter == null);
	}

	@Test
	public void testSelect() {
		DataBaseReader dataBaseReader = new DataBaseReader();
        Connection connection = (Connection) dataBaseReader.createConnection("localhost:3306", "job_tracking_system", "root", "root");
        java.sql.ResultSet resultSet = null;
        
        assertFalse(connection == null);

        if (connection != null) {
            try {
            	// select user by some parameters (login, password)
            	resultSet = null;
            	resultSet = dataBaseReader.select(connection, "users", "login", "password", "new login", "new password");
            	assertFalse(resultSet == null);
            	assertFalse(resultSet.first() == false);
            	
            	// select all users by one parameter (user_id)
                for (int i = 1; i < 5; i++) {
                	resultSet = null;
                	resultSet = dataBaseReader.select(connection, "users", "user_id", i);
                	assertFalse(resultSet == null);
                	assertFalse(resultSet.first() == false);
                 }
            } catch (SQLException exception) {
            	exception.printStackTrace();
            } finally {
            	try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
	}
	
	@Test
	public void testInsert() {
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
        Connection connection = (Connection) dataBaseWriter.createConnection("localhost:3306", "job_tracking_system", "root", "root");
        
        try {
        	// Insert into 'users'
			dataBaseWriter.insert(connection, "users", "new login", "new name", "new surname", "new password", "email@email.com", 2);
			
			Date beginDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse("2013-01-20");
        	Date endDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse("2013-01-22");
			// Insert into 'jobs'
			dataBaseWriter.insert(connection, "jobs", "new test job", "no description", 55, beginDate, endDate, 2, 2, 2, 1);			
		} catch (SQLException | ParseException e) {
			// TODO logger
			e.printStackTrace();
		}
        
	}
	
	@Test
	public void testUpdate() {
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
        Connection connection = (Connection) dataBaseWriter.createConnection("localhost:3306", "job_tracking_system", "root", "root");
        
        String update = "update users set name='Ibraghim' where surname='Petrov';";
        dataBaseWriter.update(connection, update);
	}
	
}
