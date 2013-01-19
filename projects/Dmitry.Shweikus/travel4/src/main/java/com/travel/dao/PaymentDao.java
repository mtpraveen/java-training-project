/**
 * 
 */
package com.travel.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.travel.dao.utils.SqlConstrainBuilder;
import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
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

	public Payment create(Order order, BigDecimal amount, Date date) throws DbSqlException
	{
		return createConcrete(new Object[] { order.getId(), amount, date });
	}
	
	public List<Payment> findOrdersPayments(long orderId) throws DbSqlException
	{
		SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
		constrainBuilder.addConstrainWithId(new OrderDao(), new PaymentDao(), orderId);
		return findAllWithCondition(constrainBuilder.build(), null);
	}
	
	public double findClientTotalPayments(long clientId) throws DbSqlException
	{
		Connection connection = ConnectionManager.getConnection();
		SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
		constrainBuilder.addConstrainWithId(new ClientDao(), new OrderDao(), clientId);
		OrderDao orderDao = new OrderDao();
		String subSql = "SELECT id FROM " + orderDao.getTableName() + " WHERE " + constrainBuilder.build();
		String sql = "SELECT SUM(amount) FROM " + getTableName() + " WHERE id_order in (" + subSql + ")";
		System.out.println(sql);
		Statement statement;
		ResultSet rs = null;
		try
		{
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			if (rs.next())
			{
				return rs.getDouble(1);
			}
			return 0;
		} catch (SQLException e)
		{
			throw new DbSqlException(e);
		}
		finally
		{
			try
			{
				if (rs!=null)
					rs.close();
				connection.close();
			} catch (SQLException e)
			{
				//do nothings here
			}
		}
	}
}
