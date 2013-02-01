/**
 * 
 */
package com.travel.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author dima
 * main class for users : managers and administrators. 
 *  
 */
@Entity
@Table(name="users")
public class User extends BaseEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Length(min=3, max=80)
	@Column(unique=true)
	private String name = "";
	@NotNull
	@Length(min=3, max=80)
	@Column(unique=true)
	private String login = "";
	@NotNull
	@Length(min=3, max=80)
	private String password = "";
	private boolean admin = false;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (admin != other.admin)
			return false;
		if (login == null)
		{
			if (other.login != null)
				return false;
		}
		else if (!login.equals(other.login))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
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
	 * @return the login
	 */
	public String getLogin()
	{
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login)
	{
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/**
	 * @return the admin
	 */
	public boolean isAdmin()
	{
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
}
