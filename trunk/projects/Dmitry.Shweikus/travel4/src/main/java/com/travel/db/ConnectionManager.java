package com.travel.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.travel.exceptions.DbSqlException;

public class ConnectionManager
{
	private static ConnectionManager INSTANCE = new ConnectionManager();
	private Connection conn;

	private ConnectionManager() {
	}

	public static ConnectionManager getInstance()
	{
		return INSTANCE;
	}

	public Connection getConnection() throws DbSqlException
	{
		try
		{
			if (conn == null || (conn != null && conn.isClosed()))
			{
				conn = makeConnection();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw new DbSqlException("Can't establish connection to database!");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new DbSqlException("Database driver is not found!");
		}
		return conn;
	}

	private Connection makeConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.gjt.mm.mysql.Driver");
		URI dbUri;
		try
		{
			dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
			//String dbUrl = "jdbc:mysql://localhost" + dbUri.getPath();

			return DriverManager.getConnection(dbUrl, username, password);
		} catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void finalize() throws Throwable
	{
		if (conn != null)
		{
			conn.close();
		}
		super.finalize();
	}

}
