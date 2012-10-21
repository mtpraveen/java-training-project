/**
 * 
 */
package com.epam.task1.tariffs;

/**
 * @author dima
 *
 */
public class TariffWithPerSecondBilling extends TariffWithFee
{
	/**
	 * hold price per second
	 */
	public double pricePerSecond;
	/**
	 * @return price per second
	 */
	public double getPricePerSecond()
	{
		return pricePerSecond;
	}

	/**
	 * @param tariffName - name for tariff
	 * @param fee - fee for tariff
	 * @param pricePerSecond - price per-second
	 */
	public TariffWithPerSecondBilling(String tariffName, double fee, double pricePerSecond) {
		super(tariffName, fee);
		this.pricePerSecond = pricePerSecond;
	}
	/**
	 * @param secondsCount - length of conversation
	 * @return price for presented conversation
	 */
	@Override
	public double getConversationPrice(int secondsCount)
	{
		return Math.max(1, secondsCount) * pricePerSecond;
	}

}
