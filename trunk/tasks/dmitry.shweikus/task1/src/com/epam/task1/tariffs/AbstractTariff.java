/**
 *  
 */
package com.epam.task1.tariffs;

/**
 * @author dima
 * superclass for all tariffs
 */
public abstract class AbstractTariff
{
	/**
	 * hold tariff name 
	 */
	private String tariffName = "";
	/**
	 * 
	 */
	public AbstractTariff(String tariffName) {
		this.tariffName = tariffName;
	}
	/**
	 * @return fee for this tariff
	 */
	public abstract double getFee();
	/**
	 * @param secondsCount - length of conversation
	 * @return price for presented conversation
	 */
	public abstract double getConversationPrice(int secondsCount);
	/**
	 * @return name of tariff
	 */
	public final String getTariffName()
	{
		return tariffName;
	}
}
