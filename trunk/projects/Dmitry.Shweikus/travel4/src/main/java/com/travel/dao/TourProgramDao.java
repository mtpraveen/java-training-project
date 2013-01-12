/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;
import com.travel.pojo.TourProgram;

/**
 * @author dima
 *
 */
public class TourProgramDao extends BaseDao<TourProgram>
{
	private final String[] columns = new String[]{"id_tour","day_number","description","last_day_number"};
 	@Override
	public String getTableName()
	{
		return "tour_programs";
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	@Override
	public TourProgram newEntity()
	{
		return new TourProgram();
	}

	@Override
	public void readDataFromResultSet(TourProgram obj, ResultSet rs) throws SQLException
	{
		obj.setTourId(rs.getLong(getColumn(0)));
		obj.setDayNumber(rs.getInt(getColumn(1)));
		obj.setDescription(rs.getString(getColumn(2)));
		obj.setLastDayNumber(rs.getInt(getColumn(3)));
	}

	@Override
	public void saveDataToPreparedStatement(TourProgram obj, PreparedStatement ps)
			throws SQLException
	{
		ps.setLong(1, obj.getTourId());
		ps.setInt(2, obj.getDayNumber());
		ps.setString(3, obj.getDescription());
		ps.setInt(4, obj.getLastDayNumber());
	}
	
	public TourProgram create(Tour tour,int day_number, String description, int last_day_number) throws DbSqlException
	{
		return createConcrete(new Object[]{tour.getId(),day_number,description,last_day_number});
	}
	

}
