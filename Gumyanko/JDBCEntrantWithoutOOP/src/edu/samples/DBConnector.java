package edu.samples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static final DBConnector INSTANCE = new DBConnector();
	private Connection conn;

	private DBConnector() {

	}

	public static DBConnector getConnector() {
		return INSTANCE;
	}

	public Connection getConnection() {
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/a", "root", "");
				// TODO read from properties
			}
			return conn;
		} catch (SQLException e) {
			// problem to connect to DB
		}
		return null;
	}
}
