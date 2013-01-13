/**
 * 
 */
package com.travel.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.extenders.OrdersDaoExtender;
import com.travel.dao.extenders.OrdersExtender;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;

/**
 * @author dima
 *
 */
public class TourSheduleService extends MyAbstractWebService<TourSheduleDao>
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
	public void setParamsForSingleView(HttpServletRequest request, String sItemId) throws DbSqlException
	{
		super.setParamsForSingleView(request, sItemId);
		TourShedule tourShedule = (TourShedule) request.getAttribute(getParamNameForSingleItemJsp());
		TourDao tourDao = new TourDao();
		Tour tour = tourDao.findById(tourShedule.getTourId());
		request.setAttribute("tour", tour);
	}
	
	@Override
	public void loadDetailItemsForSingleView(HttpServletRequest request) throws DbSqlException
	{
		TourShedule shedule = (TourShedule) request.getAttribute("tourShedule");
		OrdersDaoExtender ordersDaoExtender = new OrdersDaoExtender();
		request.setAttribute("orders", ordersDaoExtender.findSheduleOrders(shedule.getId()));
	}
}
