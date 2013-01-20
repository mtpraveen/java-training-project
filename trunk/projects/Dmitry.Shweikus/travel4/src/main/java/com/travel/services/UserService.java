/**
 * 
 */
package com.travel.services;

import com.travel.dao.UserDao;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.SaveException;
import com.travel.pojo.BaseEntity;
import com.travel.pojo.User;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class UserService extends MyAbstractWebService<User>
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
	
	@Override
	public void validateNewItem(User item) throws SaveException, DbSqlException
	{
		UserDao userDao = new UserDao();
		if (userDao.findUsersByLogin(item.getLogin()).size() > 0)
			throw new SaveException("User with login " + item.getLogin() + " allready exists");
	}
	
	@Override
	public void validateSavedItem(User item) throws DbSqlException, SaveException
	{
		UserDao userDao = new UserDao();
		for (User user : userDao.findUsersByLogin(item.getLogin()))
				if (user.getId() != item.getId())
					throw new SaveException("User with login " + item.getLogin() + " allready exists");
	} 
}
