/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;
import com.travel.web.enums.CrudAction;

/**
 * @author dima
 *
 */
public class TourService extends MyAbstractWebService<TourDao>
{
	@Override
	public TourDao createDao()
	{
		return new TourDao();
	}
	
	@Override
	public void loadDetailItemsForSingleView(HttpServletRequest request) throws DbSqlException
	{
		Tour tour = (Tour) request.getAttribute("tour");
		TourSheduleDao tourSheduleDao = new TourSheduleDao();
		request.setAttribute("shedules", tourSheduleDao.findTourShedules(tour.getId()));
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
