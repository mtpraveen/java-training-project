/**
 * 
 */
package com.travel.services;

import com.travel.dao.UserDao;
import com.travel.exceptions.DeleteException;
import com.travel.web.enums.CrudAction;

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
	
	/* (non-Javadoc)
	 * @see com.travel.services.MyAbstractWebService#checkCanDeleteRecord(long)
	 */
	@Override
	public void checkCanDeleteRecord(long id) throws DeleteException
	{
		if (id == getServicesContainer().getUser().getId())
			throw new DeleteException("You cannot delete self");
	}
}
