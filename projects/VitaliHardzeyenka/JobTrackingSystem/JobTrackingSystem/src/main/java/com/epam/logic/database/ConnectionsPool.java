package com.epam.logic.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionsPool {

	protected ArrayList<ConnectionFromPool> connections;
	private String driverName;
	private String url;
	private String username;
	private String password;
	private boolean checkConnections = true;
	private long cleaningInterval = 30000;
	private long maxIdleTime = 30000;
	private long maxUseTime = -1;
	private boolean draining = false;
	private PoolCleaner poolCleaner;

	public ConnectionsPool(String url, String driverName, String username, String password) {
		this.url = url;
		this.driverName = driverName;
		this.username = username;
		this.password = password;
		this.connections = new ArrayList<>();
	}

	public java.sql.Connection createConnection() throws SQLException, ClassNotFoundException {
		if (this.draining) {
			throw new SQLException("Connection Poll was drained.");
		}

		// Try reusing an existing connection.
		synchronized (connections) {
			ConnectionFromPool connectionFomPool = null;

			for (ConnectionFromPool conn : this.connections) {
				connectionFomPool = conn;

				if (connectionFomPool.use()) {
					if (!this.checkConnections) {
						return connectionFomPool;
					}
				} else {
					boolean isHealthy = true;

					try {
						if (connectionFomPool.isClosed() && connectionFomPool.getWarnings() != null) {
							isHealthy = false;
						}
					} catch (SQLException e) {
						isHealthy = false;
						// TODO  logger
						e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return connectionFromPool;
	}

	public void removeExpired() {
		ConnectionFromPool connection = null;
		long maxIdleDeadLine = System.currentTimeMillis() - this.maxIdleTime;
		long maxUseDeadLine = System.currentTimeMillis() - this.maxUseTime;

		synchronized (connections) {
			for (int i = this.connections.size() - 1; i >= 0; i--) {
				connection = connections.get(i); 

				if (!connection.isInUse() && 
						(connection.getTimeClosed() < maxIdleDeadLine)) {
					connection.expire();
					connections.remove(i);
				} else if (this.maxUseTime > 0 &&
						connection.isInUse() &&
						connection.getTimeOpened() < maxUseDeadLine) {
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
				} catch (InterruptedException exception) {
					// TODO logger
				}

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
