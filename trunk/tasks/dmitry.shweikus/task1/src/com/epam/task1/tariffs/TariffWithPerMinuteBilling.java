/**
 * 
 */
package com.epam.task1.tariffs;

/**
 * @author dima
 *
 */
public class TariffWithPerMinuteBilling extends TariffWithFee
{
	/**
	 * hold price per minute
	 */
	private double pricePerMinute;
	/**
	 * @param tariffName - name for tariff
	 * @param fee - fee for tariff
	 * @param pricePerMinute - price per-second
	 */
	public TariffWithPerMinuteBilling(String tariffName, double fee, double pricePerMinute) {
		super(tariffName, fee);
		this.pricePerMinute = pricePerMinute;
	}
	/**
	 * @return the price per minute
	 */
	public double getPricePerMinute()
	{
		return pricePerMinute;
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
		return totalMinutes * Math.max(pricePerMinute,1);
	}

}
