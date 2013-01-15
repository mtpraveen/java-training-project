package com.travel.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.travel.exceptions.DbSqlException;

public class ConnectionManager
{
	private static ConnectionManager INSTANCE = new ConnectionManager();
	private static DataSource dataSource = null;

	public static ConnectionManager getInstance()
	{
		return INSTANCE;
	}
	private static DataSource getDataSource() throws ClassNotFoundException, URISyntaxException
	{
		if (dataSource == null)
		{
			String className = "org.gjt.mm.mysql.Driver";
			
			//is this string really required?
			Class.forName(className);

			URI dbUri;
			dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
			dbUrl += "?encoding=UTF-8&useUnicode=true&characterEncoding=UTF-8&reconnect=true";
			
			PoolProperties p = new PoolProperties();
	        p.setUrl(dbUrl);
	        p.setDriverClassName(className);
	        p.setUsername(username);
	        p.setPassword(password);
	        p.setTestWhileIdle(false);
	        p.setTestOnBorrow(true);
	        p.setValidationQuery("SELECT 1");
	        p.setTestOnReturn(false);
	        p.setMaxActive(4);
	        p.setInitialSize(3);

	        dataSource = new DataSource();
	        dataSource.setPoolProperties(p); 
		}
		return dataSource;
	}
	public static Connection getConnection() throws DbSqlException 
	{
		try {
			return getDataSource().getConnection();
		} catch (ClassNotFoundException e) {
			//throw new DbSqlException("Database driver is not found!");
			throw new DbSqlException(e);
		} catch (SQLException e) {
			//throw new DbSqlException("Can't establish connection to database!");
			throw new DbSqlException(e);
		} catch (URISyntaxException e) {
			//throw new DbSqlException("Environment variable not found CLEARDB_DATABASE_URL!");
			throw new DbSqlException(e);
		}
	}

}
