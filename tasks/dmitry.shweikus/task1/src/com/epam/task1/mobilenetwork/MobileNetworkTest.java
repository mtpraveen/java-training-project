/**
 * 
 */
package com.epam.task1.mobilenetwork;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.epam.task1.tariffs.AbstractTariff;
import com.epam.task1.tariffs.TariffWithPerMinuteBilling;
import com.epam.task1.tariffs.TariffWithPerSecondBilling;
import com.epam.task1.tariffs.TariffWithoutFee;

/**
 * @author dima
 *
 */
public class MobileNetworkTest
{
	MobileNetwork network = new MobileNetwork();
	{
		network.addTariff(new TariffWithoutFee("UltraCheap", 800));
		network.addTariff(new TariffWithPerMinuteBilling("Practical", 8000,70));
		network.addTariff(new TariffWithPerMinuteBilling("Lighten", 3000,400));
		network.addTariff(new TariffWithPerSecondBilling("Default", 8001,140));
		
		network.addClient("Client 1", network.getTariff(0));
		network.addClient("Client 2", network.getTariff(0));
		network.addClient("Client 3", network.getTariff(0));
		network.addClient("Client 4", network.getTariff(1));
		network.addClient("Client 5", network.getTariff(1));
		network.addClient("Client 6", network.getTariff(2));
		network.addClient("Client 7", network.getTariff(2));
		network.addClient("Client 8", network.getTariff(3));
		network.addClient("Client 9", network.getTariff(3));
	}
	
	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#getTariffCount()}.
	 */
	@Test
	public void testGetTariffCount()
	{
		assertEquals(4, network.getTariffCount());
	}

	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#getClientCount()}.
	 */
	@Test
	public void testGetClientCount()
	{
		assertEquals(9, network.getClientCount());
	}

	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#getTariffsSortedByFee()}.
	 */
	@Test
	public void testGetTariffsSortedByFee()
	{
		ArrayList<AbstractTariff> tariffs = network.getTariffsSortedByFee();
		assertEquals(4, tariffs.size());
		assertEquals("UltraCheap", tariffs.get(0).getTariffName());
		assertEquals("Lighten", tariffs.get(1).getTariffName());
		assertEquals("Practical", tariffs.get(2).getTariffName());
		assertEquals("Default", tariffs.get(3).getTariffName());
	}

	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findAllTariffsMathesRange(double, double)}.
	 */
	@Test
	public void testFindAllTariffsMathesRange()
	{
		ArrayList<AbstractTariff> tariffs = network.findAllTariffsMathesRange(8000, 100);
		assertEquals(1, tariffs.size());
		assertEquals("Practical", tariffs.get(0).getTariffName());
		
		tariffs = network.findAllTariffsMathesRange(1000, 1000);
		assertEquals(1, tariffs.size());
		assertEquals("UltraCheap", tariffs.get(0).getTariffName());
	}

}
