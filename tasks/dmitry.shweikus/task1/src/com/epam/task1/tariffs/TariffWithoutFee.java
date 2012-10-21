/**
 * 
 */
package com.epam.task1.tariffs;

/**
 * @author dima
 *
 */
public class TariffWithoutFee extends AbstractTariff
{

	/**
	 * @param tariffName
	 */
	public TariffWithoutFee(String tariffName, double minutePrice) {
		super(tariffName);
		this.pricePerMinute = minutePrice;
	}
	/**
	 * hold price per minute
	 */
	private double pricePerMinute; 
	/**
	 * @return fee for this tariff
	 */
	@Override
	public double getFee()
	{
		return 0;
	}

	/**
	 * @param secondsCount - length of conversation
	 * @return price for presented conversation
	 */
	@Override
	public double getConversationPrice(int secondsCount)
	{
		double totalMinutes = secondsCount / 60;
		if (secondsCount % 60 > 0)
		{
			totalMinutes++;
		}
		return totalMinutes * Math.max(pricePerMinute, 1);
	}

}
