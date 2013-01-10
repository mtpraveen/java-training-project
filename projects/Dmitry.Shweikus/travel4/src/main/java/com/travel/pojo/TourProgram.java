/**
 * 
 */
package com.travel.pojo;

/**
 * @author dima
 *
 */
public class TourProgram extends BaseEntity
{
	private long tourId;
	private int dayNumber;
	private String description;
	private int lastDayNumber;
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
	 * @return the dayNumber
	 */
	public int getDayNumber()
	{
		return dayNumber;
	}
	/**
	 * @param dayNumber the dayNumber to set
	 */
	public void setDayNumber(int dayNumber)
	{
		this.dayNumber = dayNumber;
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
	 * @return the lastDayNumber
	 */
	public int getLastDayNumber()
	{
		return lastDayNumber;
	}
	/**
	 * @param lastDayNumber the lastDayNumber to set
	 */
	public void setLastDayNumber(int lastDayNumber)
	{
		this.lastDayNumber = lastDayNumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + dayNumber;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + lastDayNumber;
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
		TourProgram other = (TourProgram) obj;
		if (dayNumber != other.dayNumber)
			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (lastDayNumber != other.lastDayNumber)
			return false;
		if (tourId != other.tourId)
			return false;
		return true;
	}
}
