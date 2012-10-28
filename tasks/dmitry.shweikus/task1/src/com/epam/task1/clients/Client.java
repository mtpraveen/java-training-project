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
}
