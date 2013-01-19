/**
 * 
 */
package com.travel.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;

/**
 * @author dima
 *
 */
public class Tour extends BaseEntity
{
	@NotNull
	@Length(min=3, max=120)
	private String name;
	@NotNull
	private TravelKind travelKind;
	@NotNull
	private TransportKind transportKind;
	@NotNull
	@Length(min=3)
	private String description;
	@NotNull
	@Length(min=3)
	private String requiredDocuments;
	@Min(value=1)
	private int daysCount;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + daysCount;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((requiredDocuments == null) ? 0 : requiredDocuments.hashCode());
		result = prime * result + ((transportKind == null) ? 0 : transportKind.hashCode());
		result = prime * result + ((travelKind == null) ? 0 : travelKind.hashCode());
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
		Tour other = (Tour) obj;
		if (daysCount != other.daysCount)
			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (requiredDocuments == null)
		{
			if (other.requiredDocuments != null)
				return false;
		}
		else if (!requiredDocuments.equals(other.requiredDocuments))
			return false;
		if (transportKind != other.transportKind)
			return false;
		if (travelKind != other.travelKind)
			return false;
		return true;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the travelKind
	 */
	public TravelKind getTravelKind()
	{
		return travelKind;
	}
	/**
	 * @param travelKind the travelKind to set
	 */
	public void setTravelKind(TravelKind travelKind)
	{
		this.travelKind = travelKind;
	}
	/**
	 * @return the transportKind
	 */
	public TransportKind getTransportKind()
	{
		return transportKind;
	}
	/**
	 * @param transportKind the transportKind to set
	 */
	public void setTransportKind(TransportKind transportKind)
	{
		this.transportKind = transportKind;
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
	 * @return the requiredDocuments
	 */
	public String getRequiredDocuments()
	{
		return requiredDocuments;
	}
	/**
	 * @param requiredDocuments the requiredDocuments to set
	 */
	public void setRequiredDocuments(String requiredDocuments)
	{
		this.requiredDocuments = requiredDocuments;
	}
	/**
	 * @return the daysCount
	 */
	public int getDaysCount()
	{
		return daysCount;
	}
	/**
	 * @param daysCount the daysCount to set
	 */
	public void setDaysCount(int daysCount)
	{
		this.daysCount = daysCount;
	}
}
