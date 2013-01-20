/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.BaseDao;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.SaveException;
import com.travel.pojo.BaseEntity;
import com.travel.web.enums.CrudAction;
import com.travel.web.utils.ServicesContainer;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 *
 */
public abstract class MyAbstractWebService<T extends BaseEntity>
{
	abstract public BaseDao<? extends T> createDao();
	private ServicesContainer servicesContainer;
	public abstract boolean hasRights(CrudAction crudAction);
	public void setParamsForTableView(HttpServletRequest request) throws DbSqlException
	{
		request.setAttribute("items", createDao().findAll());
	}
	protected Object findItemById(long id) throws DbSqlException
	{
		return createDao().findById(id);
	}
	protected String getParamNameForSingleItemJsp()
	{
		return TravelConsts.evalSingleItem(servicesContainer.getTableName());
	}
	public void setParamsForSingleView(HttpServletRequest request, String sItemId) throws DbSqlException
	{
		String paramName = getParamNameForSingleItemJsp();
		long id = Long.parseLong(sItemId);
		request.setAttribute(paramName, findItemById(id));
		request.setAttribute("entityid", id);
	}
	public void loadDetailItemsForSingleView(HttpServletRequest request)throws DbSqlException
	{
		//by default item has no detail items 
	}
	protected long getMasterIdParamFromRequest(HttpServletRequest request)
	{
		String sMasterId = request.getParameter("masterId");
		if (sMasterId == null)
			return 0;
		else
			return Long.parseLong(sMasterId);
	}
	public void setParamsForNewItem(HttpServletRequest request) throws DbSqlException
	{
		long clientId = getMasterIdParamFromRequest(request);
		request.setAttribute(getParamNameForSingleItemJsp(), createDao().newEntity());
		request.setAttribute("isNew", true);
		request.setAttribute("masterId", clientId);
		request.setAttribute("entityid", -1);
		
		
	}
	public void setParamsForEditItem(HttpServletRequest request) throws DbSqlException
	{
		request.setAttribute("isNew", false);
	}
	/**
	 * @return the daoDescription
	 */
	public ServicesContainer getServicesContainer()
	{
		return servicesContainer;
	}
	/**
	 * @param daoDescription the daoDescription to set
	 */
	public void setServiceContainer(ServicesContainer servicesContainer)
	{
		this.servicesContainer = servicesContainer;
	}
	public void checkCanDeleteRecord(long id) throws DeleteException
	{
		//default do nothing 
	}
	public void validateNewItem(T item) throws SaveException, DbSqlException
	{
		//default do nothing 
	}
	public void validateSavedItem(T item) throws DbSqlException, SaveException
	{
		//default do nothing 
	}
}
