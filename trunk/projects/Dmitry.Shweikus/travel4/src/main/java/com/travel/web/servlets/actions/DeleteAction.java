/**
 * 
 */
package com.travel.web.servlets.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.BaseDao;
import com.travel.dao.OrderDao;
import com.travel.dao.PaymentDao;
import com.travel.dao.TourSheduleDao;
import com.travel.exceptions.DbSqlException;
import com.travel.exceptions.DeleteException;
import com.travel.exceptions.InvalidRequest;
import com.travel.web.enums.CrudAction;
import com.travel.web.enums.RequestMethod;
import com.travel.web.utils.ServicesContainer;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 *
 */
public class DeleteAction extends AbstractAction
{
	ServicesContainer servicesContainer;
    private abstract class EntityDeleter
    {
    	protected BaseDao getSingleDetailDao()
    	{
    		return null;
    	}
    	protected List<BaseDao> getMultipleDetailDao()
    	{
    		return null;
    	}
    	public List<BaseDao> getDetailDao()
    	{
    		List<BaseDao> details = new ArrayList<>();
    		BaseDao single = getSingleDetailDao();
    		if(single != null)
    			details.add(single);
    		List<BaseDao> multiple = getMultipleDetailDao();
    		if (multiple != null)
    			details.addAll(multiple);
    		return details;
    	}
    }
    private EntityDeleter getDeleter(HttpServletRequest request)
    {
    	String table = getPathParams().get(1);
    	switch (table) {
		case TravelConsts.CLIENTS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new OrderDao();
				}
			};
		case TravelConsts.DISCOUNTS_TABLE:
			return new EntityDeleter() {
			};
		case TravelConsts.ORDERS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new PaymentDao();
				}
			};
		case TravelConsts.PAYMENTS_TABLE:
			return new EntityDeleter() {
			};
		case TravelConsts.SHEDULES_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new OrderDao();
				}
			};
		case TravelConsts.TOURS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new TourSheduleDao();
				}
			};
		case TravelConsts.USERS_TABLE:
			return new EntityDeleter() {
				@Override
				protected BaseDao getSingleDetailDao()
				{
					return new OrderDao();
				}
			};
		default:
			return null;
		}
    }
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InvalidRequest, DbSqlException, DeleteException
	{
		if (servicesContainer == null)
			throw new InvalidRequest("Invalid table : " + getPathParams().get(1));
		EntityDeleter deleter = getDeleter(request);
		long id = Long.parseLong(getPathParams().get(2));
		BaseDao dao = servicesContainer.getService().createDao();
		servicesContainer.getService().checkCanDeleteRecord(id);
		try
		{
			dao.delete(id);
		}
		catch(Exception e)
		{
			throw new DeleteException("Cannot delete item. Item has childs in other tables");
		}
		
		sendRedirect("index",request, response);
	}

	@Override
	public boolean userHasRights()
	{
		return servicesContainer.getService().hasRights(CrudAction.DELETE);
	}
	@Override
	public String getJspTemplate()
	{
		return "";
	}
	@Override
	public void initParams(HttpServletRequest request, HttpServletResponse response) throws InvalidRequest
	{
		if (getPathParams().size() < 3)
			throw new InvalidRequest("Invalid delete params count : " + getPathParams().size());
		servicesContainer = TravelConsts.getServiceContainer(getPathParams().get(1),getUser());
	}

	@Override
	public boolean canProcessMethod(RequestMethod requestMethod)
	{
		return requestMethod == RequestMethod.GET;
	}

}
