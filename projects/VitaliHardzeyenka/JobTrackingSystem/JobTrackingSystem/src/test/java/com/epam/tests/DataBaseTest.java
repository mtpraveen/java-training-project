package com.epam.tests;

import static org.junit.Assert.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.logic.Resource;
import com.epam.logic.database.ConnectionsPool;
import com.epam.logic.database.DataBaseOpener;
import com.epam.logic.database.DataBaseReader;
import com.epam.logic.database.DataBaseWriter;

public class DataBaseTest {
	
	@Ignore
	@Test
	public void testConnection() {
		DataBaseOpener dataBaseOpener = new DataBaseReader();
        Connection connection = (Connection) dataBaseOpener.createConnection();
        
        assertFalse(connection == null);
	}

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
			dataBaseWriter.insert("users", "new login", "new name", "new surname", "new password", "email@email.com", 2);
			
			Date beginDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse("2013-01-20");
        	Date endDate = (Date) (new SimpleDateFormat("yyyy-MM-dd")).parse("2013-01-22");
			// Insert into 'jobs'
			dataBaseWriter.insert("jobs", "new test job", "no description", 55, beginDate, endDate, 2, 2, 2, 1);			
		} catch (SQLException | ParseException e) {
			// TODO logger
			e.printStackTrace();
		}
        
	}
	@Ignore
	@Test
	public void testUpdate() {
		DataBaseWriter dataBaseWriter = new DataBaseWriter();
        
        String update = "update users set name='Ibraghim' where surname='Petrov';";
        dataBaseWriter.update(update);
	}
	
	@Test
	public void testPool() throws SQLException, ClassNotFoundException {
		Resource resource = new Resource("dataBaseProperties");
		String url = String.format("%s//%s/%s", resource.getValue("url"), resource.getValue("serverName"), resource.getValue("dataBaseName"));
		String driverName = resource.getValue("driverName");
		String username = resource.getValue("userName");
		String password = resource.getValue("password");
		ConnectionsPool pool = new ConnectionsPool(url, driverName, username, password);
		Connection connection = pool.createConnection();
		System.out.println(connection);			
		assertFalse(connection == null);
		
		ResultSet result = select(connection, "select * from statuses;");
		
		while(result.next()) {
			System.out.println(result.getObject("status_id") + " "+  result.getObject("status"));
		}
	}
	
	public ResultSet select(Connection connection, String request) {
    	ResultSet resultSet = null;
    	try {
            Statement statement;
            statement = connection.createStatement();

            resultSet = statement.executeQuery(request);

            return resultSet;
        }  catch (SQLException exception) {

            if (connection != null) {
                try {
                	connection.rollback();
                } catch(SQLException e) {
                }
            }

            return null;
        } finally {        	
            try {
            	connection.setAutoCommit(true);
			} catch (SQLException e) {
			}
        }
    }
	
}
