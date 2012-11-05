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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tariffName == null) ? 0 : tariffName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractTariff))
			return false;
		AbstractTariff other = (AbstractTariff) obj;
		if (tariffName == null) {
			if (other.tariffName != null)
				return false;
		} else if (!tariffName.equals(other.tariffName))
			return false;
		return true;
	}

}
