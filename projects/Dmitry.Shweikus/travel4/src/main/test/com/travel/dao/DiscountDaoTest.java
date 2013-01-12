/**
 * 
 */
package com.travel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Discount;

/**
 * @author dima
 *
 */
public class DiscountDaoTest
{

	DiscountDao dao = new DiscountDao();
	@Before
	@After
	public void setUp() throws Exception
	{
		dao.deleteAll();
	}

	@Test
	public void testUpdate() throws DbSqlException
	{
		Discount discount1 = dao.create(6000, 5, true); 
		Discount discount2 = dao.create(12000, 7, true); 
		Discount discount21 = dao.findById(discount1.getId());
		assertNotNull(discount1);
		assertNotNull(discount2);
		assertNotNull(discount21);
		
		discount2.setThreshold(15000);
		assertTrue(dao.update(discount2));
		Discount discount2b = dao.findById(discount2.getId());
		
		assertEquals(discount2, discount2b);
		assertFalse(discount21.equals(discount2b));
		
		assertTrue(dao.delete(discount1));
		assertNull(dao.findById(discount1.getId()));
	}

}
