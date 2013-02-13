package com.epam.logic.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.logic.Logger;

/**
 * @author EXHUMLOKI
 * Pool for connections.
 * Pool has the set of data base connections.
 * When need new connection, pool search for free 
 * existing connection, if such exists, return it.
 * If its one not exists, create new connection.
 * If connection idle by maxIdleTime or its use more
 * than maxUseTime, its expired by PoolCleaner. 
 */
public class ConnectionsPool {

	Logger logger = new Logger(org.apache.log4j.Logger.getLogger(ConnectionsPool.class.getName()));

	private static ConnectionsPool INSTANCE = null; 
	private ArrayList<ConnectionFromPool> connections;
	private String driverName;
	private String url;
	private String username;
	private String password;
	private long cleaningInterval;
	private long maxIdleTime;
	private boolean draining; // state of pool: empty of not
	private PoolCleaner poolCleaner;

	private ConnectionsPool(String url, String driverName, String username, String password) {
		this.url = url;
		this.driverName = driverName;
		this.username = username;
		this.password = password;
		this.connections = new ArrayList<>();
		cleaningInterval = 10 * 000; // 10 seconds interval for cleaning
		maxIdleTime = 10 * 000; // 10 seconds interval for idle connection
		draining = false; // if true - pool is empty; false otherwise
	}

	public static ConnectionsPool getInstance(String url, String driverName, String username, String password) {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionsPool(url, driverName, username, password);
		}
		return INSTANCE;
	}

	/**
	 * Search and return free healthy existing connection
	 * from connections list or crate new one.
	 * @return
	 */
	public java.sql.Connection createConnection() {
		try {
			if (this.draining) {
				throw new SQLException("Connections poll was drained.");
			}

			// Try reusing an existing connection.
			synchronized (connections) {
				ConnectionFromPool connectionFomPool = null;

				for (ConnectionFromPool conn : this.connections) {
					connectionFomPool = conn;

					if (connectionFomPool.use()) {
						return connectionFomPool;
					} else {
						boolean isHealthy = true;

						try {
							if (connectionFomPool.isClosed() && connectionFomPool.getWarnings() != null) {
								isHealthy = false;
							}
						} catch (SQLException e) {
							isHealthy = false;
							logger.getExceptionTextFileLogger().error(e);
						}

						if (isHealthy) {
							return connectionFomPool;
						} else {
							connectionFomPool.expire();
							connections.remove(connectionFomPool);
						}
					}
				}
			}

			com.epam.logic.database.ConnectionFromPool connectionFromPool = null;
			try {
				Class.forName(this.driverName); // set the driver
				java.sql.Connection sqlConnection = DriverManager.getConnection(this.url, this.username, this.password);
				connectionFromPool = new ConnectionFromPool(sqlConnection);
				connectionFromPool.use();

				synchronized (connections) {
					this.connections.add(connectionFromPool);

					if (this.poolCleaner == null) {
						this.poolCleaner = new PoolCleaner(cleaningInterval);
						this.poolCleaner.start();
					}
				}
			} catch (SQLException e) {
				logger.getExceptionTextFileLogger().error(e);
			}  

			return connectionFromPool;
		} catch(SQLException | ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Remove unavailable connections.
	 */
	public void removeExpired() {
		ConnectionFromPool connection = null;
		long maxIdleDeadLine = System.currentTimeMillis() - this.maxIdleTime;

		synchronized (connections) {
			for (int i = this.connections.size() - 1; i >= 0; i--) {
				connection = connections.get(i); 

				if (!connection.isInUse() && 
					(connection.getTimeClosed() < maxIdleDeadLine)) {
					connection.expire();
					connections.remove(i);
				}
			}			
		}

		// Stop the PoolCleaner if the pool is empty.
		if (this.connections.isEmpty() && this.poolCleaner != null) {
			this.poolCleaner.toStop();
			this.poolCleaner = null;
		}
	}

	/*
	 * Inner class 'PoolCleaner'.
	 */
	class PoolCleaner extends Thread {

		private long cleaningInterval;
		private boolean mustStop;

		public PoolCleaner(long cleaningInterval) {
			this.mustStop = false;
			this.cleaningInterval = cleaningInterval;
			setDaemon(true);
		}

		@Override
		public void run() {
			while(!this.mustStop) {
				try {
					sleep(this.cleaningInterval);
				} catch (InterruptedException exception) { }

				if (this.mustStop) {
					break;
				}
				removeExpired();
			}
		}

		/**
		 * Stop pool cleaner.
		 * Interrupt current thread.
		 */
		public void toStop() {
			this.mustStop = true;
			synchronized (this) {
				this.interrupt();
			}
		}		
	}

}
