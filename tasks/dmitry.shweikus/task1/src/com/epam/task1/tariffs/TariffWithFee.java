/**
 * 
 */
package com.epam.task1.tariffs;

/**
 * @author dima
 * superclass for all tariffs with fee
 */
public abstract class TariffWithFee extends AbstractTariff
{
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
