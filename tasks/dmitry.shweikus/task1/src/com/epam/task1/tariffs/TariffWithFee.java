/**
 * 
 */
package com.epam.task1.tariffs;

import com.epam.task1.exceptions.NegativeFeeException;

/**
 * @author dima
 * superclass for all tariffs with fee
 */
public abstract class TariffWithFee extends AbstractTariff
{
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(fee);
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
		if (!(obj instanceof TariffWithFee))
			return false;
		TariffWithFee other = (TariffWithFee) obj;
		if (Double.doubleToLongBits(fee) != Double.doubleToLongBits(other.fee))
			return false;
		return true;
	}
	/**
	 * hold fee for this tariff
	 */
	private double fee;  
	/**
	 * @param tariffName - name for tariff
	 * @param fee - fee for tariff
	 */
	public TariffWithFee(String tariffName, double fee) {
		super(tariffName);
		if (fee < 0)
			throw new NegativeFeeException(fee);
		this.fee = fee;
	}
	/**
	 * @return fee for this tariff
	 */
	@Override
	public double getFee()
	{
		return fee;
	}
	
}
