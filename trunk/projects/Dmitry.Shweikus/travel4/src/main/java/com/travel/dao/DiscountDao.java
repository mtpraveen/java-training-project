/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;
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
	public Class<Discount> getEntityClass()
	{
		return Discount.class;
	}

	public Discount create(double threshold, int percent, boolean active) throws DbSqlException
	{
		Discount discount = new Discount();
		discount.setActive(active);
		discount.setPercent(percent);
		discount.setThreshold(threshold);
		return create(discount);
	}
}
