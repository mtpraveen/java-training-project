/**
 * 
 */
package com.travel.web.servlets.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.InvalidRequest;
import com.travel.web.enums.CrudAction;
import com.travel.web.enums.RequestMethod;
import com.travel.web.utils.ServicesContainer;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 *
 */
public class ViewAction extends AbstractAction
{
	private String tableName = "";
	private String jspActionPrefix = null;
	private String jspActionTable = null;
	private ServicesContainer container;
	private CrudAction crudAction;
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws InvalidRequest, DbSqlException
	{
		if (getPathParams().size() == 1)
			throw new InvalidRequest("Invalid params count(1)");
		jspActionTable = TravelConsts.evalSingleItem(tableName);
		jspActionPrefix = getPathParams().get(0);
		switch (getPathParams().get(0)) {
		case ActionNames.VIEW:
			if (getPathParams().size() < 3)
				throw new InvalidRequest("Invalid params count : " + getPathParams().size());
			container.getService().setParamsForSingleView(request, getPathParams().get(2));
			container.getService().loadDetailItemsForSingleView(request);
			break;
		case ActionNames.EDIT: 
			if (getPathParams().size() < 3)
				throw new InvalidRequest("Invalid params count : " + getPathParams().size());
			container.getService().setParamsForSingleView(request, getPathParams().get(2));
			container.getService().setParamsForEditItem(request);
			break;
		case ActionNames.NEW: 
			jspActionPrefix = "edit";
			if (getPathParams().size() < 2)
				throw new InvalidRequest("Invalid params count : " + getPathParams().size());
			container.getService().setParamsForNewItem(request);
			break;
		case ActionNames.SHOW: 
			jspActionPrefix = "tables";
			container.getService().setParamsForTableView(request);
			break;
		}
	}
	@Override
	public String getJspTemplate()
	{
		return jspActionPrefix + "\\" + jspActionTable + ".jsp";
	}
	@Override
	public boolean userHasRights()
	{
		return container.getService().hasRights(crudAction);
	}
	@Override
	public void initParams(HttpServletRequest request, HttpServletResponse response) throws InvalidRequest
	{
		tableName = TravelConsts.findTableName(getPathParams().get(1));
		if(tableName == null)
			throw new InvalidRequest("Invalid table name " + tableName);
		container = TravelConsts.getServiceContainer(tableName,getUser());
		switch (getPathParams().get(0)) {
		case ActionNames.VIEW:
			crudAction = CrudAction.READ;
			break;
		case ActionNames.EDIT: 
			crudAction = CrudAction.UPDATE;
			break;
		case ActionNames.NEW: 
			crudAction = CrudAction.CREATE;
			break;
		case ActionNames.SHOW: 
			crudAction = CrudAction.READ;
			break;
		}
	}
	/* (non-Javadoc)
	 * @see com.travel.web.servlets.actions.AbstractAction#canProcessMethod(com.travel.web.enums.RequestMethod)
	 */
	@Override
	public boolean canProcessMethod(RequestMethod requestMethod)
	{
		if (crudAction == CrudAction.CREATE)
			return requestMethod == RequestMethod.POST;
		else
			return requestMethod == RequestMethod.GET;
	}
}
