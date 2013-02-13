package com.epam.logic.database;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

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
public class ConnectionFromPool implements java.sql.Connection {
	
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
	public synchronized boolean use() {
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

	@Override
	/**
	 * If connection in use right now try to close it,
	 * set the last time of closing. 
	 * @throws SQLException 
	 */
	public synchronized void close() throws SQLException  {
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

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void abort(Executor arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.Statement createStatement(int arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.Statement createStatement(int arg0, int arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCatalog() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClientInfo(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String nativeSQL(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallableStatement prepareCall(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallableStatement prepareCall(String arg0, int arg1, int arg2)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallableStatement prepareCall(String arg0, int arg1, int arg2,
			int arg3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.PreparedStatement prepareStatement(String arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.PreparedStatement prepareStatement(String arg0, int[] arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.PreparedStatement prepareStatement(String arg0,
			String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.PreparedStatement prepareStatement(String arg0, int arg1,
			int arg2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.PreparedStatement prepareStatement(String arg0, int arg1,
			int arg2, int arg3) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback(Savepoint arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCatalog(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClientInfo(String arg0, String arg1)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHoldability(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReadOnly(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Savepoint setSavepoint(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSchema(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTransactionIsolation(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
