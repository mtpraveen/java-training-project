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

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.travel.db.ConnectionManager;
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
	private final String[] columns = new String[] { "clientId", "tourSheduleId", "userId", "date",
			"count", "totalPrice", "description", "finished", "finishedDate" };

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

	public Order create(Client client, TourShedule arrivalShedule, User user, Date date, int count,
			double total_price, String description, boolean finished, Date finish_date)
			throws DbSqlException
	{
		Order order = new Order();
		order.setClient(client);
		order.setTourShedule(arrivalShedule);
		order.setCount(count);
		order.setDate(date);
		order.setDescription(description);
		order.setFinished(finished);
		order.setFinishedDate(finish_date);
		order.setTotalPrice(total_price);
		order.setUser(user);
		return create(order);
	}

	public List<Order> findOrdersByShedule(long sheduleId)
	{
		return ConnectionManager.getEntityManager().createNamedQuery("Orders.getByTourShedule")
				.setParameter("id", sheduleId).getResultList();
	}

	public List<Order> findOrdersByClient(long clientId)
	{
		return ConnectionManager.getEntityManager().createNamedQuery("Orders.getByClient")
				.setParameter("id", clientId).getResultList();
	}

	public List<Order> findOrdersByUser(long userId)
	{
		return ConnectionManager.getEntityManager().createNamedQuery("Orders.getByUser")
				.setParameter("id", userId).getResultList();
	}

	@Override
	public Class<Order> getEntityClass()
	{
		return Order.class;
	}
}
