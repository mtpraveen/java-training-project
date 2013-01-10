/**
 * 
 */
package com.travel.web.my.services;

import com.travel.dao.UserDao;

/**
 * @author dima
 *
 */
public class UserService extends MyAbstractWebService<UserDao>
{
	@Override
	public UserDao createDao()
	{
		return new UserDao();
	}
	@Override
	protected String getViewItemParamName()
	{
		return "seluser";
	}
}
