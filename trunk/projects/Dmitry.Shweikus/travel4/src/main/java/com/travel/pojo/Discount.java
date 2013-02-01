/**
 * 
 */
package com.travel.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;


/**
 * @author dima
 *
 */
@Entity
@Table(name="discounts")
public class Discount extends BaseEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Min(value=0)
	private double threshold;
	@Range(min=0, max=100)
	private int percent;
	private boolean active;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + percent;
		long temp;
		temp = Double.doubleToLongBits(threshold);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (!(obj instanceof Discount))
			return false;
		Discount other = (Discount) obj;
		if (active != other.active)
			return false;
		if (percent != other.percent)
			return false;
		if (Double.doubleToLongBits(threshold) != Double.doubleToLongBits(other.threshold))
			return false;
		return true;
	}
	/**
	 * @return the threshold
	 */
	public double getThreshold()
	{
		return threshold;
	}
	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(double threshold)
	{
		this.threshold = threshold;
	}
	/**
	 * @return the percent
	 */
	public int getPercent()
	{
		return percent;
	}
	/**
	 * @param percent the percent to set
	 */
	public void setPercent(int percent)
	{
		this.percent = percent;
	}
	/**
	 * @return the active
	 */
	public boolean isActive()
	{
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}
}
