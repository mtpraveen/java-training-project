/**
 * 
 */
package com.travel.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;
import com.travel.pojo.Order;
import com.travel.pojo.TourShedule;
import com.travel.pojo.User;

/**
 * @author dima
 * 
 */
public class OrderDao extends BaseDao<Order>
{
	private final String[] columns = new String[] { "id_client", "id_tourshedule", "id_user",
			"date", "count", "total_price", "description", "finished", "finish_date" };

	@Override
	public String getTableName()
	{
		return "orders";
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	@Override
	public Order newEntity()
	{
		return new Order();
	}

	@Override
	public void readDataFromResultSet(Order obj, ResultSet rs) throws SQLException
	{
		obj.setClientId(rs.getLong(getColumn(0)));
		obj.setArrivalId(rs.getLong(getColumn(1)));
		obj.setUserId(rs.getLong(getColumn(2)));
		obj.setDate(rs.getDate(getColumn(3)));
		obj.setCount(rs.getInt(getColumn(4)));
		obj.setTotalPrice(rs.getBigDecimal(getColumn(5)));
		obj.setDescription(rs.getString(getColumn(6)));
		obj.setFinished(rs.getBoolean(getColumn(7)));
		obj.setFinishedDate(rs.getDate(getColumn(8)));
	}

	@Override
	public void saveDataToPreparedStatement(Order obj, PreparedStatement ps) throws SQLException
	{
		ps.setLong(1, obj.getClientId());
		ps.setLong(2, obj.getArrivalId());
		ps.setLong(3, obj.getUserId());
		ps.setDate(4, obj.getDate());
		ps.setInt(5, obj.getCount());
		ps.setBigDecimal(6, obj.getTotalPrice());
		ps.setString(7, obj.getDescription());
		ps.setBoolean(8, obj.isFinished());
		ps.setDate(9, obj.getFinishedDate());
	}

	public Order create(Client client, TourShedule arrivalShedule, User user, Date date,
			int count, BigDecimal total_price, String description, boolean finished,
			Date finish_date) throws DbSqlException
	{
		return createConcrete(new Object[] { client.getId(), arrivalShedule.getId(), user.getId(),
				date, count, total_price, description, finished, finish_date });
	}
}
