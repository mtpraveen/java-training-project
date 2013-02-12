package com.epam.logic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.logic.Logger;
import com.epam.logic.Resource;

/**
 * Set connection, open dataBase, check is dataBase exist and free.
 * @author Gordeenko
 */
public class DataBaseOpener {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(DataBaseOpener.class.getName()));

	protected Connection connection;
	
	public DataBaseOpener() {
		this.connection = createConnection();
	}
	
    /**
     * Create connection to data base.
     * @param serverName - name of server.
     * @param dataBaseName - name of data base
     * @param userName - login of data base user
     * @param password - password
     * @return connection object with specified data base.
     */
    public Connection createConnection() {
    	Resource resource = new Resource("dataBaseProperties");
    	String url = null;
        
    	try {
    		Class.forName(resource.getValue("driverName")); // set the driver

    		// Create a connection to the database.
    		url = String.format("%s//%s/%s", resource.getValue("url"), resource.getValue("serverName"), resource.getValue("dataBaseName"));
    		return (Connection) DriverManager.getConnection(url, resource.getValue("userName"), resource.getValue("password"));
    	} catch (ClassNotFoundException | SQLException exception) {
    		logger.getExceptionTextFileLogger().error(exception);
    		return null;
    	}
    	
//    	InitialContext initialContext;
//		try {
//			initialContext = new InitialContext();
//			DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/mysql");
//			return connection = dataSource.getConnection();
//			
//		} catch (NamingException | SQLException e) {
//			// TODO excetion logger
//			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException sqlException) {
//					
//				}
//				
//				connection = null;
//			}
//		}
//		return null;
    	
    }
}