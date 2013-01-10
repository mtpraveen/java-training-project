/**
 * 
 */
package com.travel.pojo;


/**
 * @author dima
 *
 */
public class Discount extends BaseEntity
{
	private double threshold;
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
		if (getClass() != obj.getClass())
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
