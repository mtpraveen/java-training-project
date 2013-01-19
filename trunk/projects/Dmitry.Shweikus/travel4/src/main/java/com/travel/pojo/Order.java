/**
 * 
 */
package com.travel.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author dima
 *
 */
public class Order extends BaseEntity
{
	private long clientId;
	private long arrivalId;
	private long userId;
	@NotNull
	private Date date;
	@Min(value=1)
	private int count;
	@NotNull
	@Min(value=0)
	private BigDecimal totalPrice;
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
		result = prime * result + (int) (arrivalId ^ (arrivalId >>> 32));
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + count;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (finished ? 1231 : 1237);
		result = prime * result + ((finishedDate == null) ? 0 : finishedDate.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (arrivalId != other.arrivalId)
			return false;
		if (clientId != other.clientId)
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
		if (totalPrice == null)
		{
			if (other.totalPrice != null)
				return false;
		}
		else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	/**
	 * @return the clientId
	 */
	public long getClientId()
	{
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(long clientId)
	{
		this.clientId = clientId;
	}
	/**
	 * @return the arrivalId
	 */
	public long getArrivalId()
	{
		return arrivalId;
	}
	/**
	 * @param arrivalId the arrivalId to set
	 */
	public void setArrivalId(long arrivalId)
	{
		this.arrivalId = arrivalId;
	}
	/**
	 * @return the userId
	 */
	public long getUserId()
	{
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId)
	{
		this.userId = userId;
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
	 * @return the count
	 */
	public int getCount()
	{
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count)
	{
		this.count = count;
	}
	/**
	 * @return the totalPrice
	 */
	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @param description the description to set
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
	 * @param finished the finished to set
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
	 * @param finishedDate the finishedDate to set
	 */
	public void setFinishedDate(Date finishedDate)
	{
		this.finishedDate = finishedDate;
	}
}
