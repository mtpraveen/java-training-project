/**
 * 
 */
package com.travel.pojo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author dima
 * tour has at least one ArrivalShedule
 */
public class TourShedule extends BaseEntity
{
	private long tourId;
	private Date date;
	private BigDecimal price;
	private int count;
	/**
	 * @return the tourId
	 */
	public long getTourId()
	{
		return tourId;
	}
	/**
	 * @param tourId the tourId to set
	 */
	public void setTourId(long tourId)
	{
		this.tourId = tourId;
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
	 * @return the price
	 */
	public BigDecimal getPrice()
	{
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + count;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (int) (tourId ^ (tourId >>> 32));
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
		TourShedule other = (TourShedule) obj;
		if (count != other.count)
			return false;
		if (date == null)
		{
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (price == null)
		{
			if (other.price != null)
				return false;
		}
		else if (!price.equals(other.price))
			return false;
		if (tourId != other.tourId)
			return false;
		return true;
	}
}
