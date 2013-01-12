/**
 * 
 */
package com.travel.dao.extenders;

import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;

/**
 * @author dima
 *
 */
public class TourSheduleExtender extends AbstractExtender
{
	private TourShedule shedule;
	private Tour tour;
	/**
	 * @return the tourShedule
	 */
	public TourShedule getShedule()
	{
		return shedule;
	}
	/**
	 * @param tourShedule the tourShedule to set
	 */
	public void setShedule(TourShedule tourShedule)
	{
		this.shedule = tourShedule;
	}
	/**
	 * @return the tour
	 */
	public Tour getTour()
	{
		return tour;
	}
	/**
	 * @param tour the tour to set
	 */
	public void setTour(Tour tour)
	{
		this.tour = tour;
	}
}
