package com.epam.logic.database;

import java.sql.SQLException;
import java.sql.SQLWarning;

import com.epam.logic.Logger;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * @author EXHUMLOKI
 * Class describe exemplar of data base connection and
 * all might actions with it.
 * Objects of this class will be situate in ConnectionsPool.
 * All actions from ConnectionsPool applicable for this object.  
 * Major states of connection: inUse, isClosed.
 */
public class ConnectionFromPool {
	
	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ConnectionFromPool.class.getName()));
	private java.sql.Connection connection; // sql connection to data base 
	private boolean inUse; // if this connection use right now
	private long timeOpened; // time of last connection opening
	private long timeClosed; // time of last connection closing
	
	public java.sql.Connection getConnection() {
		return connection;
	}

	public void setConnection(java.sql.Connection connection) {
		this.connection = connection;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public long getTimeOpened() {
		return timeOpened;
	}

	public void setTimeOpened(long timeOpened) {
		this.timeOpened = timeOpened;
	}

	public long getTimeClosed() {
		return timeClosed;
	}

	public void setTimeClosed(long timeClosed) {
		this.timeClosed = timeClosed;
	}

	/**
	 * Costructor.
	 * @param connection - sql connection to data base.
	 * When new connection just has been created, state
	 * inUse must be false.
	 * @throws SQLException
	 */
	public ConnectionFromPool(java.sql.Connection connection) 
			throws SQLException {
		this.connection = connection;
		this.inUse = false;
		this.connection.setAutoCommit(false);
	}
	
	/**
	 * Try to use this connection.
	 * If use connection, set the time of connection opening.
	 * @return 
	 * 		false if current connection is in using right now;
	 * 		else true, use connection.
	 */
	public boolean use() {
		if (this.inUse) {
			return false;
		} else {
			try {
				if (!this.connection.isClosed() && 
					(this.connection.getWarnings() == null)) {
					this.inUse = true;
					this.timeOpened = System.currentTimeMillis();
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				logger.getExceptionTextFileLogger().error(e);
				return false;
			}
		}
	}
	
	/**
	 * Try to make connection expired;
	 * set connection object null.
	 */
	public void expire() {
		try {
			this.connection.close();
			this.connection = null;
		} catch (SQLException e) { }
	}

	/**
	 * If connection in use right now try to close it,
	 * set the last time of closing. 
	 * @throws SQLException
	 */
	public void close() throws SQLException  {
		if (this.inUse) {
			this.timeClosed = System.currentTimeMillis();
			this.inUse = false;
			
			if (getAutoCommit() == false) {
				setAutoCommit(true);
			}			
		}
	}
	
	public Statement createStatement() throws SQLException {
		return (Statement) this.connection.createStatement();
	}
	
	public PreparedStatement prepareStatement(String query) throws SQLException {
		return (PreparedStatement) this.connection.prepareStatement(query);
	}
	
	public void commit() throws SQLException {
		this.connection.commit();
	}
	
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		this.connection.setAutoCommit(autoCommit);
	}
	
	public boolean getAutoCommit() throws SQLException {
		return this.connection.getAutoCommit();
	}
	
	public void rollback() throws SQLException {
		this.connection.rollback();
	}
	
	public boolean isClosed() throws SQLException {
		return this.connection.isClosed();
	}
	
	public SQLWarning getWarnings() throws SQLException {
		return this.connection.getWarnings();
	}

}
