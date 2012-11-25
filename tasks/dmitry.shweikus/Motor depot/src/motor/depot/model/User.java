/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ITableRowProvider;

/**
 * @author dima
 *
 */
public abstract class User implements ICanBeSaved,ITableRowProvider
{
	//int id;
	String login;
	String password;
	/**
	 * @return the id
	 */
	/*public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}*/
	/**
	 * @return the login
	 */
	public String getLogin()
	{
		return login;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null)
		{
			if (other.login != null)
				return false;
		}
		else if (!login.equals(other.login))
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
	public abstract boolean isAdmin();
	
	public boolean mathLoginData(String login, String password)
	{
		return this.login.equals(login) && this.password.equals(password);
	}
	@Override
	public ITableRowProvider getRowProvider()
	{
		return this;
	}
	@Override
	public int getColCount()
	{
		return 2;
	}
	@Override
	public String getColName(int col)
	{
		switch (col) {
		case 0: return "Login";
		case 1: return "Password";
		}
		return "";
	}
	@Override
	public String getValue(int col)
	{
		switch (col) {
		case 0: return login;
		case 1: return password;
		}
		return "";
	}
}
