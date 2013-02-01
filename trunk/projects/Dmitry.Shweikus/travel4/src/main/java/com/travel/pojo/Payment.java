/**
 * 
 */
package com.travel.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author dima
 *
 */
@Entity
@Table(name="payments")
@NamedQueries(value = { @NamedQuery(name = "Payment.findByOrderId", 
						  query = "SELECT x FROM Payment x WHERE x.order.id = :id"),
						@NamedQuery(name = "Payment.findClientTotalPayments", 
						  query = "SELECT SUM(x.amount) FROM Payment x WHERE x.order.client.id = :id") })
public class Payment extends BaseEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderId")
	@NotNull
	private Order order;
	@NotNull
	@Min(value=0)
	private double amount;
	@NotNull
	private Date date;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Payment))
			return false;
		Payment other = (Payment) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null)
		{
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (order == null)
		{
			if (other.order != null)
				return false;
		}
		else if (!order.equals(other.order))
			return false;
		return true;
	}
	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}
	/**
	 * @return the order
	 */
	public Order getOrder()
	{
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order)
	{
		this.order = order;
	}
	/**
	 * @return the amount
	 */
	public double getAmount()
	{
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
}
