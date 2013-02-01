/**
 * 
 */
package com.travel.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	private final String[] columns = new String[] { "orderId", "amount", "date" };

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

	public Payment create(Order order, double amount, Date date) throws DbSqlException
	{
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setDate(date);
		payment.setOrder(order);
		return create(payment);
	}

	public List<Payment> findOrdersPayments(long orderId) throws DbSqlException
	{
		return ConnectionManager.getEntityManager().createNamedQuery("Payment.findByOrderId")
				.setParameter("id", orderId).getResultList();
	}

	public double findClientTotalPayments(long clientId) throws DbSqlException
	{
		Query query = ConnectionManager.getEntityManager().createNamedQuery("Payment.findClientTotalPayments");
		Double total = (Double)query.setParameter("id", clientId).getSingleResult();
		return (total == null)?0:total;
	}

	@Override
	public Class<Payment> getEntityClass()
	{
		return Payment.class;
	}
}
