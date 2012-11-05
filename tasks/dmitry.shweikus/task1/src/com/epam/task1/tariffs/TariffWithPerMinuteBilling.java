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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(pricePerMinute);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TariffWithPerMinuteBilling))
			return false;
		TariffWithPerMinuteBilling other = (TariffWithPerMinuteBilling) obj;
		if (Double.doubleToLongBits(pricePerMinute) != Double
				.doubleToLongBits(other.pricePerMinute))
			return false;
		return true;
	}

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
		int totalMinutes = (int) Math.ceil(secondsCount / 60.0);
		return totalMinutes * Math.max(pricePerMinute,1);
	}

}
