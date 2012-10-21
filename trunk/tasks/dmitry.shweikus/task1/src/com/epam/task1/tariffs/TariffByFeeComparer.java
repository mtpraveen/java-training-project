/**
 * 
 */
package com.epam.task1.tariffs;

import java.util.Comparator;

/**
 * @author dima
 * class for compare tariffs by fee
 */
public class TariffByFeeComparer implements Comparator<AbstractTariff>
{
	/**
	 * compares 2 tariffs by fee
	 */
	@Override
	public int compare(AbstractTariff o1, AbstractTariff o2)
	{
		return Double.compare(o1.getFee(), o2.getFee());
	}

}
