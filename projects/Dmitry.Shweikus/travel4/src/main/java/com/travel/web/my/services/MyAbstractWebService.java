/**
 * 
 */
package com.travel.web.my.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.BaseDao;
import com.travel.db.ApplicationException;
import com.travel.web.utils.DaoDescription;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 *
 */
public abstract class MyAbstractWebService<T extends BaseDao>
{
	abstract public T createDao();
	private DaoDescription daoDescription;
	public void setShowItems(HttpServletRequest request) throws ApplicationException
	{
		request.setAttribute("items", createDao().findAll());
	}
	protected Object getViewItemById(long id) throws ApplicationException
	{
		return createDao().findById(id);
	}
	protected String getViewItemParamName()
	{
		return TravelConsts.evalSingleItem(daoDescription.getTableName());
	}
	public void setViewEditItem(HttpServletRequest request, String sItemId) throws ApplicationException
	{
		String paramName = getViewItemParamName();
		long id = Long.parseLong(sItemId);
		request.setAttribute(paramName, getViewItemById(id));
		request.setAttribute("entityid", id);
	}
	public void setNewItem(HttpServletRequest request, String sItemId) throws ApplicationException
	{
		long id = Long.parseLong(sItemId);
		request.setAttribute(getViewItemParamName(), createDao().newEntity());
		request.setAttribute("isNew", true);
		request.setAttribute("masterId", id);
		request.setAttribute("entityid", -1);
	}
	public void setEditParams(HttpServletRequest request)
	{
		request.setAttribute("isNew", false);
	}
	/**
	 * @return the daoDescription
	 */
	public DaoDescription getDaoDescription()
	{
		return daoDescription;
	}
	/**
	 * @param daoDescription the daoDescription to set
	 */
	public void setDaoDescription(DaoDescription daoDescription)
	{
		this.daoDescription = daoDescription;
	}
}
