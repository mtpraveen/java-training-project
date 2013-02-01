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
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author dima
 * tour has at least one ArrivalShedule
 */
@Entity
@Table(name="tourshedules")
@NamedQueries(value = { @NamedQuery(name = "tourShedule.findByTourId", 
						 query = "SELECT x FROM TourShedule x WHERE x.tour.id = :id") })
public class TourShedule extends BaseEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tourId")
	@NotNull
	private Tour tour;
	@NotNull
	private Date date;
	@NotNull
	@Min(value=0)
	private double price;
	@Min(value=1)
	private int count;
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
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tour == null) ? 0 : tour.hashCode());
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
		if (!(obj instanceof TourShedule))
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
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (tour == null)
		{
			if (other.tour != null)
				return false;
		}
		else if (!tour.equals(other.tour))
			return false;
		return true;
	}
	/**
	 * @return the tour
	 */
	public Tour getTour()
	{
		return tour;
	}
	/**
	 * @param tour the tour to set
	 */
	public void setTour(Tour tour)
	{
		this.tour = tour;
	}
	/**
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}
}
