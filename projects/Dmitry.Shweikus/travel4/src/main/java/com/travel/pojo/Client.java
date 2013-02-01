/**
 * 
 */
package com.travel.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author dima
 * client of our travel agency 
 */
@Entity
@Table(name="clients")
public class Client extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	@NotBlank
	@Size(min=3, max=30)
	private String firstName;
	@NotBlank
	@Size(min=3, max=30)
	private String lastName;
	@NotBlank
	@Size(min=3, max=30)
	private String document1;
	@NotNull
	private String document2;
	@NotNull
	private String document3;
	@NotNull
	private String document4;
	@NotNull
	private String description;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((document1 == null) ? 0 : document1.hashCode());
		result = prime * result + ((document2 == null) ? 0 : document2.hashCode());
		result = prime * result + ((document3 == null) ? 0 : document3.hashCode());
		result = prime * result + ((document4 == null) ? 0 : document4.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (description == null)
		{
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (document1 == null)
		{
			if (other.document1 != null)
				return false;
		}
		else if (!document1.equals(other.document1))
			return false;
		if (document2 == null)
		{
			if (other.document2 != null)
				return false;
		}
		else if (!document2.equals(other.document2))
			return false;
		if (document3 == null)
		{
			if (other.document3 != null)
				return false;
		}
		else if (!document3.equals(other.document3))
			return false;
		if (document4 == null)
		{
			if (other.document4 != null)
				return false;
		}
		else if (!document4.equals(other.document4))
			return false;
		if (firstName == null)
		{
			if (other.firstName != null)
				return false;
		}
		else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null)
		{
			if (other.lastName != null)
				return false;
		}
		else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	/**
	 * @return the document1
	 */
	public String getDocument1()
	{
		return document1;
	}
	/**
	 * @param document1 the document1 to set
	 */
	public void setDocument1(String document1)
	{
		this.document1 = document1;
	}
	/**
	 * @return the document2
	 */
	public String getDocument2()
	{
		return document2;
	}
	/**
	 * @param document2 the document2 to set
	 */
	public void setDocument2(String document2)
	{
		this.document2 = document2;
	}
	/**
	 * @return the document3
	 */
	public String getDocument3()
	{
		return document3;
	}
	/**
	 * @param document3 the document3 to set
	 */
	public void setDocument3(String document3)
	{
		this.document3 = document3;
	}
	/**
	 * @return the document4
	 */
	public String getDocument4()
	{
		return document4;
	}
	/**
	 * @param document4 the document4 to set
	 */
	public void setDocument4(String document4)
	{
		this.document4 = document4;
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
}
