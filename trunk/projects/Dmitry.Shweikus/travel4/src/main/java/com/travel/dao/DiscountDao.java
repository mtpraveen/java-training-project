/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.travel.db.ApplicationException;
import com.travel.pojo.Discount;

/**
 * @author dima
 *
 */
public class DiscountDao extends BaseDao<Discount>
{
	private final String[] columns = new String[]{"threshold","percent","active"};
	@Override
	public String getTableName()
	{
		return "discounts";
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	@Override
	public Discount newEntity()
	{
		return new Discount();
	}

	@Override
	public void readDataFromResultSet(Discount obj, ResultSet rs) throws SQLException
	{
		obj.setThreshold(rs.getDouble(getColumn(0)));
		obj.setPercent(rs.getInt(getColumn(1)));
		obj.setActive(rs.getBoolean(getColumn(2)));
	}

	@Override
	public void saveDataToPreparedStatement(Discount obj, PreparedStatement ps)
			throws SQLException
	{
		ps.setDouble(1, obj.getThreshold());
		ps.setInt(2, obj.getPercent());
		ps.setBoolean(3, obj.isActive());
	}
	
	public Discount create(double threshold, int percent, boolean active) throws ApplicationException
	{
		return createConcrete(new Object[]{threshold,percent,active});
	}
}
