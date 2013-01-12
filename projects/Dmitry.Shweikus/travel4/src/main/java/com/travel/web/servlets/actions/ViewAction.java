/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.InvalidRequest;
import com.travel.web.utils.ServicesContainer;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 *
 */
public class ViewAction extends AbstractAction
{
	private String jspTemplate = "";
	private String tableName = "";
	private String jspActionPrefix = null;
	private String jspActionTable = null;
	private Long id;
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws InvalidRequest, DbSqlException
	{
		if (getPathParams().size() == 1)
			throw new InvalidRequest("Invalid params count(1)");
		tableName = TravelConsts.findTableName(getPathParams().get(1));
		if(tableName == null)
			throw new InvalidRequest("Invalid table name " + tableName);
		ServicesContainer daoDescription = TravelConsts.getDaoDescription(tableName,getUser());
		jspActionTable = TravelConsts.evalSingleItem(tableName);
		jspActionPrefix = getPathParams().get(0);
		switch (getPathParams().get(0)) {
		case "view":
			if (getPathParams().size() < 3)
				throw new InvalidRequest("Invalid params count : " + getPathParams().size());
			daoDescription.getService().setViewEditItem(request, getPathParams().get(2));
			daoDescription.getService().setViewDetailItems(request);
			break;
		case "edit": 
			if (getPathParams().size() < 3)
				throw new InvalidRequest("Invalid params count : " + getPathParams().size());
			daoDescription.getService().setViewEditItem(request, getPathParams().get(2));
			daoDescription.getService().setEditParams(request);
			break;
		case "new": 
			jspActionPrefix = "edit";
			if (getPathParams().size() < 3)
				throw new InvalidRequest("Invalid params count : " + getPathParams().size());
			daoDescription.getService().setNewItem(request, getPathParams().get(2));
			break;
		case "show": 
			jspActionPrefix = "table";
			jspActionTable = tableName;
			daoDescription.getService().setShowItems(request);
			break;
		}
	}
	@Override
	public String getJspTemplate()
	{
		return jspActionPrefix + "_" + jspActionTable + ".jsp";
	}
}
