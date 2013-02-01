/**
 * 
 */
package com.travel.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;

/**
 * @author dima
 * 
 */
public class TourSheduleDao extends BaseDao<TourShedule>
{
	private final String[] columns = new String[] { "tourId", "date", "price", "count" };

	@Override
	public String getTableName()
	{
		return "tourshedules";
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	public TourShedule create(Tour tour, Date date, double price, int count)
			throws DbSqlException
	{
		TourShedule tourShedule = new TourShedule();
		tourShedule.setCount(count);
		tourShedule.setDate(date);
		tourShedule.setPrice(price);
		tourShedule.setTour(tour);
		return create(tourShedule);
	}

	public List<TourShedule> findTourShedules(long tourId) throws DbSqlException
	{
		return ConnectionManager.getEntityManager().createNamedQuery("tourShedule.findByTourId")
				.setParameter("id", tourId).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.travel.dao.BaseDao#getEntityClass()
	 */
	@Override
	public Class<TourShedule> getEntityClass()
	{
		return TourShedule.class;
	}

}
