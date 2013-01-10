/**
 * 
 */
package com.travel.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.db.ApplicationException;
import com.travel.pojo.Order;
import com.travel.pojo.Payment;

/**
 * @author dima
 * 
 */
public class PaymentDao extends BaseDao<Payment>
{
	private final String[] columns = new String[] { "id_order", "amount", "date" };

	@Override
	public String getTableName()
	{
		return "payments";
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	@Override
	public Payment newEntity()
	{
		return new Payment();
	}

	@Override
	public void readDataFromResultSet(Payment obj, ResultSet rs) throws SQLException
	{
		obj.setOrderId(rs.getLong(getColumn(0)));
		obj.setAmount(rs.getBigDecimal(getColumn(1)));
		obj.setDate(rs.getDate(getColumn(2)));
	}

	@Override
	public void saveDataToPreparedStatement(Payment obj, PreparedStatement ps)
			throws SQLException
	{
		ps.setLong(1, obj.getOrderId());
		ps.setBigDecimal(2, obj.getAmount());
		ps.setDate(3, obj.getDate());
	}

	public Payment create(Order order, BigDecimal amount, Date date) throws ApplicationException
	{
		return createConcrete(new Object[] { order.getId(), amount, date });
	}
}
