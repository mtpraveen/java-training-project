/**
 * 
 */
package com.travel.hibernate;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.travel.dao.DiscountDao;
import com.travel.dao.TestDatabaseDeleter;
import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Discount;

/**
 * @author dima
 *
 */
public class TransienAndPersistStateTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	@After
	public void setUp() throws Exception
	{
		TestDatabaseDeleter.clearDatabase();
	}

	//now we use one session per request and this test is invalid
	//@Test
	public void testTransientAnsPersistState() throws DbSqlException
	{
		Discount discount;
		Discount discount1;
		Discount discount2;
		Discount discount3;

		
		DiscountDao dao = new DiscountDao();
		discount  = dao.create(700, 7, true);
		discount1 = dao.findById(discount.getId());
		
		discount1.setPercent(8);
		EntityManager em = ConnectionManager.getEntityManager();
		discount1 = em.merge(discount1);
		discount1 = em.merge(discount1);//checks that we can add persist object to manager
		discount1.setPercent(9); //*1

		discount2 = dao.findById(discount.getId());
		assertEquals(discount2.getPercent(), 7);//in dao 7, because no persist 
		
		discount3 = em.find(Discount.class, discount.getId());
		assertEquals(9, discount3.getPercent());
		assertTrue(discount3 == discount1);
		//in em 9, EntityManager knows that happens at *1 because discount3 link to discount1 
		
		discount3 = ConnectionManager.getEntityManager().find(Discount.class, discount.getId());
		assertEquals(7, discount3.getPercent());
		//7, because other EntityManager 

		em.getTransaction().begin();
		em.persist(discount1);
		em.getTransaction().commit();

		discount2 = dao.findById(discount.getId());
		assertEquals(discount2.getPercent(), 9);//in dao 9, after persist with transaction 
		
		em.remove(discount1);
		assertNotNull(dao.findById(discount.getId()));
		assertNull(em.find(Discount.class, discount.getId()));

		em.getTransaction().begin();
		em.remove(discount1);
		em.getTransaction().commit();
		assertNull(em.find(Discount.class, discount.getId()));
		assertNull(dao.findById(discount.getId()));
	}

}
