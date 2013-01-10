/**
 * 
 */
package com.travel.web.beans;

import java.util.List;

import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.utils.ManyToOneConditionConstrainBuilder;
import com.travel.db.ApplicationException;

/**
 * @author dima
 *
 */
public class TourShedulesProviderBean extends DataProviderBean
{
	public List getTours() throws ApplicationException
	{
		setDao(new TourDao());
		setDao2(new TourSheduleDao());
		ManyToOneConditionConstrainBuilder constrainBuilder = new ManyToOneConditionConstrainBuilder();
		constrainBuilder.addConstrain(new TourDao(), new TourSheduleDao());
		setCustomConstrains(constrainBuilder.build());
		return getRecords();
	}
}
