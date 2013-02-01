/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.OrderDao;
import com.travel.dao.TourSheduleDao;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.TourShedule;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class TourSheduleService extends MyAbstractWebService<TourShedule>
{
	@Override
	public TourSheduleDao createDao()
	{
		return new TourSheduleDao();
	}
	@Override
	protected String getParamNameForSingleItemJsp()
	{
		return "tourShedule";
	}
	
	@Override
	public void loadDetailItemsForSingleView(HttpServletRequest request) throws DbSqlException
	{
		TourShedule shedule = (TourShedule) request.getAttribute("tourShedule");
		request.setAttribute("orders", new OrderDao().findOrdersByShedule(shedule.getId()));
	}

	@Override
	public boolean hasRights(CrudAction crudAction)
	{
		switch (crudAction) {
		case CREATE: return getServicesContainer().isUserAdmin();
		case DELETE: return getServicesContainer().isUserAdmin();
		case UPDATE: return getServicesContainer().isUserAdmin();
		case READ: return true;
		default:
			return true;
		}
	}
}
