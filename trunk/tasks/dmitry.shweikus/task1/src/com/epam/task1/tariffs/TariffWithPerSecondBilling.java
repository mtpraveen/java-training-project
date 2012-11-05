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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(pricePerSecond);
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
		if (!(obj instanceof TariffWithPerSecondBilling))
			return false;
		TariffWithPerSecondBilling other = (TariffWithPerSecondBilling) obj;
		if (Double.doubleToLongBits(pricePerSecond) != Double
				.doubleToLongBits(other.pricePerSecond))
			return false;
		return true;
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
