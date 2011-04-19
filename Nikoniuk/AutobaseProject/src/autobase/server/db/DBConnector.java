package autobase.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static DBConnector instance;
	private static final ConfigurationManager cfg = ConfigurationManager.getInstance();
	private static Connection connection;

	private DBConnector() { }

	public static DBConnector getConnector() {
		if (instance == null)
			instance = new DBConnector();
		return instance;
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(cfg.getDriverName());
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		
		if (connection == null) {
			connection = DriverManager.getConnection(cfg.getDbUrl(), 
														cfg.getUsername(), 
															cfg.getPassword());
		}
		return connection;
	}
}
