package com.epam.logic.database;

import com.epam.logic.Resource;

public abstract class AbstractDataBaseWorker {
	
	private Resource resource;
	private String url;
	private String driverName;
	private String username;
	private String password;
	private ConnectionsPool pool;
	
	public AbstractDataBaseWorker() {
		this.resource = new Resource("dataBaseProperties");
		this.url = String.format("%s//%s/%s", resource.getValue("url"), resource.getValue("serverName"), resource.getValue("dataBaseName"));
		this.driverName = resource.getValue("driverName");
		this.username = resource.getValue("userName");
		this.password = resource.getValue("password");
		this.pool = ConnectionsPool.getInstance(url, driverName, username, password);
	}
	
	protected ConnectionsPool getPool() {
		return this.pool;
	}

}
