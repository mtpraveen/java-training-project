/**
 * 
 */
package com.travel.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

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
@Table(name = "orders")
@NamedQueries(value = { 
		@NamedQuery(name = "Orders.getByTourShedule", 
			    query = "SELECT x FROM Order x WHERE x.tourShedule.id = :id"),
		@NamedQuery(name = "Orders.getByClient", 
			    query = "SELECT x FROM Order x WHERE x.client.id = :id"),
		@NamedQuery(name = "Orders.getByUser", 
			    query = "SELECT x FROM Order x WHERE x.user.id = :id")
				    })
public class Order extends BaseEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId")
	@NotNull
	private Client client;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tourSheduleId")
	@NotNull
	private TourShedule tourShedule;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	@NotNull
	private User user;

	@NotNull
	private Date date;
	@Min(value = 1)
	private int count;
	@NotNull
	@Min(value = 0)
	private double totalPrice;
	private String description;
	private boolean finished;
	private Date finishedDate;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + count;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (finished ? 1231 : 1237);
		result = prime * result + ((finishedDate == null) ? 0 : finishedDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tourShedule == null) ? 0 : tourShedule.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (client == null)
		{
			if (other.client != null)
				return false;
		}
		else if (!client.equals(other.client))
			return false;
		if (count != other.count)
			return false;
		if (date == null)
		{
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (finished != other.finished)
			return false;
		if (finishedDate == null)
		{
			if (other.finishedDate != null)
				return false;
		}
		else if (!finishedDate.equals(other.finishedDate))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		if (tourShedule == null)
		{
			if (other.tourShedule != null)
				return false;
		}
		else if (!tourShedule.equals(other.tourShedule))
			return false;
		if (user == null)
		{
			if (other.user != null)
				return false;
		}
		else if (!user.equals(other.user))
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
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return the count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count)
	{
		this.count = count;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished()
	{
		return finished;
	}

	/**
	 * @param finished
	 *            the finished to set
	 */
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}

	/**
	 * @return the finishedDate
	 */
	public Date getFinishedDate()
	{
		return finishedDate;
	}

	/**
	 * @param finishedDate
	 *            the finishedDate to set
	 */
	public void setFinishedDate(Date finishedDate)
	{
		this.finishedDate = finishedDate;
	}

	/**
	 * @return the client
	 */
	public Client getClient()
	{
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client)
	{
		this.client = client;
	}

	/**
	 * @return the tourShedule
	 */
	public TourShedule getTourShedule()
	{
		return tourShedule;
	}

	/**
	 * @param tourShedule
	 *            the tourShedule to set
	 */
	public void setTourShedule(TourShedule tourShedule)
	{
		this.tourShedule = tourShedule;
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice()
	{
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice)
	{
		this.totalPrice = totalPrice;
	}
}
