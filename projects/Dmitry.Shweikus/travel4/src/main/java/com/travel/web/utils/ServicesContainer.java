/**
 * 
 */
package com.travel.web.utils;

import com.travel.pojo.User;
import com.travel.services.MyAbstractWebService;

/**
 * @author dima
 *
 */
public class ServicesContainer
{
	MyAbstractWebService service;
	String tableName;
	User user;
	
	public ServicesContainer(MyAbstractWebService service,String tableName,User user) {
		super();
		this.service = service;
		this.tableName = tableName;
		this.user = user;
		service.setServiceContainer(this);
	}

	/**
	 * @return the service
	 */
	public MyAbstractWebService getService()
	{
		return service;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}
	
	public boolean isUserAdmin()
	{
		if (user != null)
			return user.isAdmin();
		return false;
	}
}
