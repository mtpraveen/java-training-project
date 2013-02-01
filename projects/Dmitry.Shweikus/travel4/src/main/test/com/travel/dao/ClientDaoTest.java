package com.travel.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;

public class ClientDaoTest
{
	ClientDao dao = new ClientDao();
	@Before
	@After
	public void setUp() throws DbSqlException
	{
		TestDatabaseDeleter.clearDatabase();
	}
	@Test
	public void testUpdate() throws DbSqlException
	{
		Client client1 = dao.create("Ivan", "Ivanov", "AB01", "INN001", "", "", "-");
		Client client2 = dao.create("Petr", "Petrov", "AB02", "INN002", "", "", "--");
		Client client3 = dao.create("Fedor", "Fedorov", "AB02", "INN003", "", "", "---");
		Client client2a = dao.findById(client2.getId());
		
		List<Client> clients = dao.findByName(client2.getFirstName(), client2.getLastName());
		assertEquals(1, clients.size());
		assertTrue(clients.indexOf(client2) == 0);
		
		assertNotNull(client1);
		assertNotNull(client2);
		assertNotNull(client2a);
		assertNotNull(client3);
		
		
		
		assertEquals(client2.getFirstName(), "Petr");
		assertEquals(client2.getLastName(), "Petrov");
		
		client2.setFirstName("Sidor");
		dao.update(client2);
		Client client2b = dao.findById(client2.getId());
		assertNotNull(client2b);
		
		assertEquals(client2, client2b);
		
		dao.delete(client1.getId());
		assertNull(dao.findById(client1.getId()));
	}

}
