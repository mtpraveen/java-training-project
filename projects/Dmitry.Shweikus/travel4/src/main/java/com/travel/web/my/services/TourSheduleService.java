/**
 * 
 */
package com.travel.web.my.services;

import javax.servlet.http.HttpServletRequest;

import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.db.ApplicationException;
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
	/* (non-Javadoc)
	 * @see com.travel.web.my.services.MyAbstractWebService#getViewItemParamName()
	 */
	@Override
	protected String getViewItemParamName()
	{
		return "tourShedule";
	}
	/* (non-Javadoc)
	 * @see com.travel.web.my.services.MyAbstractWebService#getViewItem(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public void setViewEditItem(HttpServletRequest request, String sItemId) throws ApplicationException
	{
		super.setViewEditItem(request, sItemId);
		TourShedule tourShedule = (TourShedule) request.getAttribute(getViewItemParamName());
		TourDao tourDao = new TourDao();
		Tour tour = tourDao.findById(tourShedule.getTourId());
		request.setAttribute("tour", tour);
	}
}
