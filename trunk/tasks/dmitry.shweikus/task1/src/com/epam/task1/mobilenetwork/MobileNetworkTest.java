/**
 * 
 */
package com.epam.task1.mobilenetwork;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.task1.clients.Client;
import com.epam.task1.exceptions.NegativeFeeException;
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
	/**
	 * 
	 */
	private static final String CLIENT_9 = "Client 9";
	/**
	 * 
	 */
	private static final String CLIENT_8 = "Client 8";
	/**
	 * 
	 */
	private static final String CLIENT_7 = "Client 7";
	/**
	 * 
	 */
	private static final String CLIENT_6 = "Client 6";
	/**
	 * 
	 */
	private static final String CLIENT_5 = "Client 5";
	/**
	 * 
	 */
	private static final String CLIENT_4 = "Client 4";
	/**
	 * 
	 */
	private static final String CLIENT_3 = "Client 3";
	/**
	 * 
	 */
	private static final String CLIENT_2 = "Client 2";
	/**
	 * 
	 */
	private static final String CLIENT_1 = "Client 1";
	/**
	 * 
	 */
	private static final String DEFAULT = "Default";
	/**
	 * 
	 */
	private static final String LIGHTEN = "Lighten";
	/**
	 * 
	 */
	private static final String PRACTICAL = "Practical";
	/**
	 * 
	 */
	private static final String ULTRA_CHEAP = "UltraCheap";
	static MobileNetwork network = new MobileNetwork();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		network.addTariff(new TariffWithoutFee(ULTRA_CHEAP, 800));
		network.addTariff(new TariffWithPerMinuteBilling(PRACTICAL, 8000,70));
		network.addTariff(new TariffWithPerMinuteBilling(LIGHTEN, 3000,400));
		network.addTariff(new TariffWithPerSecondBilling(DEFAULT, 8001,140));
		
		network.addClient(CLIENT_1, network.getTariff(0));
		network.addClient(CLIENT_2, network.getTariff(0));
		network.addClient(CLIENT_3, network.getTariff(0));
		network.addClient(CLIENT_4, network.getTariff(1));
		network.addClient(CLIENT_5, network.getTariff(1));
		network.addClient(CLIENT_6, network.getTariff(2));
		network.addClient(CLIENT_7, network.getTariff(2));
		network.addClient(CLIENT_8, network.getTariff(3));
		network.addClient(CLIENT_9, network.getTariff(3));
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
		assertEquals(ULTRA_CHEAP, tariffs.get(0).getTariffName());
		assertEquals(LIGHTEN, tariffs.get(1).getTariffName());
		assertEquals(PRACTICAL, tariffs.get(2).getTariffName());
		assertEquals(DEFAULT, tariffs.get(3).getTariffName());
	}

	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findAllTariffsMathesRange(double, double)}.
	 */
	@Test
	public void testFindAllTariffsMathesRange1()
	{
		ArrayList<AbstractTariff> tariffs = network.findAllTariffsMathesRange(8000, 100);
		assertEquals(1, tariffs.size());
		assertEquals(PRACTICAL, tariffs.get(0).getTariffName());
	}
	
	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findAllTariffsMathesRange(double, double)}.
	 */
	@Test
	public void testFindAllTariffsMathesRange2()
	{
		ArrayList<AbstractTariff> tariffs = network.findAllTariffsMathesRange(1000, 1000);
		assertEquals(1, tariffs.size());
		assertEquals(ULTRA_CHEAP, tariffs.get(0).getTariffName());
	}

	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findAllTariffsMathesRange(double, double)}.
	 */
	@Test
	public void testFindAllTariffsMathesRangeWhereTariffNotFound()
	{
		ArrayList<AbstractTariff> tariffs = network.findAllTariffsMathesRange(100, 100);
		assertEquals(0, tariffs.size());
	}

	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findClientByName(String)}.
	 */
	@Test
	public void testFindClientByName()
	{
		Client client = network.findClientByName(CLIENT_2);
		assertNotNull(client);
		assertEquals(client.getName(), CLIENT_2);
	}

	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findClientByName(String)}.
	 */
	@Test
	public void testFindClientByNameWhenNameNotFound()
	{
		Client client = network.findClientByName("this_is_non_existing_client_name");
		assertNull(client);
	}
	
	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findTariffByName(String)}.
	 */
	@Test
	public void testFindTariffByName()
	{
		AbstractTariff tariff = network.findTariffByName(PRACTICAL);
		assertNotNull(tariff);
		assertEquals(tariff.getTariffName(), PRACTICAL);
	}
	/**
	 * Test method for {@link com.epam.task1.mobilenetwork.MobileNetwork#findTariffByName(String)}.
	 */

	@Test
	public void testFindTariffByNameWhenNameNotFound()
	{
		AbstractTariff tariff = network.findTariffByName("this_is_non_existing_tariff_name");
		assertNull(tariff);
	}

	@Test
	public void testClientEquals()
	{
		Client client1 = new Client(1, CLIENT_1, network.getTariff(0));
		Client client2 = new Client(1, CLIENT_1, network.getTariff(0));
		assertEquals(client1, client2);
	}

	@Test
	public void testClientNotEqualsByTariff()
	{
		Client client1 = new Client(1, CLIENT_1, network.getTariff(0));
		Client client2 = new Client(1, CLIENT_1, network.getTariff(1));
		assertNotSame(client1, client2);
	}
	
	@Test
	public void testClientNotEqualsByName()
	{
		Client client1 = new Client(1, CLIENT_1, network.getTariff(0));
		Client client2 = new Client(1, CLIENT_2, network.getTariff(0));
		assertNotSame(client1, client2);
	}
	
	@Test(expected=NegativeFeeException.class)
	public void testExceptionByCreatingTariff()
	{
		AbstractTariff tariff = new TariffWithPerMinuteBilling(ULTRA_CHEAP, -5, 10);
	}

}
