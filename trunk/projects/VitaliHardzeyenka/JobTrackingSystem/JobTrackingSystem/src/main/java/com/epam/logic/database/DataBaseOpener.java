package com.epam.logic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.logic.Logger;

/**
 * Set connection, open dataBase, check is dataBase exist and free.
 * @author Gordeenko
 */
public class DataBaseOpener {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(DataBaseOpener.class.getName()));

    /**
     * Create connection to data base.
     * @param serverName - name of server.
     * @param dataBaseName - name of data base
     * @param userName - login of data base user
     * @param password - password
     * @return connection object with specified data base.
     */
    public Connection createConnection(String serverName, String dataBaseName, String userName, String password) {
    	String driverName = null;
    	String url = null;
        
    	try {
    		driverName = "com.mysql.jdbc.Driver"; // driver name
    		Class.forName(driverName); // set the driver

    		// Create a connection to the database.
    		url = String.format("jdbc:mysql://%s/%s", serverName, dataBaseName);
    		return (Connection) DriverManager.getConnection(url, userName, password);
    	} catch (ClassNotFoundException | SQLException exception) {
    		logger.getExceptionTextFileLogger().error(exception);
    		return null;
    	}
    }
}