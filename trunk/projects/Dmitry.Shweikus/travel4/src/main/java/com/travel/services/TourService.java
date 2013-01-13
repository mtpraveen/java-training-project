/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;

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
}
