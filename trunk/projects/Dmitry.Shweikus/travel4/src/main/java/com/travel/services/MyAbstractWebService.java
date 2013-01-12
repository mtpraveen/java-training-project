/**
 * 
 */
package com.travel.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.BaseDao;
import com.travel.exceptions.DbSqlException;
import com.travel.web.utils.ServicesContainer;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 *
 */
public abstract class MyAbstractWebService<T extends BaseDao>
{
	abstract public T createDao();
	private ServicesContainer servicesDescription;
	public void setShowItems(HttpServletRequest request) throws DbSqlException
	{
		request.setAttribute("items", createDao().findAll());
	}
	protected Object getViewItemById(long id) throws DbSqlException
	{
		return createDao().findById(id);
	}
	protected String getViewItemParamName()
	{
		return TravelConsts.evalSingleItem(servicesDescription.getTableName());
	}
	public void setViewEditItem(HttpServletRequest request, String sItemId) throws DbSqlException
	{
		String paramName = getViewItemParamName();
		long id = Long.parseLong(sItemId);
		request.setAttribute(paramName, getViewItemById(id));
		request.setAttribute("entityid", id);
	}
	public void setViewDetailItems(HttpServletRequest request)throws DbSqlException
	{
		//by default item has no detail items 
	}
	public void setNewItem(HttpServletRequest request, String sItemId) throws DbSqlException
	{
		long id = Long.parseLong(sItemId);
		request.setAttribute(getViewItemParamName(), createDao().newEntity());
		request.setAttribute("isNew", true);
		request.setAttribute("masterId", id);
		request.setAttribute("entityid", -1);
	}
	public void setEditParams(HttpServletRequest request) throws DbSqlException
	{
		request.setAttribute("isNew", false);
	}
	/**
	 * @return the daoDescription
	 */
	public ServicesContainer getServicesContainer()
	{
		return servicesDescription;
	}
	/**
	 * @param daoDescription the daoDescription to set
	 */
	public void setDaoDescription(ServicesContainer daoDescription)
	{
		this.servicesDescription = daoDescription;
	}
}
