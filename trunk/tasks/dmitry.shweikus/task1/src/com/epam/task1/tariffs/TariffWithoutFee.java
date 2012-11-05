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
		if (!(obj instanceof TariffWithoutFee))
			return false;
		TariffWithoutFee other = (TariffWithoutFee) obj;
		if (Double.doubleToLongBits(pricePerMinute) != Double
				.doubleToLongBits(other.pricePerMinute))
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
		int totalMinutes = (int) Math.ceil(secondsCount / 60.0);
		return totalMinutes * Math.max(pricePerMinute, 1);
	}

}
