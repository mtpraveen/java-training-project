/**
 * 
 */
package com.epam.task1.clients;

import com.epam.task1.tariffs.AbstractTariff;

/**
 * @author dima
 * Client for mobile network
 */
public class Client
{
	/**
	 * hold name of client
	 */
	private String name;
	/**
	 * hold tariff for client
	 */
	private AbstractTariff tariff;
	/**
	 * hold id of client
	 */
	private int id;
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
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
	 * @return the tariff
	 */
	public AbstractTariff getTariff()
	{
		return tariff;
	}
	/**
	 * @param tariff the tariff to set
	 */
	public void setTariff(AbstractTariff tariff)
	{
		this.tariff = tariff;
	}
	/**
	 * @param name - name for client
	 * @param tariff - tariff for client
	 */
	public Client(int id, String name, AbstractTariff tariff) {
		this.name = name;
		this.tariff = tariff;
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tariff == null) ? 0 : tariff.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tariff == null) {
			if (other.tariff != null)
				return false;
		} else if (!tariff.equals(other.tariff))
			return false;
		return true;
	}
}
