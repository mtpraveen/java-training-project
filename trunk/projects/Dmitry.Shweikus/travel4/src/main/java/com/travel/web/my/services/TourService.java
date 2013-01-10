/**
 * 
 */
package com.travel.web.my.services;

import com.travel.dao.TourDao;

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
}
