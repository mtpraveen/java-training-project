package com.travel.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.db.ApplicationException;
import com.travel.pojo.Client;

public class ClientDaoTest
{
	ClientDao dao = new ClientDao();
	@Before
	@After
	public void setUp() throws ApplicationException
	{
		dao.deleteAll();
	}
	@Test
	public void testUpdate() throws ApplicationException
	{
		Client client1 = dao.create("Ivan", "Ivanov", "AB01", "INN001", "", "", "-");
		Client client2 = dao.create("Petr", "Petrov", "AB02", "INN002", "", "", "--");
		Client client3 = dao.create("Fedor", "Fedorov", "AB02", "INN003", "", "", "---");
		Client client2a = dao.findById(client2.getId());
		
		assertNotNull(client1);
		assertNotNull(client2);
		assertNotNull(client2a);
		assertNotNull(client3);
		
		
		
		assertEquals(client2.getFirstName(), "Petr");
		assertEquals(client2.getLastName(), "Petrov");
		
		client2.setFirstName("Sidor");
		assertTrue(dao.update(client2));
		Client client2b = dao.findById(client2.getId());
		assertNotNull(client2b);
		
		assertEquals(client2, client2b);
		assertFalse(client2a.equals(client2b));
		
		assertTrue(dao.delete(client1.getId()));
		assertNull(dao.findById(client1.getId()));
		
		Client client1clone = dao.create(client1);
		assertNotNull(client1clone);
		assertFalse(client1clone.equals(client1));
		client1clone.setId(client1.getId());
		assertEquals(client1, client1clone);
	}

}
