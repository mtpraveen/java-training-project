/**
 * 
 */
package com.travel.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.travel.dao.utils.SqlConstrainBuilder;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;

/**
 * @author dima
 *
 */
public class TourSheduleDao extends BaseDao<TourShedule>
{
	private final String[] columns = new String[]{"id_tour","date","price","count"};
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

	@Override
	public TourShedule newEntity()
	{
		return new TourShedule();
	}

	@Override
	public void readDataFromResultSet(TourShedule obj, ResultSet rs) throws SQLException
	{
		obj.setTourId(rs.getLong(getColumn(0)));
		obj.setDate(rs.getDate(getColumn(1)));
		obj.setPrice(rs.getBigDecimal(getColumn(2)));
		obj.setCount(rs.getInt(getColumn(3)));
	}

	/* (non-Javadoc)
	 * @see com.travel.dao.BaseDao#saveDataToPreparedStatement(com.travel.pojo.BaseEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void saveDataToPreparedStatement(TourShedule obj, PreparedStatement ps)
			throws SQLException
	{
		ps.setLong(1, obj.getTourId());
		ps.setDate(2, obj.getDate());
		ps.setBigDecimal(3, obj.getPrice());
		ps.setInt(4, obj.getCount());
	}
	
	public TourShedule create(Tour tour, Date date, BigDecimal price, int count) throws DbSqlException
	{
		return createConcrete(new Object[]{tour.getId(), date, price, count});
	}
	
	public List<TourShedule> findTourShedules(long tourId) throws DbSqlException
	{
		SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
		constrainBuilder.addConstrainWithId(new TourDao(), new TourSheduleDao(), tourId);
		return findAllWithCondition(constrainBuilder.build(), null);
	}

}
