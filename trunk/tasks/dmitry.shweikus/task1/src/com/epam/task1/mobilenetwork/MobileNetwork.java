/**
 * 
 */
package com.epam.task1.mobilenetwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import com.epam.task1.clients.Client;
import com.epam.task1.tariffs.AbstractTariff;
import com.epam.task1.tariffs.TariffByFeeComparer;
import com.epam.task1.tariffs.TariffWithPerMinuteBilling;
import com.epam.task1.tariffs.TariffWithPerSecondBilling;
import com.epam.task1.tariffs.TariffWithoutFee;

/**
 * @author dima
 *
 */
public class MobileNetwork
{
	/**
	 * hold all tariffs for mobile network
	 */
	private ArrayList<AbstractTariff> tariffs = new ArrayList<AbstractTariff>();
	/**
	 * 
	 * @param index - index of tariff
	 * @return tariff form index
	 */
	public AbstractTariff getTariff(int index)
	{
		return tariffs.get(index);
	}
	/**
	 * add tariff to network
	 * @param tariff - tariff for adding
	 */
	public void addTariff(AbstractTariff tariff)
	{
		tariffs.add(tariff);
	}
	/**
	 * 
	 * @return count of tariffs in mobile network
	 */
	public int getTariffCount()
	{
		return tariffs.size();
	}
	/**
	 * hold clients list
	 */
	private ArrayList<Client> clients = new ArrayList<Client>();
	/**
	 * @return clients count
	 */
	public int getClientCount()
	{
		return clients.size();
	}
	/**
	 * 
	 * @param index - index of client
	 * @return client from index
	 */
	public Client getClient(int index)
	{
		return clients.get(index);
	}
	/**
	 * add new client to network
	 * @param name - name for new client
	 * @param tariff - tariff for new client
	 */
	public void addClient(String name, AbstractTariff tariff)
	{
		Client client = new Client(name, tariff);
		clients.add(client);
	}
	/**
	 * 
	 * @return all tariff sorted by fee
	 */
	public ArrayList<AbstractTariff> getTariffsSortedByFee()
	{
		ArrayList<AbstractTariff> res = (ArrayList<AbstractTariff>)tariffs.clone();
		Collections.sort(res,new TariffByFeeComparer());
		return res;
	}
	public ArrayList<AbstractTariff> findAllTariffsMathesRange(double maxFee, double maxCallPrice)
	{
		ArrayList<AbstractTariff> res = new ArrayList<AbstractTariff>();
		for (AbstractTariff tariff : tariffs)
		{
			if (tariff.getFee() <= maxFee)
			{
				if (tariff.getConversationPrice(1) < maxCallPrice)
				{
					res.add(tariff);
				}
			}
		}
		return res;
	}

}
