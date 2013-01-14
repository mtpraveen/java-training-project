/**
 * 
 */
package com.travel.services;

import com.travel.dao.UserDao;
import com.travel.web.utils.CrudAction;

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
	protected String getParamNameForSingleItemJsp()
	{
		return "seluser";
	}
	/* (non-Javadoc)
	 * @see com.travel.services.MyAbstractWebService#hasRights(com.travel.web.utils.CrudAction)
	 */
	@Override
	public boolean hasRights(CrudAction crudAction)
	{
		return getServicesContainer().isUserAdmin();
	}
}
